/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameshop.model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Model class representing a coupon entity for admin operations in the game
 * shop system.
 *
 * @author Pham Van Hoai - CE181744
 */
public class AdminCoupons {

    private int couponId;            // Unique identifier for the coupon
    private String code;             // Coupon code (e.g., "SAVE10")
    private int discountPercentage;  // Discount percentage offered by the coupon (1-100)
    private Date expirationDate;     // Date when the coupon expires
    private int usageLimit;          // Maximum number of times the coupon can be used
    private Timestamp createdAt;     // Timestamp of when the coupon was created

    /**
     * Default no-argument constructor for creating an empty AdminCoupons
     * object.
     */
    public AdminCoupons() {
    }

    /**
     * Parameterized constructor to initialize an AdminCoupons object with all
     * fields.
     *
     * @param couponId The unique ID of the coupon
     * @param code The coupon code
     * @param discountPercentage The discount percentage (1-100)
     * @param expirationDate The expiration date of the coupon
     * @param usageLimit The maximum number of uses allowed
     * @param createdAt The timestamp of creation
     */
    public AdminCoupons(int couponId, String code, int discountPercentage, Date expirationDate, int usageLimit, Timestamp createdAt) {
        this.couponId = couponId;
        this.code = code;
        this.discountPercentage = discountPercentage;
        this.expirationDate = expirationDate;
        this.usageLimit = usageLimit;
        this.createdAt = createdAt;
    }

    /**
     * Gets the coupon ID.
     *
     * @return The unique identifier of the coupon
     */
    public int getCouponId() {
        return couponId;
    }

    /**
     * Sets the coupon ID.
     *
     * @param couponId The unique identifier to set
     */
    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    /**
     * Gets the coupon code.
     *
     * @return The coupon code string
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the coupon code.
     *
     * @param code The coupon code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the discount percentage.
     *
     * @return The discount percentage (1-100)
     */
    public int getDiscountPercentage() {
        return discountPercentage;
    }

    /**
     * Sets the discount percentage.
     *
     * @param discountPercentage The discount percentage to set (1-100)
     */
    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    /**
     * Gets the expiration date of the coupon.
     *
     * @return The expiration date
     */
    public Date getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets the expiration date of the coupon.
     *
     * @param expirationDate The expiration date to set
     */
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * Gets the usage limit of the coupon.
     *
     * @return The maximum number of times the coupon can be used
     */
    public int getUsageLimit() {
        return usageLimit;
    }

    /**
     * Sets the usage limit of the coupon.
     *
     * @param usageLimit The maximum number of uses to set
     */
    public void setUsageLimit(int usageLimit) {
        this.usageLimit = usageLimit;
    }

    /**
     * Gets the creation timestamp of the coupon.
     *
     * @return The timestamp when the coupon was created
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation timestamp of the coupon.
     *
     * @param createdAt The timestamp to set for creation
     */
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
