/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameshop.DAO;

import gameshop.db.DBContext;
import gameshop.model.AdminUser;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Data Access Object (DAO) class for managing user-related database operations.
 *
 * @author Pham Van Hoai - CE181744
 */
public class AdminUserDAO extends DBContext {

    /**
     * Retrieves a paginated list of users with optional search by username.
     *
     * @param searchQuery The search term to filter users by username
     * (nullable).
     * @param offset The starting point for pagination.
     * @param limit The number of users to retrieve per page.
     * @return A list of AdminUser objects matching the search criteria.
     */
    public List<AdminUser> getAllAdminUsers(String searchQuery, int offset, int limit) {
        List<AdminUser> users = new ArrayList<>();
        // Base SQL query to fetch user details
        StringBuilder query = new StringBuilder(
                "SELECT user_id, username, email, role, status, last_login, created_at FROM Users WHERE 1=1"
        );

        // Add search condition if a search query is provided
        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            query.append(" AND username LIKE ?");
        }

        // Add pagination and sorting by user_id
        query.append(" ORDER BY user_id ASC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");

        try ( PreparedStatement ps = getConnection().prepareStatement(query.toString())) {
            int paramIndex = 1;

            // Set the search parameter if applicable
            if (searchQuery != null && !searchQuery.trim().isEmpty()) {
                ps.setString(paramIndex++, "%" + searchQuery.trim() + "%");
            }

            // Set pagination parameters
            ps.setInt(paramIndex++, offset);
            ps.setInt(paramIndex++, limit);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Create AdminUser object from result set and add to list
                users.add(new AdminUser(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("role"),
                        rs.getString("status"),
                        rs.getTimestamp("last_login"),
                        rs.getTimestamp("created_at")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

    /**
     * Counts the total number of users matching the search query.
     *
     * @param searchQuery The search term to filter users by username
     * (nullable).
     * @return The total count of users, or 0 if an error occurs.
     */
    public int countUsersByFilter(String searchQuery) {
        // Base SQL query to count users
        StringBuilder query = new StringBuilder("SELECT COUNT(*) FROM Users WHERE 1=1");

        // Add search condition if a search query is provided
        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            query.append(" AND username LIKE ?");
        }

        try ( PreparedStatement ps = getConnection().prepareStatement(query.toString())) {
            // Set the search parameter if applicable
            if (searchQuery != null && !searchQuery.trim().isEmpty()) {
                ps.setString(1, "%" + searchQuery.trim() + "%");
            }

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1); // Return the total count
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0; // Return 0 if an error occurs
    }

    /**
     * Deletes a user from the database by their ID.
     *
     * @param userId The ID of the user to delete.
     * @return True if the user is successfully deleted, false otherwise.
     */
    public boolean deleteUser(int userId) {
        // SQL query to delete a user by ID
        String query = "DELETE FROM Users WHERE user_id = ?";

        try ( PreparedStatement ps = getConnection().prepareStatement(query)) {
            ps.setInt(1, userId);
            return ps.executeUpdate() > 0; // Return true if at least one row is affected
        } catch (SQLException ex) {
            // Note: Logger references AdminCouponsDAO, which seems to be a typo; should be AdminUserDAO
            Logger.getLogger(AdminUserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
