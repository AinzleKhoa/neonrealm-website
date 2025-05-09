package gameshop.model;

/**
 * GoogleUser class represents a user who logs in via Google. It contains
 * details such as email, name, and avatar.
 *
 * @author CE171450 - Nguyen Hai Nam
 */
public class GoogleUser {

    private String email;
    private String name;
    private String avatar;

    /**
     * Constructor to create a GoogleUser object with email, name, and avatar.
     *
     * @param email the email of the Google user
     * @param name the name of the Google user
     * @param avatar the avatar URL of the Google user
     */
    public GoogleUser(String email, String name, String avatar) {
        this.email = email;
        this.name = name;
        this.avatar = avatar;
    }

    /**
     * Gets the email of the Google user.
     *
     * @return the email of the Google user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the name of the Google user.
     *
     * @return the name of the Google user
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the avatar of the Google user.
     *
     * @return the avatar URL of the Google user
     */
    public String getAvatar() {
        return avatar;
    }
}
