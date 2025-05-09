/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package gameshop.controller;

import gameshop.DAO.UserDAO;
import gameshop.model.User;
import gameshop.util.GitHubOAuthUtil;
import gameshop.util.InputValidator;
import gameshop.util.SessionUpdater;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/**
 *
 * @author Le Anh Khoa - CE190449
 */
public class GitHubOAuthCallbackServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP GET method for GitHub OAuth callback. This method
     * processes the authorization code from GitHub, retrieves the access token,
     * fetches user info from GitHub, and either logs the user in or creates a
     * new account.
     *
     * @param request the servlet request
     * @param response the servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter("code");
        if (code == null) {
            response.sendRedirect("/error?error=GitHub authorization failed");
            return;
        }

        // Get access token from GitHub
        String accessToken = GitHubOAuthUtil.getAccessToken(code);
        if (accessToken == null) {
            response.sendRedirect("/error?error=GitHub token retrieval failed");
            return;
        }

        // Get user info from GitHub API
        JSONObject userInfo = GitHubOAuthUtil.getUserInfo(accessToken);
        if (userInfo == null) {
            response.sendRedirect("/error?error=GitHub user retrieval failed");
            return;
        }

        // Extract user details
        String githubId = String.valueOf(userInfo.getLong("id"));
        String username = userInfo.optString("login");
        String email = userInfo.optString("email", githubId + "@github.com");

        UserDAO userDAO = new UserDAO();
        HttpSession session = request.getSession(true);
        User user = userDAO.findByGitHubId(githubId);

        if (user == null) { // If user doesn't exist, create a new user
            user = new User(username, email, null, githubId, "github");
            userDAO.signup(user);

            session.setAttribute("currentUser", user);
            session.setAttribute("currentEmail", user.getEmail());
            response.sendRedirect(request.getContextPath() + "/home");
        } else { // If the user exists
            if ((Boolean) session.getAttribute("isUpdating") != null && (Boolean) session.getAttribute("isUpdating")) { // If user exists and is updating their profile details
                session.setAttribute("isUpdating", Boolean.FALSE); // Reset after proceed
                updateUserProfile(request, response, session, userDAO);
            } else {
                // If user login with github
                session.setAttribute("currentUser", user);
                session.setAttribute("currentEmail", user.getEmail());
                response.sendRedirect(request.getContextPath() + "/home");
            }
        }
    }

    /**
     * Method to handle profile update when the user is authenticated through
     * GitHub. Validates the new email and username, updates the profile, and
     * returns success or error messages.
     *
     * @param request the HttpServletRequest
     * @param response the HttpServletResponse
     * @param session the current HttpSession
     * @param userDAO the UserDAO instance
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private void updateUserProfile(HttpServletRequest request, HttpServletResponse response,
            HttpSession session, UserDAO userDAO)
            throws ServletException, IOException {

        List<String> errorMessages = new ArrayList<>();
        String username = (String) session.getAttribute("profileUsername");
        String email = (String) session.getAttribute("profileEmail");
        int id = (Integer) session.getAttribute("profileUserId");

        // Validate the new email (if changed)
        if (email != null && session.getAttribute("currentEmail") != null
                && !email.equals(session.getAttribute("currentEmail"))) {
            if (userDAO.isEmailExists(email)) {
                errorMessages.add("Email already exists!");
            }
        }

        // Validate username and email format
        if (!InputValidator.isUsernameValid(username)) {
            errorMessages.add("Username must be 3-20 characters (letters, numbers, underscores only).");
        }
        if (!InputValidator.isEmailValid(email)) {
            errorMessages.add("Invalid email format. Example: user@example.com");
        }

        // If there are validation errors, redirect back to the profile page with errors
        if (!errorMessages.isEmpty()) {
            request.setAttribute("errors", errorMessages);
            request.getRequestDispatcher("/WEB-INF/pages/profile.jsp").forward(request, response);
            return;
        }

        // Update the user profile if validation passes
        if (userDAO.profileUpdate(id, username, email) > 0) {
            // Dynamically update the session with the new user data
            SessionUpdater.sessionUpdate(session, id);

            // Set success message
            request.setAttribute("success", "Successfully updated your account info!!");
            request.getRequestDispatcher("/WEB-INF/pages/profile.jsp").forward(request, response);
        } else {
            errorMessages.add("Something went wrong while updating your profile.");
            request.setAttribute("errors", errorMessages);
            request.getRequestDispatcher("/WEB-INF/pages/profile.jsp").forward(request, response);
        }
    }

    /**
     * Handles the HTTP POST method for the GitHub OAuth callback. This method
     * is currently not implemented.
     *
     * @param request the servlet request
     * @param response the servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
