/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameshop.DAO;

import gameshop.db.DBContext;
import gameshop.model.Game;
import gameshop.model.Order;
import gameshop.model.OrderDetails;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * OrderDAO class is responsible for handling database operations related to
 * orders
 *
 * @author CE190449 - Le Anh Khoa
 */
public class OrderDAO extends DBContext {

    /**
     * Retrieves the order history for a specific user.
     *
     * @param userId the user ID.
     * @return a List of OrderDetails objects containing the order history for
     * the specified user.
     */
    public List<OrderDetails> getOrderHistory(int userId) {
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        try {
            String query = "SELECT \n"
                    + "		od.order_detail_id,\n"
                    + "		g.game_id,\n"
                    + "		o.user_id,\n"
                    + "		o.order_id,\n"
                    + "		g.image_url,\n"
                    + "		g.title,\n"
                    + "		o.created_at,\n"
                    + "		od.price\n"
                    + "FROM Order_Details od\n"
                    + "JOIN Orders o ON od.order_id = o.order_id\n"
                    + "JOIN Games g ON od.game_id = g.game_id\n"
                    + "WHERE o.user_id = ?\n"
                    + "ORDER BY o.created_at DESC;";
            Object[] params = {userId};
            ResultSet rs = execSelectQuery(query, params);

            while (rs.next()) {
                LocalDateTime createdAt = (rs.getTimestamp("created_at") != null)
                        ? rs.getTimestamp("created_at").toLocalDateTime()
                        : null;

                OrderDetails orderDetail = new OrderDetails(
                        rs.getInt("order_detail_id"), // OrderDetail ID
                        new Order(
                                rs.getInt("order_id"),
                                userId,
                                null, // Total order price is not fetched in query
                                null, // Assuming discountCode is not fetched in query
                                createdAt // Order Date
                        ),
                        new Game(
                                rs.getInt("game_id"),
                                rs.getString("title"),
                                null, // Description not retrieved in query
                                rs.getString("image_url"),
                                null, // Game price not fetched in query
                                null, // Release date not fetched in query
                                null, // Order creation date (used as purchase date)
                                null, // Developers
                                null, // Publishers
                                null, // Genres
                                null, // Platforms
                                null // Categories
                        ),
                        rs.getBigDecimal("price") // Price at the time of purchase
                );
                System.out.println("Fetched Order: " + orderDetail); // DEBUGGING LINE
                orderDetailsList.add(orderDetail);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderDetailsList;
    }

    public int addOrder(Order order) {
        int orderId = -1; // Giá trị mặc định nếu không thêm được
        String query = "INSERT INTO Orders (user_id, total_price, discount_code, created_at) VALUES (?, ?, ?, ?)";

        try ( Connection connection = getConnection();  PreparedStatement ps = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, order.getUserId());
            ps.setBigDecimal(2, order.getTotalPrice());
            ps.setString(3, order.getDiscountCode());
            ps.setObject(4, order.getCreatedAt()); // LocalDateTime

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    orderId = rs.getInt(1);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, "Error inserting order", ex);
        }

        return orderId;
    }
}
