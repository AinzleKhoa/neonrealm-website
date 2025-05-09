/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package gameshop.controller;

import gameshop.DAO.AdminGamesDAO;
import gameshop.model.AdminGames;
import gameshop.util.SessionUtil;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Servlet for managing admin game operations such as listing, adding, editing,
 * and deleting games. Supports file uploads for game images with specified size
 * limits.
 *
 * @author Pham Van Hoai - CE181744
 */
@WebServlet(name = "AdminGamesServlet", urlPatterns = {"/admin/games"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB - Memory threshold before writing to disk
        maxFileSize = 1024 * 1024 * 10, // 10MB - Maximum size for a single file
        maxRequestSize = 1024 * 1024 * 50 // 50MB - Maximum size for the entire request
)
public class AdminGamesServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Directory where uploaded game images are stored.
     */
    private static final String UPLOAD_DIR = "/assets/img/cards/";

    /**
     * Handles the HTTP <code>GET</code> method to display the game list, add
     * game form, or edit game form.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Check if the user is an admin; redirect to home if not
        if (!SessionUtil.isAdmin(request)) {
            response.sendRedirect(request.getContextPath() + "/pages/home.jsp");
            return;
        }

        AdminGamesDAO adminGamesDAO = new AdminGamesDAO();

        // Handle request to display the add game form
        if (request.getParameter("add") != null) {
            loadFormData(request, adminGamesDAO);
            request.getRequestDispatcher("/admin/add-game.jsp").forward(request, response);
            return;
        }

        // Handle request to edit a specific game
        String editId = request.getParameter("editId");
        if (editId != null) {
            try {
                int gameId = Integer.parseInt(editId);
                AdminGames game = adminGamesDAO.getGameById(gameId);

                // Redirect to 404 page if the game is not found
                if (game == null) {
                    response.sendRedirect(request.getContextPath() + "/pages/404.jsp");
                    return;
                }

                // Load form data and set game object for editing
                loadFormData(request, adminGamesDAO);
                request.setAttribute("game", game);
                request.getRequestDispatcher("/admin/edit-game.jsp").forward(request, response);
                return;
            } catch (NumberFormatException e) {
                // Redirect to game list if editId is invalid
                response.sendRedirect(request.getContextPath() + "/admin/games");
                return;
            }
        }

        // Fetch filter parameters for genre and search query
        String selectedGenre = request.getParameter("genre");
        String searchQuery = request.getParameter("search");

        // Pagination setup
        int totalGamesPerPage = 10;
        int currentPage = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;
        int offset = (currentPage - 1) * totalGamesPerPage;

        // Retrieve filtered and paginated game list
        List<AdminGames> games = adminGamesDAO.getGamesByFilter(searchQuery, selectedGenre, offset, totalGamesPerPage);
        int totalGames = adminGamesDAO.countGamesByFilter(searchQuery, selectedGenre);
        int totalPages = (int) Math.ceil((double) totalGames / totalGamesPerPage);

        // Set pagination attributes
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalGames", totalGames);

        // Load all genres for filtering
        List<String> allGenres = adminGamesDAO.getAllGenres();
        request.setAttribute("allGenres", allGenres);

        // Calculate total pages and current total games for display
        int numOfPages = (int) Math.ceil((double) totalGames / totalGamesPerPage);
        int currentTotalGames = (currentPage < numOfPages ? totalGamesPerPage * currentPage : totalGames);
        request.setAttribute("currentTotalGames", currentTotalGames);
        request.setAttribute("numOfPages", numOfPages);

        // Set game list and filter parameters for JSP
        request.setAttribute("games", games);
        request.setAttribute("selectedGenre", selectedGenre);
        request.setAttribute("searchQuery", searchQuery);

        // Forward to the game list page
        request.getRequestDispatcher("/admin/games.jsp").forward(request, response);
    }

    /**
     * Loads data for dropdowns or selections in add/edit game forms.
     *
     * @param request The servlet request to set attributes
     * @param adminGamesDAO The DAO instance to fetch data
     */
    private void loadFormData(HttpServletRequest request, AdminGamesDAO adminGamesDAO) {
        request.setAttribute("allGenres", adminGamesDAO.getAllGenres());
        request.setAttribute("allCategories", adminGamesDAO.getAllCategories());
        request.setAttribute("allDevelopers", adminGamesDAO.getAllDevelopers());
        request.setAttribute("allPublishers", adminGamesDAO.getAllPublishers());
        request.setAttribute("allPlatforms", adminGamesDAO.getAllPlatforms());
    }

    /**
     * Handles the HTTP <code>POST</code> method to process actions like adding,
     * editing, or deleting games.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        AdminGamesDAO adminGamesDAO = new AdminGamesDAO();

        if ("add".equals(action) || "edit".equals(action)) {
            try {
                // Retrieve form data
                int gameId = "edit".equals(action) ? Integer.parseInt(request.getParameter("game_id")) : 0;
                String title = request.getParameter("title");
                String description = request.getParameter("description");
                BigDecimal price = new BigDecimal(request.getParameter("price"));
                Date releaseDate = Date.valueOf(request.getParameter("release_date"));

                // Handle image upload or reuse existing image
                String imageUrl = handleFileUpload(request, gameId, adminGamesDAO);

                // Get safe lists for multi-select fields, defaulting to empty if null
                List<String> genres = getSafeList(request.getParameterValues("genres"));
                List<String> categories = getSafeList(request.getParameterValues("categories"));
                List<String> developers = getSafeList(request.getParameterValues("developers"));
                List<String> publishers = getSafeList(request.getParameterValues("publishers"));
                List<String> platforms = getSafeList(request.getParameterValues("platforms"));

                // Create game object with form data
                AdminGames game = new AdminGames(gameId, title, description, imageUrl, price, releaseDate, developers, publishers, genres, platforms, categories);

                // Perform add or edit operation
                if ("add".equals(action)) {
                    adminGamesDAO.addGame(game);
                    request.getSession().setAttribute("successMessage", "Add game successfully!");
                } else {
                    adminGamesDAO.updateGame(game);
                    request.getSession().setAttribute("successMessage", "Game updated successfully!");
                }

                // Redirect to game list page
                response.sendRedirect(request.getContextPath() + "/admin/games");
            } catch (Exception e) {
                e.printStackTrace();
                // Redirect with error parameter if an exception occurs
                response.sendRedirect(request.getContextPath() + "/admin/games?error=1");
            }
        } else if ("delete".equals(action)) {
            // Delete a game by ID
            int gameId = Integer.parseInt(request.getParameter("gameId"));
            adminGamesDAO.deleteGame(gameId);
            request.getSession().setAttribute("successMessage", "Game deleted successfully!");
            response.sendRedirect(request.getContextPath() + "/admin/games");
        }
    }

    /**
     * Handles file upload for game images. Reuses the existing image if no new
     * file is uploaded.
     *
     * @param request The servlet request containing the file part
     * @param gameId The ID of the game (0 for new games)
     * @param adminGamesDAO The DAO instance to fetch existing game data
     * @return The image URL (newly uploaded or existing)
     * @throws IOException if an I/O error occurs
     * @throws ServletException if a servlet-specific error occurs
     */
    private String handleFileUpload(HttpServletRequest request, int gameId, AdminGamesDAO adminGamesDAO) throws IOException, ServletException {
        Part filePart = request.getPart("image");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String imageUrl = null;

        // Handle new file upload
        if (fileName != null && !fileName.isEmpty()) {
            String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir(); // Create directory if it doesn't exist
            }

            String filePath = uploadPath + File.separator + fileName;
            filePart.write(filePath); // Save the uploaded file
            imageUrl = fileName;
        } else if (gameId != 0) {
            // Reuse existing image for edits if no new file is uploaded
            AdminGames existingGame = adminGamesDAO.getGameById(gameId);
            if (existingGame != null) {
                imageUrl = existingGame.getImageUrl();
            }
        }
        return imageUrl;
    }

    /**
     * Safely converts an array of values into a list, returning an empty list
     * if null.
     *
     * @param values The array of values from the request
     * @return A list of strings, or an empty list if values is null
     */
    private List<String> getSafeList(String[] values) {
        return (List<String>) ((values != null) ? Arrays.asList(values) : new ArrayList<>());
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet for managing admin game operations";
    }// </editor-fold>
}
