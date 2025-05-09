package gameshop.model;

import java.time.LocalDateTime;

/**
 * Account class represents a user account in the system.
 * It holds account details such as email, password hash, reset token,
 * and token expiry information.
 * 
 * @author CE171450 - Nguyen Hai Nam
 */
public class Account {

    private String email;
    private String passwordHash;
    private String resetToken;
    private LocalDateTime tokenExpiry;

    /**
     * Gets the email associated with the account.
     * 
     * @return the email of the account
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email for the account.
     * 
     * @param email the email to set for the account
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the password hash associated with the account.
     * 
     * @return the password hash of the account
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * Sets the password hash for the account.
     * 
     * @param passwordHash the password hash to set for the account
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * Gets the reset token for the account, used for password reset functionality.
     * 
     * @return the reset token for the account
     */
    public String getResetToken() {
        return resetToken;
    }

    /**
     * Sets the reset token for the account.
     * 
     * @param resetToken the reset token to set for the account
     */
    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    /**
     * Gets the token expiry date and time for the reset token.
     * 
     * @return the expiry date and time of the reset token
     */
    public LocalDateTime getTokenExpiry() {
        return tokenExpiry;
    }

    /**
     * Sets the token expiry date and time for the reset token.
     * 
     * @param tokenExpiry the expiry date and time to set for the reset token
     */
    public void setTokenExpiry(LocalDateTime tokenExpiry) {
        this.tokenExpiry = tokenExpiry;
    }
}
