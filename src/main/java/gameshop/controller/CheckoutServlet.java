/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package gameshop.controller;

import gameshop.DAO.CartDAO;
import gameshop.model.Cart;
import gameshop.model.Game;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author CE171450 - Nguyen Hai Nam
 */
public class CheckoutServlet extends HttpServlet {
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
        //int userId = 1;
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        String action = request.getParameter("action");
        //if (userId != null) {
        CartDAO cartDAO = new CartDAO();
        List<Game> gamesInCart = cartDAO.getGamesInCartByUserId(userId);
        request.setAttribute("gamesInCart", gamesInCart);
        //List<Cart> cartList = cartDAO.getAllCarts();
        //double totalPrice = cartDAO.getAllTotalCartPrice();
        List<Cart> cartList = cartDAO.getCartByUserId(userId);
        double totalPrice = cartDAO.getTotalCartPrice(userId);
        session.setAttribute("discountPercen", 0);

        // Đưa dữ liệu vào request
        request.setAttribute("cartList", cartList);
        request.setAttribute("cartCount", cartList.size());
        request.setAttribute("totalPrice", totalPrice);
        /**
         * }else{ request.setAttribute("cartCount", 0);
         * request.setAttribute("totalPrice", 0.0); }*
         */
        // Forward về trang header.jsp
        request.getRequestDispatcher("WEB-INF/pages/checkout.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        // Kiểm tra đăng nhập
        if (userId == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            out.print("{\"status\":\"error\", \"message\":\"User not logged in\"}");
            out.flush();
            return;
        }
        if ("delete".equals(action)) {
            String gameIdStr = request.getParameter("gameId");
            if (gameIdStr == null || !gameIdStr.matches("\\d+")) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"status\":\"error\", \"message\":\"Invalid game ID\"}");
                out.flush();
                return;
            }

            int gameId = Integer.parseInt(gameIdStr);
            CartDAO cartDAO = new CartDAO();
            boolean success = cartDAO.removeFromCart(userId, gameId);

            if (success) {
                // Lấy danh sách giỏ hàng cập nhật sau khi xóa
                List<Cart> cart = cartDAO.getCartByUserId(userId);
                double totalPrice = cartDAO.getTotalCartPrice(userId);

                session.setAttribute("totalPrice", totalPrice);

                out.print("{\"status\":\"success\", \"gameId\":" + gameId
                        + ", \"cartCount\":" + cart.size()
                        + ", \"totalPrice\":" + totalPrice + "}");
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                out.print("{\"status\":\"error\", \"message\":\"Failed to remove game\"}");
            }
        }

    }

}
