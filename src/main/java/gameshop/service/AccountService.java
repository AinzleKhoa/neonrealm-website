/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameshop.service;

import gameshop.DAO.AccountDAO;
import gameshop.model.Account;

import java.time.LocalDateTime;

/**
 * AccountService class provides services related to user account management,
 * such as saving password reset tokens, validating reset tokens, and updating
 * passwords using reset tokens. It interacts with the AccountDAO to perform
 * database operations for account-related tasks.
 *
 * @author CE171450 - Nguyen Hai Nam
 */
public class AccountService {

    private final AccountDAO accountDAO;

    /**
     * Constructor for AccountService. Initializes the AccountDAO to handle
     * account-related database operations.
     */
    public AccountService() {
        this.accountDAO = new AccountDAO(); // Đảm bảo AccountDAO được khởi tạo
    }

    /**
     * Saves the password reset token into the database and sets its expiry
     * time.
     *
     * @param email the email of the user requesting the password reset
     * @param token the generated password reset token
     * @return true if the token was successfully saved, false otherwise
     */
    public boolean savePasswordResetToken(String email, String token) {
        Account account = accountDAO.getAccountByEmail(email);
        if (account != null) {
            account.setResetToken(token);
            account.setTokenExpiry(LocalDateTime.now().plusMinutes(30)); // Token hết hạn sau 30 phút
            return accountDAO.updateAccount(account);
        }
        return false;
    }

    /**
     * Checks if the provided reset token is valid. A valid token is one that
     * exists and has not expired.
     *
     * @param token the password reset token to check
     * @return true if the token is valid, false otherwise
     */
    public boolean isValidResetToken(String token) {
        Account account = accountDAO.getAccountByToken(token);
        return account != null && account.getTokenExpiry() != null && account.getTokenExpiry().isAfter(LocalDateTime.now());
    }

    /**
     * Updates the password for the user associated with the provided reset
     * token. The token must be valid, and the new password must be provided.
     *
     * @param token the password reset token
     * @param newPassword the new password to set
     * @return true if the password was successfully updated, false otherwise
     */
    public boolean updatePasswordByToken(String token, String newPassword) {
        if (token == null || newPassword == null) {
            System.out.println("⚠️ Token hoặc mật khẩu mới bị null!");
            return false;
        }

        Account account = accountDAO.getAccountByToken(token);
        if (account == null) {
            System.out.println("⚠️ Không tìm thấy tài khoản với token: " + token);
            return false;
        }

        if (!isValidResetToken(token)) {
            System.out.println("❌ Token không hợp lệ hoặc đã hết hạn.");
            return false;
        }

        account.setPasswordHash(newPassword); // Mã hóa mật khẩu trước khi lưu
        account.setResetToken(null); // Xóa token sau khi đổi mật khẩu
        account.setTokenExpiry(null);

        return accountDAO.updateAccount(account);
    }

}
