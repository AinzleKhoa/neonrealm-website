/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameshop.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * Model class representing a game entity for admin operations in the game shop
 * system.
 *
 * @author Pham Van Hoai - CE181744
 */
public class AdminGames {

    private int gameId;              // Unique identifier for the game
    private String title;            // Title of the game
    private String description;      // Description of the game
    private String imageUrl;         // URL or filename of the game's image
    private BigDecimal price;        // Price of the game
    private Date releaseDate;        // Release date of the game
    private Timestamp createdAt;     // Timestamp of when the game was added
    private List<String> developers; // List of developers associated with the game
    private List<String> publishers; // List of publishers associated with the game
    private List<String> genres;     // List of genres the game belongs to
    private List<String> platforms;  // List of platforms the game supports
    private List<String> categories; // List of categories the game fits into

    /**
     * Default no-argument constructor for creating an empty AdminGames object.
     */
    public AdminGames() {
    }

    /**
     * Constructor to initialize an AdminGames object with all fields, including
     * creation timestamp.
     *
     * @param gameId The unique ID of the game
     * @param title The title of the game
     * @param description The description of the game
     * @param imageUrl The URL or filename of the game's image
     * @param price The price of the game
     * @param releaseDate The release date of the game
     * @param createdAt The timestamp of creation
     * @param developers The list of developers
     * @param publishers The list of publishers
     * @param genres The list of genres
     * @param platforms The list of platforms
     * @param categories The list of categories
     */
    public AdminGames(int gameId, String title, String description, String imageUrl, BigDecimal price, Date releaseDate, Timestamp createdAt, List<String> developers, List<String> publishers, List<String> genres, List<String> platforms, List<String> categories) {
        this.gameId = gameId;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.releaseDate = releaseDate;
        this.createdAt = createdAt;
        this.developers = developers;
        this.publishers = publishers;
        this.genres = genres;
        this.platforms = platforms;
        this.categories = categories;
    }

    /**
     * Constructor to initialize an AdminGames object without creation
     * timestamp.
     *
     * @param gameId The unique ID of the game
     * @param title The title of the game
     * @param description The description of the game
     * @param imageUrl The URL or filename of the game's image
     * @param price The price of the game
     * @param releaseDate The release date of the game
     * @param developers The list of developers
     * @param publishers The list of publishers
     * @param genres The list of genres
     * @param platforms The list of platforms
     * @param categories The list of categories
     */
    public AdminGames(int gameId, String title, String description, String imageUrl, BigDecimal price, Date releaseDate, List<String> developers, List<String> publishers, List<String> genres, List<String> platforms, List<String> categories) {
        this.gameId = gameId;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.releaseDate = releaseDate;
        this.developers = developers;
        this.publishers = publishers;
        this.genres = genres;
        this.platforms = platforms;
        this.categories = categories;
    }

    /**
     * Constructor to initialize an AdminGames object with basic fields only.
     *
     * @param gameId The unique ID of the game
     * @param title The title of the game
     * @param description The description of the game
     * @param imageUrl The URL or filename of the game's image
     * @param price The price of the game
     * @param releaseDate The release date of the game
     */
    public AdminGames(int gameId, String title, String description, String imageUrl, BigDecimal price, Date releaseDate) {
        this.gameId = gameId;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.releaseDate = releaseDate;
    }

    /**
     * Constructor to initialize an AdminGames object with genres and publishers
     * only.
     *
     * @param gameId The unique ID of the game
     * @param title The title of the game
     * @param description The description of the game
     * @param imageUrl The URL or filename of the game's image
     * @param price The price of the game
     * @param releaseDate The release date of the game
     * @param createdAt The timestamp of creation
     * @param genres The list of genres
     * @param publishers The list of publishers
     */
    public AdminGames(int gameId, String title, String description, String imageUrl, BigDecimal price, Date releaseDate, Timestamp createdAt, List<String> genres, List<String> publishers) {
        this.gameId = gameId;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.releaseDate = releaseDate;
        this.createdAt = createdAt;
        this.genres = genres;
        this.publishers = publishers;
    }

    /**
     * Gets the game ID.
     *
     * @return The unique identifier of the game
     */
    public int getGameId() {
        return gameId;
    }

    /**
     * Sets the game ID.
     *
     * @param gameId The unique identifier to set
     */
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    /**
     * Gets the game title.
     *
     * @return The title of the game
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the game title.
     *
     * @param title The title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the game description.
     *
     * @return The description of the game
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the game description.
     *
     * @param description The description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the image URL of the game.
     *
     * @return The URL or filename of the game's image
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Sets the image URL of the game.
     *
     * @param imageUrl The URL or filename to set
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * Gets the price of the game.
     *
     * @return The price of the game
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the price of the game.
     *
     * @param price The price to set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Gets the release date of the game.
     *
     * @return The release date
     */
    public Date getReleaseDate() {
        return releaseDate;
    }

    /**
     * Sets the release date of the game.
     *
     * @param releaseDate The release date to set
     */
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * Gets the creation timestamp of the game.
     *
     * @return The timestamp when the game was created
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation timestamp of the game.
     *
     * @param createdAt The timestamp to set for creation
     */
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the list of developers.
     *
     * @return The list of developers associated with the game
     */
    public List<String> getDevelopers() {
        return developers;
    }

    /**
     * Sets the list of developers.
     *
     * @param developers The list of developers to set
     */
    public void setDevelopers(List<String> developers) {
        this.developers = developers;
    }

    /**
     * Gets the list of publishers.
     *
     * @return The list of publishers associated with the game
     */
    public List<String> getPublishers() {
        return publishers;
    }

    /**
     * Sets the list of publishers.
     *
     * @param publishers The list of publishers to set
     */
    public void setPublishers(List<String> publishers) {
        this.publishers = publishers;
    }

    /**
     * Gets the list of genres.
     *
     * @return The list of genres the game belongs to
     */
    public List<String> getGenres() {
        return genres;
    }

    /**
     * Sets the list of genres.
     *
     * @param genres The list of genres to set
     */
    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    /**
     * Gets the list of platforms.
     *
     * @return The list of platforms the game supports
     */
    public List<String> getPlatforms() {
        return platforms;
    }

    /**
     * Sets the list of platforms.
     *
     * @param platforms The list of platforms to set
     */
    public void setPlatforms(List<String> platforms) {
        this.platforms = platforms;
    }

    /**
     * Gets the list of categories.
     *
     * @return The list of categories the game fits into
     */
    public List<String> getCategories() {
        return categories;
    }

    /**
     * Sets the list of categories.
     *
     * @param categories The list of categories to set
     */
    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
