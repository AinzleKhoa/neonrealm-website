/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package gameshop.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import gameshop.DAO.UserDAO;
import gameshop.model.GoogleUser;
import gameshop.model.User;
import gameshop.service.GoogleOAuthService;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.security.GeneralSecurityException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CE171450 - Nguyen Hai Nam
 */
@WebServlet("/google-callback")
public class GoogleCallbackServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter("code");

        if (code == null || code.isEmpty()) {
            response.sendRedirect("login.jsp?error=google_auth_failed");
            return;
        }

        GoogleOAuthService googleService = new GoogleOAuthService();
        GoogleTokenResponse tokenResponse = googleService.getTokens(code);

        String idTokenString = tokenResponse.getIdToken(); // Lấy id_token
        
        GoogleUser googleUser;
        try {
            googleUser = googleService.getUserInfo(idTokenString);
        } catch (GeneralSecurityException | IOException ex) {
            Logger.getLogger(GoogleCallbackServlet.class.getName()).log(Level.SEVERE, "Lỗi khi giải mã ID token", ex);
            response.sendRedirect("login.jsp?error=google_auth_failed");
            return;
        }

        // Kiểm tra user trong database
        UserDAO userDAO = new UserDAO();
        User existingUser = userDAO.getUserByEmail(googleUser.getEmail());

        HttpSession session = request.getSession(true);
        if (existingUser == null) {
            // Nếu chưa có, đăng ký user mới
            User newUser = new User(googleUser.getEmail(), googleUser.getName(), "GOOGLE");
            userDAO.insertUser(newUser);
            session.setAttribute("currentUser", newUser);
        } else {
            session.setAttribute("currentUser", existingUser);
        }

        //  Chuyển hướng về trang Home
        response.sendRedirect(request.getContextPath() + "/home");
    }

}
