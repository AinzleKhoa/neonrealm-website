/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameshop.model;

import java.time.Instant;

/**
 * Represents a user in the system. This class contains all the information
 * related to a user, including authentication details, user status, and other
 * related attributes.
 *
 * @author Le Anh Khoa - CE190449
 */
public class User {

    private int userId;
    private String username;
    private String email;
    private String hashedPassword;
    private String githubId;
    private String authProvider; // "local" or "github"
    private String role = "user";
    private String status = "active";
    private String rememberMeToken;
    private Instant lastLogin;
    private Instant createdAt = Instant.now();

    /**
     * Default constructor.
     */
    public User() {
    }

    /**
     * Constructor for debugging login issues.
     *
     * @param userId the user ID
     * @param username the username
     */
    public User(int userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    /**
     * Constructor for login locally.
     *
     * @param email the user's email
     * @param hashedPassword the hashed password of the user
     */
    public User(String email, String hashedPassword) {
        this.email = email;
        this.hashedPassword = hashedPassword;
    }

    /**
     * Constructor for signup locally and provider login/signup.
     *
     * @param username the username
     * @param email the email
     * @param hashedPassword the hashed password
     * @param githubId the GitHub ID (for GitHub login)
     * @param authProvider the authentication provider (e.g., "local" or
     * "github")
     */
    public User(String username, String email, String hashedPassword, String githubId, String authProvider) {
        this.username = username;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.githubId = githubId;
        this.authProvider = authProvider;
    }

    /**
     * Constructor for retrieving all user data during login.
     *
     * @param userId the user ID
     * @param username the username
     * @param email the email
     * @param hashedPassword the hashed password
     * @param githubId the GitHub ID
     * @param authProvider the authentication provider
     * @param role the role of the user
     * @param status the status of the user
     * @param rememberMeToken the avatar URL of the user
     * @param lastLogin the last login timestamp
     * @param createdAt the account creation timestamp
     */
    public User(int userId, String username, String email, String hashedPassword, String githubId, String authProvider, String role, String status, String rememberMeToken, Instant lastLogin, Instant createdAt) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.githubId = githubId;
        this.authProvider = authProvider;
        this.role = role;
        this.status = status;
        this.rememberMeToken = rememberMeToken;
        this.lastLogin = lastLogin;
        this.createdAt = createdAt;
    }

    public User(String email, String username, String authProvider) {
        this.email = email;
        this.username = username;
        this.authProvider = authProvider;
    }

    /**
     * Gets the user ID.
     *
     * @return the user ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user ID.
     *
     * @param userId the user ID to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     *
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the hashed password.
     *
     * @return the hashed password
     */
    public String getHashedPassword() {
        return hashedPassword;
    }

    /**
     * Sets the hashed password.
     *
     * @param hashedPassword the hashed password to set
     */
    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    /**
     * Gets the GitHub ID.
     *
     * @return the GitHub ID
     */
    public String getGithubId() {
        return githubId;
    }

    /**
     * Sets the GitHub ID.
     *
     * @param githubId the GitHub ID to set
     */
    public void setGithubId(String githubId) {
        this.githubId = githubId;
    }

    /**
     * Gets the authentication provider.
     *
     * @return the authentication provider
     */
    public String getAuthProvider() {
        return authProvider;
    }

    /**
     * Sets the authentication provider.
     *
     * @param authProvider the authentication provider to set
     */
    public void setAuthProvider(String authProvider) {
        this.authProvider = authProvider;
    }

    /**
     * Gets the role of the user.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role of the user.
     *
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Gets the status of the user.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the user.
     *
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the rememberMeToken.
     *
     * @return the avatar URL
     */
    public String getRememberMeToken() {
        return rememberMeToken;
    }

    /**
     * Sets the rememberMeToken.
     *
     * @param rememberMeToken the avatar URL to set
     */
    public void setRememberMeToken(String rememberMeToken) {
        this.rememberMeToken = rememberMeToken;
    }

    /**
     * Gets the last login timestamp.
     *
     * @return the last login timestamp
     */
    public Instant getLastLogin() {
        return lastLogin;
    }

    /**
     * Sets the last login timestamp.
     *
     * @param lastLogin the last login timestamp to set
     */
    public void setLastLogin(Instant lastLogin) {
        this.lastLogin = lastLogin;
    }

    /**
     * Gets the account creation timestamp.
     *
     * @return the account creation timestamp
     */
    public Instant getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the account creation timestamp.
     *
     * @param createdAt the account creation timestamp to set
     */
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

}
