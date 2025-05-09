package gameshop.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a game in the system. This class contains all the details related
 * to a game, including its title, description, price, release date, and
 * associated developers, publishers, genres, platforms, and categories.
 *
 * @author Le Anh Khoa - CE190449
 */
public class Game {

    private int gameId;
    private String title;
    private String description;
    private String imageUrl;
    private BigDecimal price;
    private LocalDateTime releaseDate;
    private LocalDateTime createdAt;
    private List<String> developers;
    private List<String> publishers;
    private List<String> genres;
    private List<String> platforms;
    private List<String> categories;

    /**
     * Default constructor.
     */
    public Game() {
    }

    /**
     * Constructor for OrderDetails.
     *
     * @param gameId the unique ID of the game
     * @param title the title of the game
     * @param description the description of the game
     * @param imageUrl the image URL of the game
     * @param price the price of the game
     * @param releaseDate the release date of the game
     * @param createdAt the creation timestamp of the game record
     * @param developers the list of developers of the game
     * @param publishers the list of publishers of the game
     * @param genres the list of genres of the game
     * @param platforms the list of platforms for the game
     * @param categories the list of categories the game belongs to
     */
    public Game(int gameId, String title, String description, String imageUrl, BigDecimal price,
            LocalDateTime releaseDate, LocalDateTime createdAt, List<String> developers,
            List<String> publishers, List<String> genres, List<String> platforms, List<String> categories) {
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
     * Constructor for retrieving all the game data for the catalog.
     *
     * @param gameId the unique ID of the game
     * @param title the title of the game
     * @param imageUrl the image URL of the game
     * @param price the price of the game
     * @param releaseDate the release date of the game
     * @param developers the list of developers of the game
     * @param publishers the list of publishers of the game
     * @param genres the list of genres of the game
     * @param platforms the list of platforms for the game
     * @param categories the list of categories the game belongs to
     */
    public Game(int gameId, String title, String imageUrl, BigDecimal price, LocalDateTime releaseDate,
            List<String> developers, List<String> publishers, List<String> genres,
            List<String> platforms, List<String> categories) {
        this.gameId = gameId;
        this.title = title;
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
     * Constructor for retrieving the game data for the home page.
     *
     * @param gameId the unique ID of the game
     * @param title the title of the game
     * @param imageUrl the image URL of the game
     * @param price the price of the game
     * @param releaseDate the release date of the game
     * @param genres the list of genres of the game
     * @param platforms the list of platforms for the game
     * @param categories the list of categories the game belongs to
     */
    public Game(int gameId, String title, String imageUrl, BigDecimal price, LocalDateTime releaseDate,
            List<String> genres, List<String> platforms, List<String> categories) {
        this.gameId = gameId;
        this.title = title;
        this.imageUrl = imageUrl;
        this.price = price;
        this.releaseDate = releaseDate;
        this.genres = genres;
        this.platforms = platforms;
        this.categories = categories;
    }

    /**
     * Constructor for retrieving the game data for the game details page.
     *
     * @param gameId the unique ID of the game
     * @param title the title of the game
     * @param description the description of the game
     * @param imageUrl the image URL of the game
     * @param price the price of the game
     * @param releaseDate the release date of the game
     * @param developers the list of developers of the game
     * @param publishers the list of publishers of the game
     * @param genres the list of genres of the game
     * @param platforms the list of platforms for the game
     * @param categories the list of categories the game belongs to
     */
    public Game(int gameId, String title, String description, String imageUrl, BigDecimal price,
            LocalDateTime releaseDate, List<String> developers, List<String> publishers,
            List<String> genres, List<String> platforms, List<String> categories) {
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
     * Gets the unique ID of the game.
     *
     * @return the game ID
     */
    public int getGameId() {
        return gameId;
    }

    /**
     * Sets the unique ID of the game.
     *
     * @param gameId the game ID to set
     */
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    /**
     * Gets the title of the game.
     *
     * @return the game title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the game.
     *
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the description of the game.
     *
     * @return the game description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the game.
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the image URL of the game.
     *
     * @return the image URL
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Sets the image URL of the game.
     *
     * @param imageUrl the image URL to set
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * Gets the price of the game.
     *
     * @return the price of the game
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the price of the game.
     *
     * @param price the price to set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Gets the release date of the game.
     *
     * @return the release date
     */
    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    /**
     * Sets the release date of the game.
     *
     * @param releaseDate the release date to set
     */
    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * Gets the creation timestamp of the game record.
     *
     * @return the creation timestamp
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation timestamp of the game record.
     *
     * @param createdAt the creation timestamp to set
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the list of developers for the game.
     *
     * @return the list of developers
     */
    public List<String> getDevelopers() {
        return developers;
    }

    /**
     * Sets the list of developers for the game.
     *
     * @param developers the developers to set
     */
    public void setDevelopers(List<String> developers) {
        this.developers = developers;
    }

    /**
     * Gets the list of publishers for the game.
     *
     * @return the list of publishers
     */
    public List<String> getPublishers() {
        return publishers;
    }

    /**
     * Sets the list of publishers for the game.
     *
     * @param publishers the publishers to set
     */
    public void setPublishers(List<String> publishers) {
        this.publishers = publishers;
    }

    /**
     * Gets the list of genres for the game.
     *
     * @return the list of genres
     */
    public List<String> getGenres() {
        return genres;
    }

    /**
     * Sets the list of genres for the game.
     *
     * @param genres the genres to set
     */
    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    /**
     * Gets the list of platforms for the game.
     *
     * @return the list of platforms
     */
    public List<String> getPlatforms() {
        return platforms;
    }

    /**
     * Sets the list of platforms for the game.
     *
     * @param platforms the platforms to set
     */
    public void setPlatforms(List<String> platforms) {
        this.platforms = platforms;
    }

    /**
     * Gets the list of categories for the game.
     *
     * @return the list of categories
     */
    public List<String> getCategories() {
        return categories;
    }

    /**
     * Sets the list of categories for the game.
     *
     * @param categories the categories to set
     */
    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    /**
     * Gets the formatted list of developers as a comma-separated string.
     *
     * @return the formatted string of developers
     */
    public String getFormattedDevelopers() {
        return String.join(", ", developers);
    }

    /**
     * Gets the formatted list of publishers as a comma-separated string.
     *
     * @return the formatted string of publishers
     */
    public String getFormattedPublishers() {
        return String.join(", ", publishers);
    }

    /**
     * Gets the formatted list of genres as a comma-separated string.
     *
     * @return the formatted string of genres
     */
    public String getFormattedGenres() {
        return String.join(", ", genres);
    }

    /**
     * Gets the formatted list of platforms as a comma-separated string.
     *
     * @return the formatted string of platforms
     */
    public String getFormattedPlatforms() {
        return String.join(", ", platforms);
    }

    /**
     * Gets the formatted list of categories as a comma-separated string.
     *
     * @return the formatted string of categories
     */
    public String getFormattedCategories() {
        return String.join(", ", categories);
    }
}
