package gameshop.service;

import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.auth.oauth2.AuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import gameshop.model.GoogleUser;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

/**
 * GoogleOAuthService handles the OAuth 2.0 authentication process for Google
 * accounts. It includes methods to retrieve access tokens and user information
 * based on the authorization code and ID token from Google.
 *
 * @author CE171450 - Nguyen Hai Nam
 */
public class GoogleOAuthService {

    private static final String CLIENT_ID = System.getenv("GOOGLE_CLIENT_ID");
    private static final String CLIENT_SECRET = System.getenv("GOOGLE_CLIENT_SECRET");
    private static final String REDIRECT_URI = "http://localhost:8080/NeonRealm/google-callback";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    /**
     * Gets the access token from the authorization code provided by Google
     * OAuth.
     *
     * @param code the authorization code received from Google OAuth
     * @return the GoogleTokenResponse containing the access token
     * @throws IOException if an error occurs during the token request
     */
    public GoogleTokenResponse getTokens(String code) throws IOException {
        return new GoogleAuthorizationCodeTokenRequest(
                new NetHttpTransport(),
                JSON_FACTORY,
                "https://oauth2.googleapis.com/token",
                CLIENT_ID,
                CLIENT_SECRET,
                code,
                REDIRECT_URI)
                .execute();
    }

    /**
     * Retrieves user information from the provided ID token.
     *
     * @param idTokenString the ID token received from Google OAuth
     * @return a GoogleUser object containing the user's email, name, and avatar
     * URL
     * @throws IOException if an error occurs during the verification process
     * @throws GeneralSecurityException if an error occurs with the ID token
     * verification
     */
    public GoogleUser getUserInfo(String idTokenString) throws IOException, GeneralSecurityException {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                new NetHttpTransport(), JSON_FACTORY)
                .setAudience(Collections.singletonList(CLIENT_ID))
                .build();

        GoogleIdToken idToken = verifier.verify(idTokenString);
        if (idToken == null) {
            throw new IllegalArgumentException("Invalid ID token.");
        }

        GoogleIdToken.Payload payload = idToken.getPayload();
        return new GoogleUser(
                payload.getEmail(),
                (String) payload.get("name"),
                (String) payload.get("picture")
        );
    }

}
