/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package gameshop.controller;

import gameshop.DAO.AdminCouponsDAO;
import gameshop.model.AdminCoupons;
import gameshop.util.SessionUtil;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet for managing admin coupon operations such as listing, adding,
 * editing, and deleting coupons.
 *
 * @author Pham Van Hoai - CE181744
 */
@WebServlet(name = "AdminCouponsServlet", urlPatterns = {"/admin/coupons"})
public class AdminCouponsServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method to display the coupon list, add
     * coupon form, or edit coupon form.
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

        AdminCouponsDAO adminCouponsDAO = new AdminCouponsDAO();

        // Handle request to display the add coupon form
        if (request.getParameter("add") != null) {
            request.getRequestDispatcher("/admin/add-coupon.jsp").forward(request, response);
            return;
        }

        // Handle request to edit a specific coupon
        String editId = request.getParameter("editId");
        if (editId != null) {
            try {
                int couponId = Integer.parseInt(editId);
                AdminCoupons coupon = adminCouponsDAO.getCouponById(couponId);

                // Redirect to 404 page if the coupon is not found
                if (coupon == null) {
                    response.sendRedirect(request.getContextPath() + "/pages/404.jsp");
                    return;
                }

                // Set coupon data and forward to edit page
                request.setAttribute("coupon", coupon);
                request.getRequestDispatcher("/admin/edit-coupon.jsp").forward(request, response);
                return;
            } catch (NumberFormatException e) {
                // Redirect to coupon list if editId is invalid
                response.sendRedirect(request.getContextPath() + "/admin/coupons");
                return;
            }
        }

        // Default action: fetch and display the list of all coupons
        List<AdminCoupons> coupons = adminCouponsDAO.getAllCoupons();
        request.setAttribute("coupons", coupons);
        request.getRequestDispatcher("/admin/coupons.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method to process actions like adding,
     * editing, or deleting coupons.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        AdminCouponsDAO adminCouponsDAO = new AdminCouponsDAO();

        try {
            if ("delete".equals(action)) {
                // Delete a coupon by its ID
                int couponId = Integer.parseInt(request.getParameter("couponId"));
                adminCouponsDAO.deleteCoupon(couponId);
            } else if ("add".equals(action)) {
                // Add a new coupon
                String code = request.getParameter("code");
                int discount = Integer.parseInt(request.getParameter("discount"));
                String expiration = request.getParameter("expiration");
                int usageLimit = Integer.parseInt(request.getParameter("usageLimit"));

                // Validate input data
                if (code == null || code.isEmpty() || discount < 1 || discount > 100 || expiration.isEmpty() || usageLimit < 1) {
                    request.setAttribute("error", "Please enter valid information!");
                    request.getSession().setAttribute("successMessage", "Add coupon successfully!"); // Note: This seems misplaced; likely intended for success case
                    response.sendRedirect(request.getContextPath() + "/admin/add-coupon");
                    return;
                }

                // Create and add the new coupon
                AdminCoupons coupon = new AdminCoupons(0, code, discount, Date.valueOf(expiration), usageLimit, null);
                adminCouponsDAO.addCoupon(coupon);
            } else if ("edit".equals(action)) {
                // Edit an existing coupon
                int couponId = Integer.parseInt(request.getParameter("couponId"));
                String code = request.getParameter("code");
                int discount = Integer.parseInt(request.getParameter("discount_percentage"));
                String expiration = request.getParameter("expiration_date");
                int usageLimit = Integer.parseInt(request.getParameter("usage_limit"));

                // Validate input data
                if (code == null || code.isEmpty() || discount < 1 || discount > 100 || expiration.isEmpty() || usageLimit < 1) {
                    request.setAttribute("error", "Please enter valid information!");
                    request.getRequestDispatcher("/admin/edit-coupon.jsp").forward(request, response);
                    return;
                }

                // Update the coupon
                AdminCoupons coupon = new AdminCoupons(couponId, code, discount, Date.valueOf(expiration), usageLimit, null);
                adminCouponsDAO.updateCoupon(coupon);
            }
        } catch (Exception e) {
            // Handle any exceptions during processing
            request.setAttribute("error", "Error occurred during processing!");
        }

        // Redirect to the coupon list page after processing
        response.sendRedirect(request.getContextPath() + "/admin/coupons");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet for managing admin coupon operations";
    }// </editor-fold>
}
