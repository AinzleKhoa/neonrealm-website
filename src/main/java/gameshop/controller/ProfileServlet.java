/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package gameshop.controller;

import gameshop.DAO.OrderDAO;
import gameshop.DAO.UserDAO;
import gameshop.model.OrderDetails;
import gameshop.model.User;
import gameshop.util.InputValidator;
import gameshop.util.PasswordUtils;
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

/**
 *
 * @author Le Anh Khoa - CE190449
 */
public class ProfileServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        OrderDAO oDAO = new OrderDAO();
        User user = (User) session.getAttribute("currentUser"); // Cast to User

        List<OrderDetails> orderDetailsList = oDAO.getOrderHistory(user.getUserId());

        // Set attribute to forward data to JSP
        request.setAttribute("orderHistory", orderDetailsList);

        request.getRequestDispatcher("/WEB-INF/pages/profile.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method. 1
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String authProvider = request.getParameter("authProvider");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String oldPass = request.getParameter("oldpass");
        String newPass = request.getParameter("newpass");
        String confirmNewPass = request.getParameter("confirmnewpass");

        HttpSession session = request.getSession(false); // Get the current session, don't create if not exists

        if (!authProvider.equals("local")) {
            session.setAttribute("profileUsername", username);
            session.setAttribute("profileEmail", email);
            session.setAttribute("profileUserId", id);
            session.setAttribute("isUpdating", Boolean.TRUE);
            response.sendRedirect(request.getContextPath() + "/auth/github/authorize");
            return;
        } // If OAuth is not local, redirect to github servlet

        UserDAO uDAO = new UserDAO();

        List<String> errorMessages = new ArrayList<>();

        int result = uDAO.profileUpdateAuth(id, oldPass);

        if ((newPass != null && !newPass.trim().isEmpty())) {
            switch (result) {
                case 1:
                    // Nothing happen, moving on.
                    break;
                case -1:
                    errorMessages.add("Your account is locked. Try again later.");
                    request.setAttribute("errors", errorMessages);
                    request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
                    return;
                case -2:
                    errorMessages.add("Incorrect old password! Too many failed attempts may lock your account.");
                    request.setAttribute("errors", errorMessages);
                    request.getRequestDispatcher("/WEB-INF/pages/profile.jsp").forward(request, response);
                    return;
                default:
                    errorMessages.add("Something went wrong!");
                    request.setAttribute("errors", errorMessages);
                    request.getRequestDispatcher("/WEB-INF/pages/profile.jsp").forward(request, response);
                    return;
            }

            // After password is correct
            if (oldPass.equals(newPass)) {
                errorMessages.add("The old password is identical to the new password!");
            }
            if (!newPass.equals(confirmNewPass)) {
                errorMessages.add("New Passwords do not match!");
            }
            // Only check email if it's changed
            if (email != null && session.getAttribute("currentEmail") != null && !email.equals(session.getAttribute("currentEmail"))) {
                if (uDAO.isEmailExists(email)) {
                    errorMessages.add("Email already exists!");
                }
            }
            if (!InputValidator.isUsernameValid(username)) {
                errorMessages.add("Username must be 3-20 characters (letters, numbers, underscores only).");
            }
            if (!InputValidator.isEmailValid(email)) {
                errorMessages.add("Invalid email format. Example: user@example.com");
            }
            if (!InputValidator.isPasswordValid(newPass)) {
                errorMessages.add("Password must be at least 8 characters with 1 letter & 1 number.");
            }
            // If there are errors, return
            if (!errorMessages.isEmpty()) {
                request.setAttribute("errors", errorMessages);
                request.getRequestDispatcher("/WEB-INF/pages/profile.jsp").forward(request, response);
                return;
            }

            String hashedNewPassword = PasswordUtils.hashPassword(newPass); // Hash the password before storing it

            if (uDAO.profileUpdate(id, username, email, hashedNewPassword) > 0) {
                // Use the utility method to dynamically update the session with the new user data
                SessionUpdater.sessionUpdate(request.getSession(false), id);
                request.setAttribute("success", "Successfully updated your account info!!");
                request.getRequestDispatcher("/WEB-INF/pages/profile.jsp").forward(request, response);
            } else {
                errorMessages.add("Something went wrong...");
                request.setAttribute("errors", errorMessages);
                request.getRequestDispatcher("/WEB-INF/pages/profile.jsp").forward(request, response);
            }
        } else { // When people dont want to change their password
            // Only check email if it's changed
            if (email != null && session.getAttribute("currentEmail") != null && !email.equals(session.getAttribute("currentEmail"))) {
                if (uDAO.isEmailExists(email)) {
                    errorMessages.add("Email already exists!");
                }
            }
            if (!InputValidator.isUsernameValid(username)) {
                errorMessages.add("Username must be 3-20 characters (letters, numbers, underscores only).");
            }
            if (!InputValidator.isEmailValid(email)) {
                errorMessages.add("Invalid email format. Example: user@example.com");
            }
            // If there are errors, return
            if (!errorMessages.isEmpty()) {
                request.setAttribute("errors", errorMessages);
                request.getRequestDispatcher("/WEB-INF/pages/profile.jsp").forward(request, response);
                return;
            }

            if (uDAO.profileUpdate(id, username, email) > 0) {
                // Use the utility method to dynamically update the session with the new user data
                SessionUpdater.sessionUpdate(request.getSession(false), id);
                request.setAttribute("success", "Successfully updated your account info!!");
                request.getRequestDispatcher("/WEB-INF/pages/profile.jsp").forward(request, response);
            } else {
                errorMessages.add("Something went wrong...");
                request.setAttribute("errors", errorMessages);
                request.getRequestDispatcher("/WEB-INF/pages/profile.jsp").forward(request, response);
            }
        }
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
