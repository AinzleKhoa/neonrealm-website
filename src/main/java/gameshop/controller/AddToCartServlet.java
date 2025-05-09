/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package gameshop.controller;

import gameshop.DAO.CartDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * AddToCartServlet handles the request to add a game to the user's cart. It
 * checks the validity of the request and updates the cart in the database.
 *
 * @author CE180035 - Nguyen Huynh Nhat Thien
 */
public class AddToCartServlet extends HttpServlet {

    /**
     * Handles the POST request to add a game to the user's cart. It validates
     * the user, checks if the game is already in the cart, and then adds the
     * game to the cart if not already present.
     *
     * @param request the HttpServletRequest containing the user's request data
     * @param response the HttpServletResponse used to send the response
     * @throws ServletException if an error occurs during request processing
     * @throws IOException if an input/output error occurs during response
     * sending
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Request received!");

        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            System.out.println("Error: userId is null. Redirecting to login.");
            response.sendRedirect("login"); // Chuyển hướng về trang đăng nhập
            return;
        }

        String gameIdStr = request.getParameter("id");
        if (gameIdStr == null || !gameIdStr.matches("\\d+")) {
            System.out.println("Error: Invalid gameId: " + gameIdStr);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Game id incorrect.");
            return;
        }

        int gameId = Integer.parseInt(gameIdStr);
        System.out.println("userId: " + userId + ", gameId: " + gameId);

        CartDAO cartDAO = new CartDAO();

        boolean exists = cartDAO.isGameInCart(userId, gameId);
        if (exists) {
            System.out.println("Game already in cart");
            response.getWriter().print("{\"status\":\"exists\"}");
            return;
        }

        boolean success = cartDAO.addToCart(userId, gameId);
        if (success) {
            int cartCount = cartDAO.getCartByUserId(userId).size();
            double totalPrice = cartDAO.getTotalCartPrice(userId);
            session.setAttribute("totalPrice", totalPrice);
            System.out.println("Game added successfully. Cart Count: " + cartCount + ", Total Price: " + totalPrice);
            response.getWriter().print("{\"status\":\"success\", \"cartCount\":" + cartCount + ", \"totalPrice\":" + totalPrice + "}");
        } else {
            System.out.println("Error: Failed to add game to cart");
            response.getWriter().print("{\"status\":\"error\"}");
        }
    }

}
