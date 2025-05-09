/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameshop.DAO;

import gameshop.db.DBContext;
import gameshop.model.Game;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * GameDAO class is responsible for handling database operations related to
 * game
 *
 * @author CE190449 - Le Anh Khoa
 */
public class GameDAO extends DBContext {

    /**
     * Retrieves the list of platform names from the database.
     *
     * @return a List of platform names.
     */
    public List<String> getPlatformName() {
        List<String> list = new ArrayList<>();
        try {
            String query = "SELECT name\n"
                    + "FROM Platforms";
            ResultSet rs = execSelectQuery(query);

            while (rs.next()) {
                list.add(rs.getString("name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GameDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    /**
     * Retrieves the list of genre names from the database.
     *
     * @return a List of genre names.
     */
    public List<String> getGenreName() {
        List<String> list = new ArrayList<>();
        try {
            String query = "SELECT name\n"
                    + "FROM Genres";
            ResultSet rs = execSelectQuery(query);

            while (rs.next()) {
                list.add(rs.getString("name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GameDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    /**
     * Retrieves a game by its ID from the database.
     *
     * @param id the game ID.
     * @return the Game object corresponding to the given ID.
     */
    public Game getGameById(int id) {
        Game game = null;
        String query = "SELECT \n"
                + "    g.game_id AS id, \n"
                + "    g.title, \n"
                + "	g.description,\n"
                + "	g.release_date,\n"
                + "    g.image_url, \n"
                + "    g.price, \n"
                + "	-- Subquery to remove duplicates before aggregation (Developers)\n"
                + "	(SELECT STRING_AGG(unique_developers.name, ', ')\n"
                + "	FROM (SELECT DISTINCT d.name\n"
                + "		  FROM Game_Developers gd\n"
                + "		  JOIN Developers d ON d.developer_id = gd.developer_id\n"
                + "		  WHERE g.game_id = gd.game_id) AS unique_developers\n"
                + "	) AS developers,\n"
                + "	-- Subquery to remove duplicates before aggregation (Publishers)\n"
                + "	(SELECT STRING_AGG(unique_publishers.name, ', ')\n"
                + "	FROM (SELECT DISTINCT p.name\n"
                + "		  FROM Game_Publishers gp\n"
                + "		  JOIN Publishers p ON p.publisher_id = gp.publisher_id\n"
                + "		  WHERE g.game_id = gp.game_id) AS unique_publishers\n"
                + "	) AS publishers,\n"
                + "	-- Subquery to remove duplicates before aggregation (Genres)\n"
                + "	(SELECT STRING_AGG(unique_genres.name, ', ')\n"
                + "	FROM (SELECT DISTINCT gs.name\n"
                + "		  FROM Game_Genres gg\n"
                + "		  JOIN Genres gs ON gs.genre_id = gg.genre_id\n"
                + "		  WHERE g.game_id = gg.game_id) AS unique_genres\n"
                + "	) AS genres,\n"
                + "    -- Subquery to remove duplicates before aggregation (Platforms)\n"
                + "    (SELECT STRING_AGG(unique_platforms.name, ', ') \n"
                + "     FROM (SELECT DISTINCT p.name \n"
                + "           FROM Game_Platforms gp \n"
                + "           JOIN Platforms p ON p.platform_id = gp.platform_id \n"
                + "           WHERE gp.game_id = g.game_id) AS unique_platforms\n"
                + "    ) AS platforms,\n"
                + "    -- Subquery to remove duplicates before aggregation (Categories)\n"
                + "    (SELECT STRING_AGG(unique_categories.name, ', ') \n"
                + "     FROM (SELECT DISTINCT c.name \n"
                + "           FROM Game_Categories gc \n"
                + "           JOIN Categories c ON c.category_id = gc.category_id \n"
                + "           WHERE gc.game_id = g.game_id) AS unique_categories\n"
                + "    ) AS categories\n"
                + "FROM Games g\n"
                + "WHERE g.game_id = ?;";
        Object[] params = {id};
        try ( ResultSet rs = execSelectQuery(query, params)) {
            if (rs.next()) {

                LocalDateTime releaseDate = (rs.getTimestamp("release_date") != null ? rs.getTimestamp("release_date").toLocalDateTime() : null);

                List<String> developerNames = new ArrayList<>();
                if (rs.getString("developers") != null) {
                    String[] names = rs.getString("developers").split(", ");
                    for (String name : names) {
                        try {
                            developerNames.add(name.trim());
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid developers name: " + name); // Debugging log
                        }
                    }
                }

                List<String> publisherNames = new ArrayList<>();
                if (rs.getString("publishers") != null) {
                    String[] names = rs.getString("publishers").split(", ");
                    for (String name : names) {
                        try {
                            publisherNames.add(name.trim());
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid publishers name: " + name); // Debugging log
                        }
                    }
                }

                List<String> genreNames = new ArrayList<>();
                if (rs.getString("genres") != null) {
                    String[] names = rs.getString("genres").split(",");
                    for (String name : names) {
                        try {
                            genreNames.add(name.trim());
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid genres name: " + name); // Debugging log
                        }
                    }
                }

                List<String> platformNames = new ArrayList<>();
                if (rs.getString("platforms") != null) {
                    String[] names = rs.getString("platforms").split(",");
                    for (String name : names) {
                        try {
                            platformNames.add(name.trim());
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid platform name: " + name); // Debugging log
                        }
                    }
                }

                List<String> categoryNames = new ArrayList<>();
                if (rs.getString("categories") != null) {
                    String[] names = rs.getString("categories").split(",");
                    for (String name : names) {
                        try {
                            categoryNames.add(name.trim());
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid category name: " + name); // Debugging log
                        }
                    }
                }

                game = new Game(
                        id,
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image_url"),
                        rs.getBigDecimal("price"),
                        releaseDate,
                        developerNames,
                        publisherNames,
                        genreNames,
                        platformNames,
                        categoryNames
                );

            }
        } catch (SQLException ex) {
            Logger.getLogger(GameDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return game;
    }

    /**
     * Retrieves a list of all games from the database.
     *
     * @return a List of Game objects.
     */
    public List<Game> getGameList() {
        List<Game> games = new ArrayList<>();
        String query = "SELECT \n"
                + "    g.game_id AS id, \n"
                + "    g.title, \n"
                + "    g.release_date,\n"
                + "    g.image_url, \n"
                + "    g.price, \n"
                + "	-- Subquery to remove duplicates before aggregation (Genres)\n"
                + "	(SELECT STRING_AGG(unique_genres.name, ', ')\n"
                + "	FROM (SELECT DISTINCT gs.name\n"
                + "		  FROM Game_Genres gg\n"
                + "		  JOIN Genres gs ON gs.genre_id = gg.genre_id\n"
                + "		  WHERE g.game_id = gg.game_id) AS unique_genres\n"
                + "	) AS genres,\n"
                + "    -- Subquery to remove duplicates before aggregation (Platforms)\n"
                + "    (SELECT STRING_AGG(unique_platforms.name, ', ') \n"
                + "     FROM (SELECT DISTINCT p.name \n"
                + "           FROM Game_Platforms gp \n"
                + "           JOIN Platforms p ON p.platform_id = gp.platform_id \n"
                + "           WHERE gp.game_id = g.game_id) AS unique_platforms\n"
                + "    ) AS platforms,\n"
                + "    -- Subquery to remove duplicates before aggregation (Categories)\n"
                + "    (SELECT STRING_AGG(unique_categories.name, ', ') \n"
                + "     FROM (SELECT DISTINCT c.name \n"
                + "           FROM Game_Categories gc \n"
                + "           JOIN Categories c ON c.category_id = gc.category_id \n"
                + "           WHERE gc.game_id = g.game_id) AS unique_categories\n"
                + "    ) AS categories\n"
                + "FROM Games g;";
        try ( ResultSet rs = execSelectQuery(query)) {
            while (rs.next()) {  // Always check if there's data first!

                LocalDateTime releaseDate = (rs.getTimestamp("release_date") != null ? rs.getTimestamp("release_date").toLocalDateTime() : null);

                List<String> genreNames = new ArrayList<>();
                if (rs.getString("genres") != null) {
                    String[] names = rs.getString("genres").split(", ");
                    for (String name : names) {
                        try {
                            genreNames.add(name.trim());
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid genres name: " + name); // Debugging log
                        }
                    }
                }

                List<String> platformNames = new ArrayList<>();
                if (rs.getString("platforms") != null) {
                    String[] names = rs.getString("platforms").split(", ");
                    for (String name : names) {
                        try {
                            platformNames.add(name.trim());
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid platform name: " + name); // Debugging log
                        }
                    }
                }

                List<String> categoryNames = new ArrayList<>();
                if (rs.getString("categories") != null) {
                    String[] names = rs.getString("categories").split(", ");
                    for (String name : names) {
                        try {
                            categoryNames.add(name.trim());
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid category name: " + name); // Debugging log
                        }
                    }
                }

                games.add(new Game(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("image_url"),
                        rs.getBigDecimal("price"),
                        releaseDate,
                        genreNames,
                        platformNames,
                        categoryNames
                ));

                // Debug
                if (games.isEmpty()) {
                    System.out.println("No games found in category.");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(GameDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return games;
    }

    /**
     * Retrieves a paginated list of games from the database.
     *
     * @param currentPage the current page number.
     * @param totalGamesPerPage the number of games per page.
     * @return a List of Game objects for the current page.
     */
    public List<Game> getPagination(int currentPage, int totalGamesPerPage) {
        List<Game> games = new ArrayList<>();
        try {
            String query = "SELECT \n"
                    + "    g.game_id AS id, \n"
                    + "    g.title, \n"
                    + "    g.release_date,\n"
                    + "    g.image_url, \n"
                    + "    g.price, \n"
                    + "	-- Subquery to remove duplicates before aggregation (Developers)\n"
                    + "	(SELECT STRING_AGG(unique_developers.name, ', ')\n"
                    + "	FROM (SELECT DISTINCT d.name\n"
                    + "		  FROM Game_Developers gd\n"
                    + "		  JOIN Developers d ON d.developer_id = gd.developer_id\n"
                    + "		  WHERE g.game_id = gd.game_id) AS unique_developers\n"
                    + "	) AS developers,\n"
                    + "	-- Subquery to remove duplicates before aggregation (Publishers)\n"
                    + "	(SELECT STRING_AGG(unique_publishers.name, ', ')\n"
                    + "	FROM (SELECT DISTINCT p.name\n"
                    + "		  FROM Game_Publishers gp\n"
                    + "		  JOIN Publishers p ON p.publisher_id = gp.publisher_id\n"
                    + "		  WHERE g.game_id = gp.game_id) AS unique_publishers\n"
                    + "	) AS publishers,\n"
                    + "	-- Subquery to remove duplicates before aggregation (Genres)\n"
                    + "	(SELECT STRING_AGG(unique_genres.name, ', ')\n"
                    + "	FROM (SELECT DISTINCT gs.name\n"
                    + "		  FROM Game_Genres gg\n"
                    + "		  JOIN Genres gs ON gs.genre_id = gg.genre_id\n"
                    + "		  WHERE g.game_id = gg.game_id) AS unique_genres\n"
                    + "	) AS genres,\n"
                    + "    -- Subquery to remove duplicates before aggregation (Platforms)\n"
                    + "    (SELECT STRING_AGG(unique_platforms.name, ', ') \n"
                    + "     FROM (SELECT DISTINCT p.name \n"
                    + "           FROM Game_Platforms gp \n"
                    + "           JOIN Platforms p ON p.platform_id = gp.platform_id \n"
                    + "           WHERE gp.game_id = g.game_id) AS unique_platforms\n"
                    + "    ) AS platforms,\n"
                    + "    -- Subquery to remove duplicates before aggregation (Categories)\n"
                    + "    (SELECT STRING_AGG(unique_categories.name, ', ') \n"
                    + "     FROM (SELECT DISTINCT c.name \n"
                    + "           FROM Game_Categories gc \n"
                    + "           JOIN Categories c ON c.category_id = gc.category_id \n"
                    + "           WHERE gc.game_id = g.game_id) AS unique_categories\n"
                    + "    ) AS categories\n"
                    + "FROM Games g\n"
                    + "ORDER BY    g.game_id, \n"
                    + "		g.title, \n"
                    + "		g.description,\n"
                    + "		g.release_date,\n"
                    + "		g.image_url, \n"
                    + "		g.price\n"
                    + "OFFSET ? ROWS\n"
                    + "FETCH NEXT ? ROWS ONLY";
            Object[] params = {currentPage, totalGamesPerPage};

            ResultSet rs = execSelectQuery(query, params);

            while (rs.next()) {
                LocalDateTime releaseDate = (rs.getTimestamp("release_date") != null ? rs.getTimestamp("release_date").toLocalDateTime() : null);

                List<String> genreNames = new ArrayList<>();
                if (rs.getString("genres") != null) {
                    String[] names = rs.getString("genres").split(", ");
                    for (String name : names) {
                        try {
                            genreNames.add(name.trim());
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid genres name: " + name); // Debugging log
                        }
                    }
                }

                List<String> platformNames = new ArrayList<>();
                if (rs.getString("platforms") != null) {
                    String[] names = rs.getString("platforms").split(", ");
                    for (String name : names) {
                        try {
                            platformNames.add(name.trim());
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid platform name: " + name); // Debugging log
                        }
                    }
                }

                List<String> categoryNames = new ArrayList<>();
                if (rs.getString("categories") != null) {
                    String[] names = rs.getString("categories").split(", ");
                    for (String name : names) {
                        try {
                            categoryNames.add(name.trim());
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid category name: " + name); // Debugging log
                        }
                    }
                }

                games.add(new Game(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("image_url"),
                        rs.getBigDecimal("price"),
                        releaseDate,
                        genreNames,
                        platformNames,
                        categoryNames
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GameDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return games;
    }

    /**
     * Counts the total number of games in the database.
     *
     * @return the total number of games.
     */
    public int countGames() {
        try {
            String query = "SELECT COUNT(g.game_id)\n"
                    + "FROM Games g";
            ResultSet rs = execSelectQuery(query);
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GameDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    /**
     * Retrieves a filtered and paginated list of games based on the selected
     * platforms, genres, and keyword.
     *
     * @param currentPage the current page number.
     * @param totalGamesPerPage the number of games per page.
     * @param selectedPlatforms the platforms selected by the user.
     * @param selectedGenres the genres selected by the user.
     * @param keyword the search keyword.
     * @return a List of filtered and paginated Game objects.
     */
    public List<Game> getFilteredPagination(int currentPage, int totalGamesPerPage, String[] selectedPlatforms, String[] selectedGenres, String keyword) {
        List<Game> games = new ArrayList<>();
        try {
            StringBuilder query = new StringBuilder("WITH FilteredGames AS (\n"
                    + "SELECT \n"
                    + "		g.game_id, \n"
                    + "		g.title, \n"
                    + "		g.release_date,\n"
                    + "		g.image_url, \n"
                    + "		g.price, \n"
                    + "		-- Subquery to remove duplicates before aggregation (Developers)\n"
                    + "		(SELECT STRING_AGG(unique_developers.name, ', ')\n"
                    + "		FROM (SELECT DISTINCT d.name\n"
                    + "			  FROM Game_Developers gd\n"
                    + "			  JOIN Developers d ON d.developer_id = gd.developer_id\n"
                    + "			  WHERE g.game_id = gd.game_id) AS unique_developers\n"
                    + "		) AS developers,\n"
                    + "		-- Subquery to remove duplicates before aggregation (Publishers)\n"
                    + "		(SELECT STRING_AGG(unique_publishers.name, ', ')\n"
                    + "		FROM (SELECT DISTINCT p.name\n"
                    + "			  FROM Game_Publishers gp\n"
                    + "			  JOIN Publishers p ON p.publisher_id = gp.publisher_id\n"
                    + "			  WHERE g.game_id = gp.game_id) AS unique_publishers\n"
                    + "		) AS publishers,\n"
                    + "		-- Subquery to remove duplicates before aggregation (Genres)\n"
                    + "		(SELECT STRING_AGG(unique_genres.name, ', ')\n"
                    + "		FROM (SELECT DISTINCT gs.name\n"
                    + "			  FROM Game_Genres gg\n"
                    + "			  JOIN Genres gs ON gs.genre_id = gg.genre_id\n"
                    + "			  WHERE g.game_id = gg.game_id) AS unique_genres\n"
                    + "		) AS genres,\n"
                    + "		-- Subquery to remove duplicates before aggregation (Platforms)\n"
                    + "		(SELECT STRING_AGG(unique_platforms.name, ', ') \n"
                    + "		 FROM (SELECT DISTINCT p.name \n"
                    + "			   FROM Game_Platforms gp \n"
                    + "			   JOIN Platforms p ON p.platform_id = gp.platform_id \n"
                    + "			   WHERE gp.game_id = g.game_id) AS unique_platforms\n"
                    + "		) AS platforms,\n"
                    + "		-- Subquery to remove duplicates before aggregation (Categories)\n"
                    + "		(SELECT STRING_AGG(unique_categories.name, ', ') \n"
                    + "		 FROM (SELECT DISTINCT c.name \n"
                    + "			   FROM Game_Categories gc \n"
                    + "			   JOIN Categories c ON c.category_id = gc.category_id \n"
                    + "			   WHERE gc.game_id = g.game_id) AS unique_categories\n"
                    + "		) AS categories\n"
                    + "	FROM Games g");
            List<Object> params = new ArrayList<>(); // Stores values for the ? placeholders in the SQL query.
            List<String> conditions = new ArrayList<>(); // Stores different WHERE conditions to be added dynamically.

            if (keyword != null && !keyword.isEmpty()) {
                conditions.add("LOWER(g.title) LIKE ?");
                params.add("%" + keyword.toLowerCase() + "%");
            }
            if (selectedGenres.length > 0) {
                conditions.add("g.game_id IN \n"
                        + "	(SELECT gg.game_id\n"
                        + "	FROM Game_Genres gg\n"
                        + "	JOIN Genres gs ON gg.genre_id = gs.genre_id\n"
                        + "	WHERE gs.name IN " + buildSQLInClause(selectedGenres) + ")");
                params.addAll(Arrays.asList(selectedGenres)); // converts the array into a List. String[] → List<String>
                // params.addAll(...) only works with a Collection (like a List), not an array.
            }
            if (selectedPlatforms.length > 0) {
                conditions.add("g.game_id IN \n"
                        + "	(SELECT gp.game_id\n"
                        + "	FROM Game_Platforms gp\n"
                        + "	JOIN Platforms pl ON gp.platform_id = pl.platform_id\n"
                        + "	WHERE pl.name IN " + buildSQLInClause(selectedPlatforms) + ")");
                params.addAll(Arrays.asList(selectedPlatforms)); // converts the array into a List.
            }
            if (!conditions.isEmpty()) {
                query.append(" WHERE ").append(String.join(" AND ", conditions)); // Add WHERE before a whole list of conditions followed by AND
            }

            // Add pagination
            query.append(") -- At the end\n"
                    + "SELECT 	fg.game_id AS id, \n"
                    + "		fg.title, \n"
                    + "		fg.release_date,\n"
                    + "		fg.image_url, \n"
                    + "		fg.price,\n"
                    + "		fg.developers,\n"
                    + "		fg.publishers,\n"
                    + "		fg.genres,\n"
                    + "		fg.platforms,\n"
                    + "		fg.categories\n"
                    + "FROM FilteredGames fg\n"
                    + "ORDER BY fg.game_id\n"
                    + "OFFSET ? ROWS\n"
                    + "FETCH NEXT ? ROWS ONLY");
            params.add(currentPage);
            params.add(totalGamesPerPage);

            // params.toArray : JDBC requires query parameters in an Object[] array. => convert from List<Object> to Object[]
            ResultSet rs = execSelectQuery(query.toString(), params.toArray());

            while (rs.next()) {
                LocalDateTime releaseDate = (rs.getTimestamp("release_date") != null ? rs.getTimestamp("release_date").toLocalDateTime() : null);

                List<String> developerNames = new ArrayList<>();
                if (rs.getString("developers") != null) {
                    String[] names = rs.getString("developers").split(", ");
                    for (String name : names) {
                        try {
                            developerNames.add(name.trim());
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid developers name: " + name); // Debugging log
                        }
                    }
                }

                List<String> publisherNames = new ArrayList<>();
                if (rs.getString("publishers") != null) {
                    String[] names = rs.getString("publishers").split(", ");
                    for (String name : names) {
                        try {
                            publisherNames.add(name.trim());
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid publishers name: " + name); // Debugging log
                        }
                    }
                }

                List<String> genreNames = new ArrayList<>();
                if (rs.getString("genres") != null) {
                    String[] names = rs.getString("genres").split(", ");
                    for (String name : names) {
                        try {
                            genreNames.add(name.trim());
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid genres name: " + name); // Debugging log
                        }
                    }
                }

                List<String> platformNames = new ArrayList<>();
                if (rs.getString("platforms") != null) {
                    String[] names = rs.getString("platforms").split(", ");
                    for (String name : names) {
                        try {
                            platformNames.add(name.trim());
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid platform name: " + name); // Debugging log
                        }
                    }
                }

                List<String> categoryNames = new ArrayList<>();
                if (rs.getString("categories") != null) {
                    String[] names = rs.getString("categories").split(", ");
                    for (String name : names) {
                        try {
                            categoryNames.add(name.trim());
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid category name: " + name); // Debugging log
                        }
                    }
                }

                games.add(new Game(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("image_url"),
                        rs.getBigDecimal("price"),
                        releaseDate,
                        developerNames,
                        publisherNames,
                        genreNames,
                        platformNames,
                        categoryNames
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GameDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return games;
    }

    /**
     * Counts the total number of games that match the selected filters
     * (platforms, genres, and keyword).
     *
     * @param selectedPlatforms the platforms selected by the user.
     * @param selectedGenres the genres selected by the user.
     * @param keyword the search keyword.
     * @return the total number of filtered games.
     */
    public int countFilteredGames(String[] selectedPlatforms, String[] selectedGenres, String keyword) {
        try {
            StringBuilder query = new StringBuilder("SELECT COUNT(g.game_id) FROM Games g");
            List<Object> params = new ArrayList<>(); // Stores values for the ? placeholders in the SQL query.
            List<String> conditions = new ArrayList<>(); // Stores different WHERE conditions to be added dynamically.

            if (keyword != null && !keyword.isEmpty()) {
                conditions.add("LOWER(g.title) LIKE ?");
                params.add("%" + keyword.toLowerCase() + "%");
            }
            if (selectedGenres.length > 0) {
                conditions.add("g.game_id IN \n"
                        + "	(SELECT gg.game_id\n"
                        + "	FROM Game_Genres gg\n"
                        + "	JOIN Genres gs ON gg.genre_id = gs.genre_id\n"
                        + "	WHERE gs.name IN " + buildSQLInClause(selectedGenres) + ")");
                params.addAll(Arrays.asList(selectedGenres)); // converts the array into a List. String[] → List<String>
                // params.addAll(...) only works with a Collection (like a List), not an array.
            }
            if (selectedPlatforms.length > 0) {
                conditions.add("g.game_id IN \n"
                        + "	(SELECT gp.game_id\n"
                        + "	FROM Game_Platforms gp\n"
                        + "	JOIN Platforms pl ON gp.platform_id = pl.platform_id\n"
                        + "	WHERE pl.name IN " + buildSQLInClause(selectedPlatforms) + ")");
                params.addAll(Arrays.asList(selectedPlatforms)); // converts the array into a List.
            }
            if (!conditions.isEmpty()) {
                query.append(" WHERE ").append(String.join(" AND ", conditions)); // Add WHERE before a whole list of conditions followed by AND
            }
            // params.toArray : JDBC requires query parameters in an Object[] array. => convert from List<Object> to Object[]
            ResultSet rs = execSelectQuery(query.toString(), params.toArray());

            if (rs.next() && rs.getInt(1) > 0) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GameDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    /**
     * Builds the SQL IN clause for filtering games based on an array of values.
     *
     * @param values an array of values to be used in the IN clause.
     * @return a string representing the SQL IN clause.
     */
    public String buildSQLInClause(String[] values) {
        if (values == null || values.length == 0) {
            return "('')"; // Avoid SQL syntax error
        }
        return "(" + String.join(", ", Collections.nCopies(values.length, "?")) + ")";
    }

}
