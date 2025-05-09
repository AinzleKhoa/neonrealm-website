/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package gameshop.controller;

import gameshop.DAO.UserDAO;
import gameshop.model.User;
import gameshop.util.PasswordUtils;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.security.SecureRandom;
import java.util.Base64;

/**
 *
 * @author Le Anh Khoa - CE190449
 */
public class LoginServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP GET method for displaying the login page. This method
     * forwards the request to the login.jsp page.
     *
     * @param request the servlet request
     * @param response the servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Check if the "rememberMeToken" cookie is present
        Cookie[] cookies = request.getCookies();
        String rememberMeToken = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("rememberMeToken".equals(cookie.getName())) {
                    rememberMeToken = cookie.getValue();
                    break;
                }
            }
        }

        if (rememberMeToken != null) {
            // Validate token (retrieve user by token from the database)
            UserDAO uDAO = new UserDAO();
            User user = uDAO.getUserByRememberMeToken(rememberMeToken);

            if (user != null) {
                // Token is valid, log the user in
                HttpSession session = request.getSession(true);
                session.setAttribute("currentUser", user);
                session.setAttribute("currentEmail", user.getEmail());
                session.setMaxInactiveInterval(30 * 60); // 30 minutes session timeout
                response.sendRedirect(request.getContextPath() + "/home");
                return;
            }
        }

        request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP POST method for processing login requests. This method
     * validates the user's email and password, manages session creation, and
     * handles different login error scenarios (e.g., invalid email, invalid
     * password, locked account).
     *
     * @param request the servlet request
     * @param response the servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Destroy old session if any (to *prevent session fixation attacks)
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("remember");

        UserDAO uDAO = new UserDAO();
        User user = uDAO.login(email, password); // Pass raw password (comparison is done inside login method)

        if (user == null) { // Something went wrong
            request.setAttribute("error", "Something went wrong");
            request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
        } else if (user.getUserId() == -1) { // Account locked - *prevent brute force attacks
            request.setAttribute("error", "Your account is locked. Try again later.");
            request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
        } else if (user.getUserId() == -2) { // Invalid password
            request.setAttribute("error", "Invalid password.");
            request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
        } else if (user.getUserId() == -3) { // Invalid email
            request.setAttribute("error", "Invalid email.");
            request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
        } else { // If user exists (-4 meant OAuth user)
            session = request.getSession(true); // Create a new secure session
            session.setAttribute("currentUser", user); // Current user
            session.setAttribute("currentEmail", user.getEmail());
            // For buy now
            session.setAttribute("userId", user.getUserId());
            session.setAttribute("userName", user.getUsername());
            // Set session timeout - *prevent Session Hijacking
            session.setMaxInactiveInterval(30 * 60); // 30 minutes without interacting

            // Handle "Remember Me" functionality
            if ("on".equals(rememberMe)) {
                System.out.println("TOKEN ON !");
                // Generate a secure token (for simplicity, we use a random string here)
                String token = generateSecureToken();
                // Save the token in the database, linked to the user
                uDAO.saveRememberMeToken(user.getUserId(), token);

                // Create a cookie for the token
                Cookie rememberMeCookie = new Cookie("rememberMeToken", token);
                rememberMeCookie.setMaxAge(30 * 24 * 60 * 60); // Set cookie expiration to 30 days
                rememberMeCookie.setPath("/"); // Make cookie available to all paths of the application
                response.addCookie(rememberMeCookie);
            }

            response.sendRedirect(request.getContextPath() + "/home");
        }
    }

    /**
     * Generates a secure random token using `SecureRandom`. The token is
     * generated as a byte array of 24 bytes (192 bits) and then encoded as a
     * Base64 string.
     *
     * @return A Base64 encoded string representing the secure random token.
     */
    private String generateSecureToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] token = new byte[24]; // 24 bytes for the token (192 bits)
        secureRandom.nextBytes(token);
        return Base64.getEncoder().encodeToString(token);
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
