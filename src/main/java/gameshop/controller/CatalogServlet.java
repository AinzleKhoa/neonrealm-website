/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package gameshop.controller;

import gameshop.DAO.GameDAO;
import gameshop.model.Game;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * CatalogServlet handles the request for displaying the game catalog. It
 * retrieves game data from the database based on filters and pagination, and
 * forwards the result to the catalog page.
 *
 * @author CE190449 - Le Anh Khoa
 */
public class CatalogServlet extends HttpServlet {

    /**
     * Handles the HTTP GET method for displaying the catalog page with game
     * filters and pagination.
     *
     * @param request the servlet request
     * @param response the servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        GameDAO gDAO = new GameDAO();

        // Get platform and genre name for filter display
        List<String> platformNames = gDAO.getPlatformName();
        List<String> genreNames = gDAO.getGenreName();
        request.setAttribute("platformNames", platformNames);
        request.setAttribute("genreNames", genreNames);

        int currentPage;
        int totalGamesPerPage = 8;
        String pageParam = request.getParameter("page");
        // If "page" is missing or invalid, redirect to ?page=1
        if (pageParam == null || pageParam.isEmpty()) {
            String queryString = request.getQueryString();
            String newUrl = request.getContextPath() + "/catalog";

            // Only append `?` if `queryString` is empty
            if (queryString != null && !queryString.isEmpty()) {
                newUrl += "?" + queryString;
            }

            // Ensure page=1 is added correctly
            if (!newUrl.contains("page=")) {
                newUrl += newUrl.contains("?") ? "&page=1" : "?page=1";
            }

            response.sendRedirect(newUrl);
            return;
        } else {
            try {
                currentPage = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                currentPage = 1; // Default to page 1 if invalid input
            }
        }

        // Retrieve selected filters (if any)
        String[] selectedPlatforms = request.getParameterValues("platforms");
        String[] selectedGenres = request.getParameterValues("genres");
        String keyword = request.getParameter("keyword");

        // Convert comma-separated values into array so e.g http://localhost:8080/NeonRealm/catalog?platforms=Windows,Xbox&page=2 will work
        // Split so getQueryString can retrieve correctly for each "," 
        // E.g: platforms = ["Windows,Xbox"]; will become platforms = ["{Windows},{Xbox}"]; because split help it read individually
        if (selectedPlatforms != null && selectedPlatforms.length == 1 && selectedPlatforms[0].contains(",")) {
            selectedPlatforms = selectedPlatforms[0].split(",");
        }
        if (selectedGenres != null && selectedGenres.length == 1 && selectedGenres[0].contains(",")) {
            selectedGenres = selectedGenres[0].split(",");
        }

        if (selectedPlatforms == null) {
            selectedPlatforms = new String[0]; // Prevent null errors
        }
        if (selectedGenres == null) {
            selectedGenres = new String[0]; // Prevent null errors
        }
        if (keyword == null) {
            keyword = ""; // Prevent null errors
        }

        // Store filters in request attributes so they persist
        request.setAttribute("selectedPlatforms", selectedPlatforms);
        request.setAttribute("selectedGenres", selectedGenres);
        request.setAttribute("keyword", keyword);

        // Get filtered total count
        int totalGames = gDAO.countFilteredGames(selectedPlatforms, selectedGenres, keyword);
        request.setAttribute("totalGames", totalGames);

        // Calculate total pages correctly
        int numOfPages = (int) Math.ceil((double) totalGames / totalGamesPerPage);
        request.setAttribute("numOfPages", numOfPages);

        // Ensure correct current total games
        int currentTotalGames = Math.min(currentPage * totalGamesPerPage, totalGames);
        request.setAttribute("currentTotalGames", currentTotalGames);

        // Calculate OFFSET correctly
        int nextGame = (currentPage - 1) * totalGamesPerPage;
        List<Game> filteredGamePagination = gDAO.getFilteredPagination(nextGame, totalGamesPerPage, selectedPlatforms, selectedGenres, keyword);
        request.setAttribute("filteredGamesPage", filteredGamePagination);

        request.getRequestDispatcher("/WEB-INF/pages/catalog.jsp")
                .forward(request, response);
    }

    /**
     * Handles the HTTP POST method for the catalog servlet. This method is
     * currently not implemented.
     *
     * @param request the servlet request
     * @param response the servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
