/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package gameshop.controller;

import gameshop.DAO.AdminOrdersDAO;
import gameshop.model.AdminOrders;
import gameshop.util.SessionUtil;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Servlet for managing admin order operations, such as listing and paginating
 * orders.
 *
 * @author Pham Van Hoai - CE181744
 */
@WebServlet(name = "AdminOrdersServlet", urlPatterns = {"/admin/orders"})
public class AdminOrdersServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method to display a paginated list of
     * orders with optional sorting.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Check if the user is an admin; redirect to home if not
        if (!SessionUtil.isAdmin(request)) {
            response.sendRedirect(request.getContextPath() + "/pages/home.jsp");
            return;
        }

        AdminOrdersDAO adminOrdersDAO = new AdminOrdersDAO();

        // Retrieve sorting parameter (defaults to null if not provided)
        String sortPrice = request.getParameter("sortPrice");

        // Pagination setup
        int pageSize = 10; // Number of orders per page
        int currentPage = 1; // Default to page 1

        try {
            // Parse the current page from request parameter, if provided
            if (request.getParameter("page") != null && !request.getParameter("page").trim().isEmpty()) {
                currentPage = Integer.parseInt(request.getParameter("page"));
            }
        } catch (NumberFormatException e) {
            currentPage = 1; // Fallback to page 1 if parsing fails
        }

        int offset = (currentPage - 1) * pageSize; // Calculate offset for pagination

        // Fetch paginated and sorted list of orders
        List<AdminOrders> orders = adminOrdersDAO.getAllOrders(sortPrice, offset, pageSize);
        int totalOrders = adminOrdersDAO.countTotalOrders(); // Get total number of orders
        int totalPages = (int) Math.ceil((double) totalOrders / pageSize); // Calculate total pages

        // Calculate the current total orders displayed (full page size or remaining orders on the last page)
        int currentTotalOrders = (currentPage < totalPages ? pageSize * currentPage : totalOrders);
        request.setAttribute("currentTotalOrders", currentTotalOrders);

        // Set attributes for JSP rendering
        request.setAttribute("orders", orders);           // List of orders
        request.setAttribute("totalPages", totalPages);   // Total number of pages for pagination
        request.setAttribute("totalOrders", totalOrders); // Total number of orders
        request.setAttribute("currentPage", currentPage); // Current page number
        request.setAttribute("sortPrice", sortPrice);     // Sorting parameter for reuse in JSP

        // Forward request to the orders JSP page
        request.getRequestDispatcher("/admin/orders.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method. Currently unimplemented.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO: Implement POST handling logic if needed (e.g., order updates or deletions)
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Admin Orders Servlet for managing order operations";
    }// </editor-fold>
}
