/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package gameshop.controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

/**
 *
 * @author Le Anh Khoa - CE190449
 */
public class GitHubOAuthServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP GET method for redirecting the user to GitHub's OAuth
     * authorization page. This method constructs the authorization URL and
     * redirects the user to GitHub for login and authorization.
     *
     * @param request the servlet request
     * @param response the servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String clientId = System.getenv("GITHUB_CLIENT_ID")  // Your GitHub OAuth Client ID

        String redirectUri = "http://localhost:8080/NeonRealm/auth/github/callback";
        String oauthUrl = "https://github.com/login/oauth/authorize?client_id=" + clientId
                + "&redirect_uri=" + redirectUri
                + "&scope=user:email";
        response.sendRedirect(oauthUrl);
    }

    /**
     * Handles the HTTP POST method for the GitHub OAuth servlet. This method is
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
