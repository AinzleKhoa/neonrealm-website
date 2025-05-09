/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameshop.DAO;

import gameshop.db.DBContext;
import gameshop.model.AdminOrders;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Access Object (DAO) class for managing order-related database
 * operations.
 *
 * @author Pham Van Hoai - CE181744
 */
public class AdminOrdersDAO extends DBContext {

    /**
     * Retrieves a paginated list of all orders with optional sorting by price.
     *
     * @param sortPrice The sorting order for price ("asc" for ascending, "desc"
     * for descending, null for default).
     * @param offset The starting point for pagination.
     * @param pageSize The number of orders to retrieve per page.
     * @return A list of AdminOrders objects containing order details.
     */
    public List<AdminOrders> getAllOrders(String sortPrice, int offset, int pageSize) {
        List<AdminOrders> orders = new ArrayList<>();
        try {
            // Base SQL query to fetch order details with related user and game information
            String query = "SELECT \n"
                    + "    od.order_detail_id,\n"
                    + "    o.user_id,\n"
                    + "    od.order_id,\n"
                    + "    u.username,\n"
                    + "    o.discount_code,\n"
                    + "    o.created_at,\n"
                    + "    g.game_id,\n"
                    + "    g.title,\n"
                    + "    od.price\n" // Fetch price from Order_Details
                    + "FROM Order_Details od\n"
                    + "JOIN Orders o ON od.order_id = o.order_id\n"
                    + "JOIN Users u ON o.user_id = u.user_id\n"
                    + "JOIN Games g ON od.game_id = g.game_id\n";

            // Apply sorting based on the sortPrice parameter
            if ("asc".equals(sortPrice)) {
                query += "ORDER BY od.price ASC, o.created_at DESC\n"; // Sort by price ascending, then by creation date descending
            } else if ("desc".equals(sortPrice)) {
                query += "ORDER BY od.price DESC, o.created_at DESC\n"; // Sort by price descending, then by creation date descending
            } else {
                query += "ORDER BY o.order_id ASC\n"; // Default sorting by order_id ascending
            }

            // Add pagination to limit the result set
            query += "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";

            try ( PreparedStatement ps = getConnection().prepareStatement(query)) {
                // Set pagination parameters
                ps.setInt(1, offset);
                ps.setInt(2, pageSize);
                ResultSet rs = ps.executeQuery();

                // Iterate through the result set and populate the orders list
                while (rs.next()) {
                    AdminOrders order = new AdminOrders(
                            rs.getInt("order_detail_id"),
                            rs.getInt("user_id"),
                            rs.getInt("order_id"),
                            rs.getString("username"),
                            rs.getBigDecimal("price"), // Price retrieved from Order_Details
                            rs.getString("discount_code"),
                            rs.getTimestamp("created_at"),
                            rs.getInt("game_id"),
                            rs.getString("title")
                    );
                    orders.add(order);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminOrdersDAO.class.getName()).log(Level.SEVERE, "Error fetching all orders", ex);
        }
        return orders;
    }

    /**
     * Counts the total number of order details in the database.
     *
     * @return The total count of order details, or 0 if an error occurs.
     */
    public int countTotalOrders() {
        // SQL query to count the total number of rows in Order_Details
        String query = "SELECT COUNT(*) FROM Order_Details";
        try ( PreparedStatement ps = getConnection().prepareStatement(query);  ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1); // Return the total count
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminOrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0; // Return 0 if an error occurs
    }
}
