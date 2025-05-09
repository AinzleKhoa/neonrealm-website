package gameshop.model;

import java.math.BigDecimal;

/**
 * OrderDetails class represents the details of an order, including the specific
 * game ordered, the price at the time of purchase, and the associated order. It
 * establishes a many-to-one relationship with both the Order and Game classes.
 *
 * @author Le Anh Khoa - CE190449
 */
public class OrderDetails {

    private int orderDetailId;
    private Order order;  // Many-to-One relationship with Order
    private Game game;    // Many-to-One relationship with Game
    private BigDecimal price; // The price of the game at the time of purchase
    private int orderId;
    private int gameId;

    /**
     * Constructor to create an OrderDetails object with all necessary details.
     *
     * @param orderDetailId the unique identifier for the order detail
     * @param order the associated Order object
     * @param game the associated Game object
     * @param price the price of the game at the time of purchase
     */
    public OrderDetails(int orderDetailId, Order order, Game game, BigDecimal price) {
        this.orderDetailId = orderDetailId;
        this.order = order;
        this.game = game;
        this.price = price;
    }

    /**
     * Constructor to create an OrderDetails object without the order detail ID.
     *
     * @param order the associated Order object
     * @param game the associated Game object
     * @param price the price of the game at the time of purchase
     */
    public OrderDetails(Order order, Game game, BigDecimal price) {
        this.order = order;
        this.game = game;
        this.price = price;
    }

    /**
     * Constructor to create an OrderDetails object using order ID, game ID, and
     * price.
     *
     * @param orderId the ID of the associated order
     * @param gameId the ID of the associated game
     * @param price the price of the game at the time of purchase
     */
    public OrderDetails(int orderId, int gameId, BigDecimal price) {
        this.orderId = orderId;
        this.gameId = gameId;
        this.price = price;
    }

    /**
     * Gets the unique identifier for the order detail.
     *
     * @return the order detail ID
     */
    public int getOrderDetailId() {
        return orderDetailId;
    }

    /**
     * Sets the unique identifier for the order detail.
     *
     * @param orderDetailId the order detail ID to set
     */
    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    /**
     * Gets the associated Order object.
     *
     * @return the associated Order object
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Sets the associated Order object.
     *
     * @param order the Order object to set
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * Gets the associated Game object.
     *
     * @return the associated Game object
     */
    public Game getGame() {
        return game;
    }

    /**
     * Sets the associated Game object.
     *
     * @param game the Game object to set
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Gets the price of the game at the time of purchase.
     *
     * @return the price of the game at the time of purchase
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the price of the game at the time of purchase.
     *
     * @param price the price to set for the game
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
