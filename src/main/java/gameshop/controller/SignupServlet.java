/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package gameshop.controller;

import gameshop.DAO.UserDAO;
import gameshop.model.User;
import gameshop.util.InputValidator;
import gameshop.util.PasswordUtils;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Le Anh Khoa - CE190449
 */
public class SignupServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP GET method for displaying the signup page. This method
     * forwards the request to the signup.jsp page where the user can input
     * their details to sign up.
     *
     * @param request the servlet request
     * @param response the servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/pages/signup.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP POST method for processing the signup form submission.
     * This method validates the user's input, checks for errors (e.g., matching
     * passwords, valid email, etc.), hashes the password, and attempts to
     * create a new user account.
     *
     * @param request the servlet request
     * @param response the servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserDAO uDAO = new UserDAO();
        // For debug, creating a whole new account (BCrypt password cant be insert manually in SQL)
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");

        List<String> errorMessages = new ArrayList<>();

        if (!password.equals(confirmPassword)) {
            errorMessages.add("Passwords do not match!");
        }
        if (uDAO.isEmailExists(email)) {
            errorMessages.add("Email already existed!");
        }
        if (!InputValidator.isUsernameValid(username)) {
            errorMessages.add("Username must be 3-20 characters (letters, numbers, underscores only).");
        }
        if (!InputValidator.isEmailValid(email)) {
            errorMessages.add("Invalid email format. Example: user@example.com");
        }
        if (!InputValidator.isPasswordValid(password)) {
            errorMessages.add("Password must be at least 8 characters with 1 letter & 1 number.");
        }

        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
        if (gRecaptchaResponse == null || !verifyRecaptcha(gRecaptchaResponse)) {
            errorMessages.add("Captcha verification failed. Please try again.");
        }

        // If there are errors, return
        if (!errorMessages.isEmpty()) {
            request.setAttribute("errors", errorMessages);
            request.getRequestDispatcher("/WEB-INF/pages/signup.jsp").forward(request, response);
            return;
        }

        String hashedPassword = PasswordUtils.hashPassword(password); // Hash the password before storing it

        if (uDAO.signup(new User(username, email, hashedPassword, null, "local")) > 0) {
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            response.sendRedirect(request.getContextPath() + "/signup");
        }
    }

    private boolean verifyRecaptcha(String gRecaptchaResponse) throws IOException {
        String secretKey = "6LeIxAcTAAAAAGG-vFI1TnRWxMZNFuojJ4WifJWe"; // secret key
        String url = "https://www.google.com/recaptcha/api/siteverify";
        String params = "secret=" + secretKey + "&response=" + gRecaptchaResponse;

        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.getOutputStream().write(params.getBytes());

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString().contains("\"success\": true");
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
