package gameshop.DAO;

import gameshop.db.DBContext;
import gameshop.model.Cart;
import gameshop.model.Game;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * CartDAO class is responsible for handling database operations related to the
 * shopping cart. It provides methods to retrieve, add, remove, and clear items
 * in the cart.
 *
 * @author CE180035 - Nguyen Huynh Nhat Thien
 */
public class CartDAO extends DBContext {

    /**
     * Clears all items from the cart of the specified user.
     *
     * @param userId the ID of the user whose cart should be cleared
     * @return true if at least one row was deleted, false otherwise
     */
    public boolean clearCart(int userId) {
        String query = "DELETE FROM Cart WHERE user_id = ?";
        try ( Connection connection = getConnection();  PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, userId);
            return stmt.executeUpdate() > 0; // Trả về true nếu có ít nhất một hàng bị xóa

        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, "Error clearing cart", ex);
            return false;
        }
    }

    /**
     * Retrieves the total price of all items in the cart across all users.
     *
     * @return the total price of all items in the cart
     */
    public double getAllTotalCartPrice() {
        double totalPrice = 0.0;
        String sql = "SELECT SUM(g.price) AS total FROM Cart c JOIN Games g ON c.game_id = g.game_id";
        try {
            PreparedStatement ps = this.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                totalPrice = rs.getDouble("total");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalPrice;
    }

    /**
     * Retrieves the total price of items in the cart for a specific user.
     *
     * @param userId the ID of the user whose cart price is to be retrieved
     * @return the total price of items in the user's cart
     */
    public double getTotalCartPrice(int userId) {
        double totalPrice = 0;
        String sql = "SELECT SUM(g.price) AS total FROM Cart c JOIN Games g ON c.game_id = g.game_id WHERE c.user_id = ?";
        try {
            PreparedStatement ps = this.getConnection().prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                totalPrice = rs.getDouble("total");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalPrice;
    }

    /**
     * Retrieves all carts from the database.
     *
     * @return a list of Cart objects representing all carts in the database
     */
    public ArrayList<Cart> getAllCarts() {
        try {
            ArrayList<Cart> cartList = new ArrayList<>();
            String query = "SELECT * FROM Cart";
            PreparedStatement pStatement = this.getConnection().prepareStatement(query);
            ResultSet rs = pStatement.executeQuery();

            while (rs.next()) {
                cartList.add(new Cart(
                        rs.getInt("cart_id"),
                        rs.getInt("user_id"),
                        rs.getInt("game_id"),
                        rs.getTimestamp("created_at")
                ));
            }
            return cartList;
        } catch (SQLException e) {
        }
        return null;
    }

    /**
     * Retrieves all games in the cart for a specific user.
     *
     * @param userId the ID of the user whose cart games are to be retrieved
     * @return a list of Game objects in the user's cart
     */
    public List<Game> getGamesInCartByUserId(int userId) {
        List<Game> games = new ArrayList<>();
        String sql = "SELECT g.* FROM Cart c JOIN Games g ON c.game_id = g.game_id WHERE c.user_id = ?";
        try ( PreparedStatement ps = this.getConnection().prepareStatement(sql)) {
            ps.setInt(1, userId);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int gameId = rs.getInt("game_id");
                    List<String> platforms = getPlatformsByGameId(gameId);
                    Game game = new Game(
                            gameId,
                            rs.getString("title"),
                            rs.getString("description"),
                            rs.getString("image_url"),
                            rs.getBigDecimal("price"),
                            null,
                            null, // createdAt (nếu cần, bạn có thể lấy `rs.getDate("created_at").toLocalDate()`)
                            null, null, null, platforms, null // Developers, publishers, genres, platforms, categories
                    );
                    games.add(game);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return games;
    }

    /**
     * Retrieves the list of platforms for a specific game.
     *
     * @param gameId the ID of the game whose platforms are to be retrieved
     * @return a list of platform names for the specified game
     */
    private List<String> getPlatformsByGameId(int gameId) {
        List<String> platforms = new ArrayList<>();
        String sql = "SELECT p.name FROM Game_Platforms gp JOIN Platforms p ON gp.platform_id = p.platform_id WHERE gp.game_id = ?";

        try ( PreparedStatement ps = this.getConnection().prepareStatement(sql)) {
            ps.setInt(1, gameId);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    platforms.add(rs.getString("name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return platforms;
    }

    /**
     * Retrieves all items in the cart for a specific user.
     *
     * @param userId the ID of the user whose cart items are to be retrieved
     * @return a list of Cart objects representing the user's cart
     */
    public List<Cart> getCartByUserId(int userId) {
        List<Cart> cartList = new ArrayList<>();
        String sql = "SELECT * FROM Cart WHERE user_id = ?";
        try ( PreparedStatement ps = this.getConnection().prepareStatement(sql)) {
            ps.setInt(1, userId);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    cartList.add(new Cart(
                            rs.getInt("cart_id"),
                            rs.getInt("user_id"),
                            rs.getInt("game_id"),
                            rs.getTimestamp("created_at")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartList;
    }

    /**
     * Checks if a specific game is already in the user's cart.
     *
     * @param userId the ID of the user
     * @param gameId the ID of the game
     * @return true if the game is already in the cart, false otherwise
     */
    public boolean isGameInCart(int userId, int gameId) {
        String sql = "SELECT COUNT(*) FROM Cart WHERE user_id = ? AND game_id = ?";
        try ( PreparedStatement ps = this.getConnection().prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, gameId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Trả về true nếu có ít nhất 1 kết quả
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Adds a game to the user's cart.
     *
     * @param userId the ID of the user
     * @param gameId the ID of the game to be added
     * @return true if the game was successfully added to the cart, false
     * otherwise
     */
    public boolean addToCart(int userId, int gameId) {
        String insertSql = "INSERT INTO Cart (user_id, game_id, created_at) VALUES (?, ?, GETDATE())";
        try ( PreparedStatement insertStmt = this.getConnection().prepareStatement(insertSql)) {
            insertStmt.setInt(1, userId);
            insertStmt.setInt(2, gameId);
            int rowsInserted = insertStmt.executeUpdate();
            return rowsInserted > 0; // Trả về true nếu thêm thành công  
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Removes a specific game from the user's cart.
     *
     * @param userId the ID of the user
     * @param gameId the ID of the game to be removed
     * @return true if the game was successfully removed from the cart, false
     * otherwise
     */
    public boolean removeFromCart(int userId, int gameId) {
        String sql = "DELETE FROM Cart WHERE user_id = ? AND game_id = ?";
        try ( PreparedStatement ps = this.getConnection().prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, gameId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu có ít nhất 1 dòng bị xóa
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Trả về false nếu có lỗi xảy ra
        }
    }

    /**
     * Clears all items from the cart of the specified user.
     *
     * @param userId the ID of the user whose cart should be cleared
     */
    public void clearCartByUserId(int userId) {
        String sql = "DELETE FROM Cart WHERE user_id = ?";
        try ( PreparedStatement ps = this.getConnection().prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
