package gameshop.model;

import java.time.LocalDate;

/**
 * Coupon class represents a discount coupon in the system. It contains details
 * such as the coupon ID, code, discount percentage, expiration date, usage
 * limit, and the creation date of the coupon.
 *
 * @author CE180035 - Nguyen Huynh Nhat Thien
 */
public class Coupon {

    private int couponId;
    private String couponCode;
    private int discountPercentage;
    private LocalDate expirationDate;
    private int usageLimit;
    private LocalDate createAt;

    /**
     * Gets the unique identifier for the coupon.
     *
     * @return the coupon ID
     */
    public int getCouponId() {
        return couponId;
    }

    /**
     * Sets the unique identifier for the coupon.
     *
     * @param couponId the coupon ID to set
     */
    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    /**
     * Gets the coupon code used for redeeming the discount.
     *
     * @return the coupon code
     */
    public String getCouponCode() {
        return couponCode;
    }

    /**
     * Sets the coupon code used for redeeming the discount.
     *
     * @param couponCode the coupon code to set
     */
    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    /**
     * Gets the discount percentage offered by the coupon.
     *
     * @return the discount percentage
     */
    public int getDiscountPercentage() {
        return discountPercentage;
    }

    /**
     * Sets the discount percentage offered by the coupon.
     *
     * @param discountPercentage the discount percentage to set
     */
    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    /**
     * Gets the expiration date of the coupon.
     *
     * @return the expiration date of the coupon
     */
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets the expiration date of the coupon.
     *
     * @param expirationDate the expiration date to set
     */
    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * Gets the usage limit of the coupon.
     *
     * @return the usage limit of the coupon
     */
    public int getUsageLimit() {
        return usageLimit;
    }

    /**
     * Sets the usage limit of the coupon.
     *
     * @param usageLimit the usage limit to set
     */
    public void setUsageLimit(int usageLimit) {
        this.usageLimit = usageLimit;
    }

    /**
     * Gets the creation date of the coupon.
     *
     * @return the creation date of the coupon
     */
    public LocalDate getCreateAt() {
        return createAt;
    }

    /**
     * Sets the creation date of the coupon.
     *
     * @param createAt the creation date to set
     */
    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    /**
     * Constructor for creating a Coupon object with coupon ID, code, discount
     * percentage, expiration date, and usage limit.
     *
     * @param couponId the unique identifier for the coupon
     * @param couponCode the coupon code
     * @param discountPercentage the discount percentage of the coupon
     * @param expirationDate the expiration date of the coupon
     * @param usageLimit the usage limit of the coupon
     */
    public Coupon(int couponId, String couponCode, int discountPercentage, LocalDate expirationDate, int usageLimit) {
        this.couponId = couponId;
        this.couponCode = couponCode;
        this.discountPercentage = discountPercentage;
        this.expirationDate = expirationDate;
        this.usageLimit = usageLimit;
    }

    /**
     * Constructor for creating a Coupon object with coupon ID, code, discount
     * percentage, expiration date, usage limit, and creation date.
     *
     * @param couponId the unique identifier for the coupon
     * @param couponCode the coupon code
     * @param discountPercentage the discount percentage of the coupon
     * @param expirationDate the expiration date of the coupon
     * @param usageLimit the usage limit of the coupon
     * @param createAt the creation date of the coupon
     */
    public Coupon(int couponId, String couponCode, int discountPercentage, LocalDate expirationDate, int usageLimit, LocalDate createAt) {
        this.couponId = couponId;
        this.couponCode = couponCode;
        this.discountPercentage = discountPercentage;
        this.expirationDate = expirationDate;
        this.usageLimit = usageLimit;
        this.createAt = createAt;
    }
}
