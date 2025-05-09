package gameshop.DAO;

import gameshop.model.Coupon;
import gameshop.db.DBContext;
import gameshop.db.DBContext;
import java.sql.*;

/**
 * CouponDAO class is responsible for handling database operations related to
 * coupons. It provides methods to retrieve and update coupon information.
 *
 * @author CE180035 - Nguyen Huynh Nhat Thien
 */
public class CouponDAO {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public CouponDAO() {
        try {
            conn = new DBContext().getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a valid coupon from the database based on the coupon code. The
     * coupon must not be expired and should have remaining usage limits.
     *
     * @param couponCode the code of the coupon to retrieve
     * @return a Coupon object if the coupon is valid, or null if no valid
     * coupon is found
     */
    public Coupon getValidCoupon(String couponCode) {
        String query = "SELECT * FROM Coupons WHERE code = ? AND expiration_date >= GETDATE() AND usage_limit > 0";
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, couponCode);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Coupon(
                        rs.getInt("coupon_id"),
                        rs.getString("code"),
                        rs.getInt("discount_percentage"),
                        rs.getDate("expiration_date").toLocalDate(),
                        rs.getInt("usage_limit"),
                        rs.getDate("created_at").toLocalDate()
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Decreases the usage limit of a coupon by 1. The coupon's usage limit must
     * be greater than 0.
     *
     * @param couponCode the code of the coupon to update
     * @return true if the usage limit was successfully decremented, false
     * otherwise
     */
    public boolean decrementUsageLimit(String couponCode) {
        String query = "UPDATE Coupons SET usage_limit = usage_limit - 1 WHERE code = ? AND usage_limit > 0";
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, couponCode);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
