/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameshop.security;

import gameshop.db.DBContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class handles tracking of failed login attempts, account lockout
 * mechanism, and the saving of the last login time for a user. It interacts
 * with the database to manage user login attempts and enforce security measures
 * such as account lockout.
 *
 * @author Le Anh Khoa - CE190449
 */
public class LoginAttemptTracker extends DBContext {

    private static final int LOCKOUT_DURATION = 15 * 60 * 1000; // Lock for 15 minutes

    /**
     * Tracks failed login attempts for the user. If the number of failed
     * attempts reaches 5, the account is locked for a specified duration.
     *
     * @param email the email address of the user
     * @return -1 if the account is locked, 0 if the login attempt was incorrect
     * but not locked
     */
    public int trackFailedLoginAttempt(String email) {
        String querySelect = "SELECT failed_attempts,\n"
                + "		last_failed_attempt,\n"
                + "		locked_until\n"
                + "FROM Users u\n"
                + "WHERE email = ?;";
        try ( ResultSet rs = execSelectQuery(querySelect, new Object[]{email})) { // Query into that specific account so that we can retrieve correctly
            if (rs.next()) { // If that email exist (The email must already be existed if it's called, but whatever)

                if (rs.getInt("failed_attempts") >= 5) { // get failed_attempts at the correct row at the correct account
                    lockAccount(email);
                    return -1; // Return -1 if the account is locked
                }

                // Only increment if not locked
                String queryUpdate = "UPDATE Users\n"
                        + "SET failed_attempts = ?,\n"
                        + "	last_failed_attempt = ?"
                        + "WHERE email = ?;";
                execQuery(queryUpdate, new Object[]{ // execQuery and save result to return later
                    rs.getInt("failed_attempts") + 1,
                    new Timestamp(System.currentTimeMillis()),
                    email
                });

                return 0; // Indicate wrong password but not locked
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginAttemptTracker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0; // Default case: Incorrect login (not locked)
    }

    /**
     * Resets the failed login attempt count for the user to 0, usually called
     * when the user logs in successfully.
     *
     * @param email the email address of the user
     * @return the number of rows affected in the database (typically 1 if
     * successful)
     */
    public int resetFailedAttempts(String email) {
        try {
            String query = "UPDATE Users \n"
                    + "SET failed_attempts = 0\n"
                    + "WHERE email = ?;";
            Object[] params = {email};

            return execQuery(query, params);
        } catch (SQLException ex) {
            Logger.getLogger(LoginAttemptTracker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    /**
     * Locks the user's account by setting the locked_until field to a future
     * timestamp. The account will remain locked for a specified duration to
     * prevent further login attempts.
     *
     * @param email the email address of the user whose account is to be locked
     * @return the number of rows affected in the database (typically 1 if
     * successful)
     */
    public int lockAccount(String email) {
        try {
            String query = "UPDATE Users \n"
                    + "SET locked_until = ?\n"
                    + "WHERE email = ?;";
            Object[] params = {
                new Timestamp(System.currentTimeMillis() + LOCKOUT_DURATION),
                email
            };

            return execQuery(query, params);
        } catch (SQLException ex) {
            Logger.getLogger(LoginAttemptTracker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    /**
     * Saves the last login timestamp for the user when they successfully log
     * in.
     *
     * @param email the email address of the user
     * @return the number of rows affected in the database (typically 1 if
     * successful)
     */
    public int saveLoginTime(String email) {
        try {
            String query = "UPDATE Users \n"
                    + "SET last_login = ?\n"
                    + "WHERE email = ?;";
            Object[] params = {
                new Timestamp(System.currentTimeMillis()),
                email
            };

            return execQuery(query, params);
        } catch (SQLException ex) {
            Logger.getLogger(LoginAttemptTracker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
