package gameshop.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Order class represents an order made by a user in the system. It contains
 * details such as the order ID, user ID, total price, discount code, and the
 * creation timestamp of the order.
 *
 * @author Le Anh Khoa - CE190449
 */
public class Order {

    private int orderId;
    private int userId;
    private BigDecimal totalPrice;
    private String discountCode;
    private LocalDateTime createdAt;

    /**
     * Constructor for creating an Order object with all necessary details.
     *
     * @param orderId the unique identifier for the order
     * @param userId the unique identifier for the user who made the order
     * @param totalPrice the total price of the order
     * @param discountCode the discount code applied to the order (if any)
     * @param createdAt the timestamp when the order was created
     */
    public Order(int orderId, int userId, BigDecimal totalPrice, String discountCode, LocalDateTime createdAt) {
        this.orderId = orderId;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.discountCode = discountCode;
        this.createdAt = createdAt;
    }

    /**
     * Constructor for creating an Order object without the order ID.
     *
     * @param userId the unique identifier for the user who made the order
     * @param totalPrice the total price of the order
     * @param discountCode the discount code applied to the order (if any)
     * @param createdAt the timestamp when the order was created
     */
    public Order(int userId, BigDecimal totalPrice, String discountCode, LocalDateTime createdAt) {
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.discountCode = discountCode;
        this.createdAt = createdAt;
    }

    /**
     * Gets the unique identifier for the order.
     *
     * @return the order ID
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * Sets the unique identifier for the order.
     *
     * @param orderId the order ID to set
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * Gets the unique identifier for the user associated with the order.
     *
     * @return the user ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the unique identifier for the user associated with the order.
     *
     * @param userId the user ID to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the total price of the order.
     *
     * @return the total price of the order
     */
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    /**
     * Sets the total price of the order.
     *
     * @param totalPrice the total price to set
     */
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * Gets the discount code applied to the order.
     *
     * @return the discount code applied to the order
     */
    public String getDiscountCode() {
        return discountCode;
    }

    /**
     * Sets the discount code applied to the order.
     *
     * @param discountCode the discount code to set
     */
    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    /**
     * Gets the creation timestamp of the order.
     *
     * @return the timestamp when the order was created
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation timestamp of the order.
     *
     * @param createdAt the timestamp to set
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
