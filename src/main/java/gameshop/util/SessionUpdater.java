/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameshop.util;

import gameshop.DAO.UserDAO;
import gameshop.model.User;
import jakarta.servlet.http.HttpSession;

/**
 * Utility class for updating session data with the latest user information.
 * This class provides a method to update the session with the latest user data
 * retrieved from the database.
 *
 * @author Le Anh Khoa - CE190449
 */
public class SessionUpdater {

    /**
     * Updates the session with the latest user information. This method
     * retrieves the updated user data from the database and sets it in the
     * session.
     *
     * @param session the HttpSession object to update
     * @param id the user ID to fetch updated information
     */
    public static void sessionUpdate(HttpSession session, int id) {
        UserDAO uDAO = new UserDAO();
        User updatedUser = uDAO.findById(id); // Retrieve updated user info from DB to dynamically update the profile

        if (updatedUser != null) {
            session.setAttribute("currentUser", updatedUser);
            session.setAttribute("currentEmail", updatedUser.getEmail());
        }
    }
}
