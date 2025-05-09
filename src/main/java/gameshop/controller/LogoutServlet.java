/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package gameshop.controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Le Anh Khoa - CE190449
 */
public class LogoutServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP GET method for logging out the user. This method
     * invalidates the current session and redirects the user to the login page.
     *
     * @param request the servlet request
     * @param response the servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Get session, don't create new one

        if (session != null) {
            session.invalidate();
        }

        // Clear the "rememberMeToken" cookie if it exists
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("rememberMeToken".equals(cookie.getName())) {
                    // Set the cookie's max age to 0 to delete it
                    cookie.setMaxAge(0);
                    cookie.setPath("/"); // Ensure the path matches the cookie's original path
                    response.addCookie(cookie); // Add the cleared cookie to the response
                    break;
                }
            }
        }

        response.sendRedirect(request.getContextPath() + "/login");
    }

    /**
     * Handles the HTTP POST method for logging out the user. This method is
     * currently not implemented.
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
