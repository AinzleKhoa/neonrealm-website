/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameshop.util;

/**
 * Utility class for validating user inputs such as username, email, and
 * password. This class provides methods to check the validity of these inputs
 * based on specific rules.
 *
 * @author Le Anh Khoa - CE190449
 */
public class InputValidator {

    /**
     * Validates the username according to specific criteria. The username must
     * be between 3 and 20 characters long and only allow letters, numbers, and
     * underscores.
     *
     * @param username the username to be validated
     * @return true if the username is valid, false otherwise
     */
    public static boolean isUsernameValid(String username) {
        return username.matches("^[a-zA-Z0-9_]{3,20}$");
        /*
            3-20 Characters
            Only allow letters, numbers, underscores (_)
         */
    }

    /**
     * Validates the email address according to standard email format. The email
     * must contain only allowed characters and follow the pattern of username @
     * domain TLD
     *
     * @param email the email address to be validated
     * @return true if the email is valid, false otherwise
     */
    public static boolean isEmailValid(String email) {
        return email.matches("^[a-zA-Z0-9]+([._%+-]?[a-zA-Z0-9]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z]{2,})+$");
        /*
        ^                       Start of the string
        [a-zA-Z0-9]+            Username starts with a letter/number
        ([._%+-]?[a-zA-Z0-9]+)*	Allows . _ % + - in username
        @                       Required @ symbol
        [a-zA-Z0-9-]+           Domain must contain letters, numbers, or -
        (\\.[a-zA-Z]{2,})+	TLD must be at least 2 letters (.com, .org)
        $                       End of the string
         */
    }

    /**
     * Validates the password based on length and the inclusion of both letters
     * and digits. The password must be at least 8 characters long and contain
     * at least one letter and one digit.
     *
     * @param username the password to be validated (though the parameter name
     * might be misleading, it represents a password)
     * @return true if the password is valid, false otherwise
     */
    public static boolean isPasswordValid(String username) {
        return username.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
        /*
        ^                   Start of the string	✅ "Password1"
        (?=.*[A-Za-z])      At least one letter (A-Z or a-z)	✅ "abc123"
        (?=.*\d)        	At least one digit (0-9)	✅ "Password1"
        [A-Za-z\d]{8,}      At least 8 characters (only letters and numbers allowed)	✅ "abc12345"
        $                   End of the string	✅ "secure123"
         */
    }
}
