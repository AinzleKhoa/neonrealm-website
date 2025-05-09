/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameshop.model;

import java.sql.Timestamp;

/**
 * Model class representing a user entity for admin operations in the game shop
 * system.
 *
 * @author Pham Van Hoai - CE181744
 */
public class AdminUser {

    private int user_id;           // Unique identifier for the user
    private String username;       // Username of the user
    private String email;          // Email address of the user
    private String githubId;       // GitHub ID for users authenticated via GitHub (optional)
    private String auth_provider;  // Authentication provider ("local" or "github")
    private String role;           // Role of the user (e.g., "admin", "user")
    private String status;         // Status of the user account (e.g., "active", "inactive")
    private String avatarUrl;      // URL of the user's avatar image (optional)
    private Timestamp last_login;  // Timestamp of the user's last login
    private Timestamp created_at;  // Timestamp of when the user account was created

    /**
     * Constructor to initialize an AdminUser object with essential fields
     * including role and status.
     *
     * @param user_id The unique ID of the user
     * @param username The username of the user
     * @param email The email address of the user
     * @param role The role of the user
     * @param status The status of the user account
     * @param last_login The timestamp of the last login
     * @param created_at The timestamp of account creation
     */
    public AdminUser(int user_id, String username, String email, String role, String status, Timestamp last_login, Timestamp created_at) {
        this.user_id = user_id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.status = status;
        this.last_login = last_login;
        this.created_at = created_at;
    }

    /**
     * Constructor to initialize an AdminUser object with minimal fields.
     *
     * @param user_id The unique ID of the user
     * @param username The username of the user
     * @param email The email address of the user
     * @param created_at The timestamp of account creation
     */
    public AdminUser(int user_id, String username, String email, Timestamp created_at) {
        this.user_id = user_id;
        this.username = username;
        this.email = email;
        this.created_at = created_at;
    }

    /**
     * Constructor to initialize an AdminUser object with GitHub authentication
     * details.
     *
     * @param user_id The unique ID of the user
     * @param username The username of the user
     * @param email The email address of the user
     * @param githubId The GitHub ID of the user
     * @param authProvider The authentication provider ("local" or "github")
     * @param avatarUrl The URL of the user's avatar
     * @param last_login The timestamp of the last login
     * @param created_at The timestamp of account creation
     */
    public AdminUser(int user_id, String username, String email, String githubId, String authProvider, String avatarUrl, Timestamp last_login, Timestamp created_at) {
        this.user_id = user_id;
        this.username = username;
        this.email = email;
        this.githubId = githubId;
        this.auth_provider = authProvider;
        this.avatarUrl = avatarUrl;
        this.last_login = last_login;
        this.created_at = created_at;
    }

    /**
     * Default no-argument constructor for creating an empty AdminUser object.
     */
    public AdminUser() {
    }

    /**
     * Gets the user ID.
     *
     * @return The unique identifier of the user
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     * Sets the user ID.
     *
     * @param user_id The unique identifier to set
     */
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    /**
     * Gets the username.
     *
     * @return The username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param username The username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the email address.
     *
     * @return The email address of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address.
     *
     * @param email The email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the GitHub ID.
     *
     * @return The GitHub ID of the user (if applicable)
     */
    public String getGithubId() {
        return githubId;
    }

    /**
     * Sets the GitHub ID.
     *
     * @param githubId The GitHub ID to set
     */
    public void setGithubId(String githubId) {
        this.githubId = githubId;
    }

    /**
     * Gets the authentication provider.
     *
     * @return The authentication provider ("local" or "github")
     */
    public String getAuth_provider() {
        return auth_provider;
    }

    /**
     * Sets the authentication provider.
     *
     * @param auth_provider The authentication provider to set ("local" or
     * "github")
     */
    public void setAuth_provider(String auth_provider) {
        this.auth_provider = auth_provider;
    }

    /**
     * Gets the user role.
     *
     * @return The role of the user (e.g., "admin", "user")
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the user role.
     *
     * @param role The role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Gets the user status.
     *
     * @return The status of the user account (e.g., "active", "inactive")
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the user status.
     *
     * @param status The status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the avatar URL.
     *
     * @return The URL of the user's avatar image (if applicable)
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * Sets the avatar URL.
     *
     * @param avatarUrl The URL of the avatar to set
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    /**
     * Gets the last login timestamp.
     *
     * @return The timestamp of the user's last login
     */
    public Timestamp getLast_login() {
        return last_login;
    }

    /**
     * Sets the last login timestamp.
     *
     * @param last_login The timestamp of the last login to set
     */
    public void setLast_login(Timestamp last_login) {
        this.last_login = last_login;
    }

    /**
     * Gets the creation timestamp.
     *
     * @return The timestamp when the user account was created
     */
    public Timestamp getCreated_at() {
        return created_at;
    }

    /**
     * Sets the creation timestamp.
     *
     * @param created_at The timestamp of account creation to set
     */
    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }
}
