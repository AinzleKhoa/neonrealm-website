/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameshop.filter;

import gameshop.util.SessionUtil;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter to check if the user is logged in before granting access to certain
 * resources. This filter ensures that only authenticated users can access
 * specific pages or URLs.
 *
 * @author Le Anh Khoa - CE190449
 */
//@WebFilter(filterName = "auth", urlPatterns = {"/artist", "/album"})
public class AuthFilter implements Filter {

    /**
     * Checks if the user is logged in by verifying the session. If the user is
     * logged in, the request is forwarded to the next filter or resource. If
     * the user is not logged in, they are redirected to the login page.
     *
     * @param request the servlet request
     * @param response the servlet response
     * @param chain the filter chain to continue processing the request
     * @throws IOException if an I/O error occurs during the filter processing
     * @throws ServletException if a servlet-specific error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Kiem tra session co hop le hay khong (dang nhap chua)
        HttpServletRequest req = (HttpServletRequest) request;

        if (SessionUtil.isLoggedIn(req)) {
            chain.doFilter(req, response);
            System.out.println("User has logged in!");
        } else {
            ((HttpServletResponse) response).sendRedirect(req.getContextPath() + "/login");
            System.out.println("❌ User not logged in! Redirecting to login page");
        }
    }

}
