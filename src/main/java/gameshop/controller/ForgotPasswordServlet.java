package gameshop.controller;

import gameshop.DAO.UserDAO;
import gameshop.util.EmailUtils;
import gameshop.util.PasswordUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.SecureRandom;

/**
 *
 * @author CE171450 - Nguyen Hai Nam
 */
@WebServlet("/forgot-password")
public class ForgotPasswordServlet extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/forgot-password.jsp").forward(request, response);
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        UserDAO userDAO = new UserDAO();

        // Kiểm tra email tồn tại trong CSDL
        if (!userDAO.isEmailExists(email)) {
            request.setAttribute("error", "Email không tồn tại!");
            request.getRequestDispatcher("/WEB-INF/pages/forgot-password.jsp").forward(request, response);
            return;
        }

        // Sinh mật khẩu mới ngẫu nhiên (độ dài 8 ký tự)
        String newPassword = generateRandomPassword(8);
        
        // Mã hoá mật khẩu mới trước khi lưu vào CSDL
        String hashedPassword = PasswordUtils.hashPassword(newPassword);

        // Cập nhật mật khẩu mới vào CSDL
        userDAO.updatePassword(email, hashedPassword);

        // Gửi mật khẩu mới qua email
        String emailContent = "Your New Password: " + newPassword;
        EmailUtils.sendEmail(email, "Password reset request", emailContent);

        request.setAttribute("message", "A new password has been sent to your email.");
        request.getRequestDispatcher("/WEB-INF/pages/forgot-password.jsp").forward(request, response);
    }

    // Hàm sinh mật khẩu ngẫu nhiên với độ dài cho trước
    private String generateRandomPassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(chars.length());
            sb.append(chars.charAt(index));
        }
        return sb.toString();
    }
}
