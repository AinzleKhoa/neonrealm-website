/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package gameshop.controller;

import gameshop.DAO.CartDAO;
import gameshop.DAO.GameDAO;
import gameshop.model.Cart;
import gameshop.model.Game;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author Le Anh Khoa - CE190449
 */
public class HomeServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP GET method for displaying the home page. This method
     * retrieves a list of games from the database and forwards the request to
     * the home page.
     *
     * @param request the servlet request
     * @param response the servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        GameDAO gDAO = new GameDAO();
        List<Game> gameList = gDAO.getGameList();

        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (gameList == null || gameList.isEmpty()) {
            response.sendRedirect("/error?error=Couldn't retrieve game list from home database");
            return;
        } else {
            request.setAttribute("gameList", gameList);
        }

        //Import totalPrice and Count items         
        if (userId != null) {
            CartDAO cartDAO = new CartDAO();
            List<Cart> cartList = cartDAO.getCartByUserId(userId);
            double totalPrice = cartDAO.getTotalCartPrice(userId);
            session.setAttribute("cartList", cartList);
            session.setAttribute("cartCount", cartList.size());
            //session.setAttribute("cartCount", cartCount);
            session.setAttribute("totalPrice", totalPrice);
        } else {
            session.setAttribute("cartList", 0);
            session.setAttribute("cartCount", 0);
            session.setAttribute("totalPrice", 0.00);

        }

        request.getRequestDispatcher("/WEB-INF/pages/home.jsp")
                .forward(request, response);
    }

    /**
     * Handles the HTTP POST method for the home page. This method is currently
     * not implemented.
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
