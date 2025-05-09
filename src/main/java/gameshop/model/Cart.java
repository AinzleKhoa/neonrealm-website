package gameshop.model;

import java.sql.Timestamp;

/**
 * Cart class represents a user's shopping cart. It stores the cart ID, user ID,
 * game ID, and the timestamp when the cart was created.
 *
 * @author CE180035 - Nguyen Huynh Nhat Thien
 */
public class Cart {

    private int cartId;
    private int userId;
    private int gameId;
    private Timestamp createdAt;

    /**
     * Constructor for creating a Cart object with all necessary details.
     *
     * @param cartId the unique identifier for the cart
     * @param userId the unique identifier for the user
     * @param gameId the unique identifier for the game
     * @param createdAt the timestamp when the cart was created
     */
    public Cart(int cartId, int userId, int gameId, Timestamp createdAt) {
        this.cartId = cartId;
        this.userId = userId;
        this.gameId = gameId;
        this.createdAt = createdAt;
    }

    /**
     * Gets the unique identifier for the cart.
     *
     * @return the cart ID
     */
    public int getCartId() {
        return cartId;
    }

    /**
     * Gets the unique identifier for the user associated with the cart.
     *
     * @return the user ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Gets the unique identifier for the game associated with the cart.
     *
     * @return the game ID
     */
    public int getGameId() {
        return gameId;
    }

    /**
     * Gets the timestamp indicating when the cart was created.
     *
     * @return the creation timestamp of the cart
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }
}
