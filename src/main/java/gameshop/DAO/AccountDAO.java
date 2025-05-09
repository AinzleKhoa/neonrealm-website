/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameshop.DAO;

import gameshop.db.DBContext;
import gameshop.model.Account;
import java.sql.*;
import java.time.LocalDateTime;

/**
 * AccountDAO class is responsible for handling database operations related to
 * user accounts. It provides methods to retrieve and update account information
 * based on email and token.
 *
 * @author CE171450 - Nguyen Hai Nam
 */
public class AccountDAO {

    private Connection conn;
    private DBContext dbContext;

    /**
     * Constructor for AccountDAO. Initializes the database connection using
     * DBContext.
     */
    public AccountDAO() {
        dbContext = new DBContext();
        conn = dbContext.getConnection(); // Lấy kết nối từ DBContext
    }

    /**
     * Retrieves an Account from the database by email.
     *
     * @param email the email of the account to retrieve
     * @return an Account object if found, or null if no account with the given
     * email exists
     */
    public Account getAccountByEmail(String email) {
        try {
            String query = "SELECT * FROM Users WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToAccount(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves an Account from the database by reset token.
     *
     * @param token the reset token associated with the account
     * @return an Account object if found, or null if no account with the given
     * token exists
     */
    public Account getAccountByToken(String token) {
        if (token == null || token.trim().isEmpty()) {
            System.out.println("⚠️ Token bị null hoặc rỗng!");
            return null;
        }

        try {
            String query = "SELECT * FROM Users WHERE reset_token = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, token);
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {
                System.out.println("⚠️ Không tìm thấy tài khoản với token: " + token);
                return null;
            }

            return mapResultSetToAccount(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Updates the reset token and its expiry time for the account with the
     * given email.
     *
     * @param email the email of the account to update
     * @param token the new reset token
     * @param expiryTime the expiry time of the reset token
     * @return true if the update was successful, false otherwise
     */
    public boolean updateResetToken(String email, String token, LocalDateTime expiryTime) {
        String query = "UPDATE Users SET reset_token = ?, token_expiry = ? WHERE email = ?";

        try ( PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, token);
            stmt.setTimestamp(2, Timestamp.valueOf(expiryTime));
            stmt.setString(3, email);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Nếu cập nhật thành công, trả về true
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Updates the account information, including password hash, reset token,
     * and token expiry.
     *
     * @param account the account object containing updated account information
     * @return true if the account was updated successfully, false otherwise
     */
    public boolean updateAccount(Account account) {
        try {
            String query = "UPDATE Users SET password_hash = ?, reset_token = ?, token_expiry = ? WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, account.getPasswordHash());
            stmt.setString(2, account.getResetToken());
            stmt.setTimestamp(3, account.getTokenExpiry() != null ? Timestamp.valueOf(account.getTokenExpiry()) : null);
            stmt.setString(4, account.getEmail());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Maps the ResultSet from the database query to an Account object.
     *
     * @param rs the ResultSet containing the account data
     * @return the Account object populated with data from the ResultSet
     * @throws SQLException if there is an error accessing the ResultSet
     */
    private Account mapResultSetToAccount(ResultSet rs) throws SQLException {
        Account account = new Account();
        account.setEmail(rs.getString("email"));
        account.setPasswordHash(rs.getString("password_hash"));
        account.setResetToken(rs.getString("reset_token"));
        account.setTokenExpiry(rs.getTimestamp("token_expiry") != null ? rs.getTimestamp("token_expiry").toLocalDateTime() : null);
        return account;
    }
}
