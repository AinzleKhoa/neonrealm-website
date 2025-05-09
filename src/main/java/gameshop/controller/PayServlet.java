/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package gameshop.controller;

import gameshop.DAO.CartDAO;
import gameshop.DAO.CouponDAO;
import gameshop.DAO.GameDAO;
import gameshop.DAO.OrderDAO;
import gameshop.DAO.OrderDetailsDAO;
import gameshop.model.Coupon;
import gameshop.model.Game;
import gameshop.model.Order;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author CE180035 - Nguyen Huynh Nhat Thien
 */
public class PayServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Request received!");
        HttpSession session = request.getSession();
        String couponCode = request.getParameter("couponCode"); // Lấy coupon từ request
        String action = request.getParameter("action");
        double discountPercent = 0.0;
        double newTotal = 0.0;

        if ("couponBuyNowCheck".equals(action)) {
            GameDAO gDAO = new GameDAO();
            int gameId = (int) session.getAttribute("BuyNowGameId");
            Game game = gDAO.getGameById(gameId);

            CouponDAO cDAO = new CouponDAO();
            Coupon coupon = cDAO.getValidCoupon(couponCode);

            if (coupon != null && game.getPrice() != null) {
                discountPercent = coupon.getDiscountPercentage();
                session.setAttribute("buyNowDiscountPercent", discountPercent);
                session.setAttribute("buyNowCouponCode", couponCode);
                Double totalPrice = game.getPrice().doubleValue();
                double discountAmout = totalPrice * (discountPercent / 100);
                double buyNowNewTotal = totalPrice - discountAmout;
                session.setAttribute("BuyNowNewTotal", buyNowNewTotal);
                session.setAttribute("discountAmout", discountAmout);

                Map<String, Object> jsonResponse = new HashMap<>();
                jsonResponse.put("status", "success");
                jsonResponse.put("discountPercent", discountPercent);
                jsonResponse.put("newTotal", newTotal);

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(new com.google.gson.Gson().toJson(jsonResponse));

            } else {
                response.getWriter().write("{\"status\": \"exists\"}");
            }
        }

        if ("couponCheck".equals(action)) {
            Double totalPrice = (Double) session.getAttribute("totalPrice");
            if (couponCode == null || couponCode.isEmpty()) {
                System.out.println("Error: Coupon code is empty");
                response.getWriter().print("{\"status\":\"error\"}");
                return;
            }

            CouponDAO cDAO = new CouponDAO();
            Coupon coupon = cDAO.getValidCoupon(couponCode);

            if (coupon != null && totalPrice != null) {
                discountPercent = coupon.getDiscountPercentage();
                session.setAttribute("discountPercent", discountPercent);
                session.setAttribute("couponCode", couponCode);
                double discountAmount = totalPrice * (discountPercent / 100);
                newTotal = totalPrice - discountAmount;
                session.setAttribute("totalPrice", newTotal);
                session.setAttribute("discountAmount", discountAmount);

                Map<String, Object> jsonResponse = new HashMap<>();
                jsonResponse.put("status", "success");
                jsonResponse.put("discountPercent", discountPercent);
                jsonResponse.put("newTotal", newTotal);

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(new com.google.gson.Gson().toJson(jsonResponse));
            } else {
                response.getWriter().write("{\"status\": \"exists\"}");
            }
        }
        if ("buynow".equals(action)) {
            int userId = (Integer) session.getAttribute("userId"); // Lấy userId từ session
            CouponDAO cDAO = new CouponDAO();
            GameDAO gDAO = new GameDAO();
            OrderDAO ordersDAO = new OrderDAO();
            String gameId = (String) session.getAttribute("BuyNowGameId");
            int newGameId = Integer.parseInt(gameId);
            Game game = gDAO.getGameById(newGameId);
            LocalDateTime now = LocalDateTime.now();

            Double buyNowTotalPrice = (Double) session.getAttribute("BuyNowNewTotal");
            BigDecimal BDCTotal = BigDecimal.valueOf(buyNowTotalPrice);

            String buyNowCouponCode = (String) session.getAttribute("buyNowCouponCode");
            Order order = new Order(userId, BDCTotal, buyNowCouponCode, now);
            int orderId = ordersDAO.addOrder(order);
            if (orderId > 0) {
                OrderDetailsDAO oDDAO = new OrderDetailsDAO();
                oDDAO.addOrderDetails(orderId, game.getGameId(), game.getPrice());
            }
            cDAO.decrementUsageLimit(buyNowCouponCode);
            System.out.println("succsess payment");
            response.sendRedirect("home");
        }

        if ("complete".equals(action)) {
            int userId = (Integer) session.getAttribute("userId"); // Lấy userId từ session
            CouponDAO cDAO = new CouponDAO();
            CartDAO cartDAO = new CartDAO();
            OrderDAO ordersDAO = new OrderDAO();
            Double totalPrice = (Double) session.getAttribute("totalPrice");
            String couponCodeC = (String) session.getAttribute("couponCode");
            List<Game> cartItems = cartDAO.getGamesInCartByUserId(userId);
            if (cartItems == null || cartItems.isEmpty()) {
                response.sendRedirect("/cart?error=empty");
                return;
            }
            BigDecimal BDCTotal = BigDecimal.valueOf(totalPrice);
            LocalDateTime now = LocalDateTime.now();

            Order order = new Order(userId, BDCTotal, couponCodeC, now);
            int orderId = ordersDAO.addOrder(order);
            if (orderId > 0) {
                for (Game game : cartItems) {
                    OrderDetailsDAO oDDAO = new OrderDetailsDAO();
                    oDDAO.addOrderDetails(orderId, game.getGameId(), game.getPrice());
                    System.out.println(game.getGameId());
                    System.out.println(orderId);
                    System.out.println(game.getPrice());
                    System.out.println("Succsess add game to order details");
                }
                cDAO.decrementUsageLimit(couponCodeC);
                cartDAO.clearCart(userId);
                System.out.println("succsess payment");
                response.sendRedirect("home");
            } else {
                response.sendRedirect("/cart?error=payment_failed");
            }

        }
    }
}
