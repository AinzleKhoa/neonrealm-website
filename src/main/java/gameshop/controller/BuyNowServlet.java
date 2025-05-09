/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package gameshop.controller;

import gameshop.DAO.GameDAO;
import gameshop.model.Game;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * BuyNowServlet handles the process of purchasing a game. It checks if the user
 * is logged in, retrieves game information from the database, and forwards the
 * game details to the checkout page.
 *
 * @author CE180035 - Nguyen Huynh Nhat Thien
 */
public class BuyNowServlet extends HttpServlet {

    /**
     * Handles the POST request for buying a game. It verifies the user's login
     * status, retrieves game information from the database, and forwards the
     * details to the checkout page.
     *
     * @param request the HttpServletRequest containing the user's request data
     * @param response the HttpServletResponse used to send the response
     * @throws ServletException if an error occurs during request processing
     * @throws IOException if an input/output error occurs during response
     * sending
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String gameId = request.getParameter("id");
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            System.out.println("Error: userId is null. Redirecting to login.");
            response.sendRedirect("login"); // Chuyển hướng về trang đăng nhập
            return;
        }
        if (gameId == null || gameId.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Game ID is missing!");
            return;
        }

        // Lấy thông tin game từ database
        GameDAO gameDAO = new GameDAO();
        Game game = gameDAO.getGameById(Integer.parseInt(gameId));

        if (game == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Game not found!");
            return;
        }

        // Gửi game đến trang checkout.jsp
        request.setAttribute("checkoutGame", game);

        // Chuyển tiếp đến trang checkout.jsp thay vì redirect
        request.getRequestDispatcher("WEB-INF/pages/buynow.jsp").forward(request, response);
    }
}
