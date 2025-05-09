/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameshop.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Model class representing an order entity for admin operations in the game
 * shop system.
 *
 * @author Pham Van Hoai - CE181744
 */
public class AdminOrders {

    private int order_detail_id;   // Unique identifier for the order detail
    private int userId;            // Identifier of the user who placed the order
    private int order_id;          // Unique identifier for the order
    private String username;       // Username of the buyer
    private BigDecimal totalPrice; // Total price of the order detail
    private String discountCode;   // Discount code applied to the order (if any)
    private Timestamp createdAt;   // Timestamp of when the order was created
    private int gameId;            // Identifier of the game in the order
    private String gameTitle;      // Title of the game in the order

    /**
     * Default no-argument constructor for creating an empty AdminOrders object.
     */
    public AdminOrders() {
    }

    /**
     * Parameterized constructor to initialize an AdminOrders object with all
     * fields.
     *
     * @param order_detail_id The unique ID of the order detail
     * @param userId The ID of the user who placed the order
     * @param order_id The unique ID of the order
     * @param username The username of the buyer
     * @param totalPrice The total price of the order detail
     * @param discountCode The discount code applied (if any)
     * @param createdAt The timestamp of order creation
     * @param gameId The ID of the game in the order
     * @param gameTitle The title of the game in the order
     */
    public AdminOrders(int order_detail_id, int userId, int order_id, String username, BigDecimal totalPrice, String discountCode, Timestamp createdAt, int gameId, String gameTitle) {
        this.order_detail_id = order_detail_id;
        this.userId = userId;
        this.order_id = order_id;
        this.username = username;
        this.totalPrice = totalPrice;
        this.discountCode = discountCode;
        this.createdAt = createdAt;
        this.gameId = gameId;
        this.gameTitle = gameTitle;
    }

    /**
     * Gets the order detail ID.
     *
     * @return The unique identifier of the order detail
     */
    public int getOrder_detail_id() {
        return order_detail_id;
    }

    /**
     * Sets the order detail ID.
     *
     * @param order_detail_id The unique identifier to set
     */
    public void setOrder_detail_id(int order_detail_id) {
        this.order_detail_id = order_detail_id;
    }

    /**
     * Gets the user ID.
     *
     * @return The identifier of the user who placed the order
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user ID.
     *
     * @param userId The user identifier to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the order ID.
     *
     * @return The unique identifier of the order
     */
    public int getOrder_id() {
        return order_id;
    }

    /**
     * Sets the order ID.
     *
     * @param order_id The order identifier to set
     */
    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    /**
     * Gets the username of the buyer.
     *
     * @return The username of the buyer
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the buyer.
     *
     * @param username The username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the total price of the order detail.
     *
     * @return The total price
     */
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    /**
     * Sets the total price of the order detail.
     *
     * @param totalPrice The total price to set
     */
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * Gets the discount code applied to the order.
     *
     * @return The discount code (if any)
     */
    public String getDiscountCode() {
        return discountCode;
    }

    /**
     * Sets the discount code for the order.
     *
     * @param discountCode The discount code to set
     */
    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    /**
     * Gets the creation timestamp of the order.
     *
     * @return The timestamp when the order was created
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation timestamp of the order.
     *
     * @param createdAt The timestamp to set for creation
     */
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the game ID in the order.
     *
     * @return The identifier of the game
     */
    public int getGameId() {
        return gameId;
    }

    /**
     * Sets the game ID in the order.
     *
     * @param gameId The game identifier to set
     */
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    /**
     * Gets the title of the game in the order.
     *
     * @return The title of the game
     */
    public String getGameTitle() {
        return gameTitle;
    }

    /**
     * Sets the title of the game in the order.
     *
     * @param gameTitle The game title to set
     */
    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }
}
