/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameshop.util;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;

/**
 * Utility class for handling GitHub OAuth authentication and user information
 * retrieval.
 *
 * This class provides methods to exchange an authorization code for an access
 * token and to fetch user information from GitHub using the access token.
 *
 * @author Le Anh Khoa - CE190449
 */
public class GitHubOAuthUtil {

    private static final String CLIENT_ID = "Ov23liUhueVZV9HOxwYI";
    private static final String CLIENT_SECRET = "7435397b840236298a82998f47737b9c2af80104";
    private static final String TOKEN_URL = "https://github.com/login/oauth/access_token";
    private static final String USER_URL = "https://api.github.com/user";

    /**
     * Exchange authorization code for an access token.
     *
     * @param code Authorization code received from GitHub
     * @return Access token or null if failed
     * @throws java.io.IOException
     */
    public static String getAccessToken(String code) throws IOException {
        URL url = new URL(TOKEN_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Accept", "application/json");

        String requestBody = "client_id=" + CLIENT_ID
                + "&client_secret=" + CLIENT_SECRET
                + "&code=" + code;

        try ( OutputStream os = conn.getOutputStream()) {
            os.write(requestBody.getBytes());
        }

        Scanner scanner = new Scanner(conn.getInputStream());
        String response = scanner.useDelimiter("\\A").next();
        scanner.close();

        JSONObject json = new JSONObject(response);
        return json.optString("access_token", null);
    }

    /**
     * Fetch user info from GitHub using access token.
     *
     * @param accessToken GitHub OAuth access token
     * @return JSONObject containing user details
     * @throws java.io.IOException
     */
    public static JSONObject getUserInfo(String accessToken) throws IOException {
        URL url = new URL(USER_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", "Bearer " + accessToken);
        conn.setRequestProperty("Accept", "application/json");

        Scanner scanner = new Scanner(conn.getInputStream());
        String response = scanner.useDelimiter("\\A").next();
        scanner.close();

        return new JSONObject(response);
    }
}
