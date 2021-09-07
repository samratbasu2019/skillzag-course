package com.org.skillzag.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A Cart.
 */
@Entity
@Table(name = "cart")
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "subscription_id")
    private Long subscriptionId;

    @Column(name = "promo_id")
    private Long promoId;

    @Column(name = "discount_percentage")
    private Double discountPercentage;

    @Column(name = "checkout_date")
    private Instant checkoutDate;

    @Column(name = "check_out_status")
    private String checkOutStatus;

    @Column(name = "creation_date")
    private Instant creationDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public Cart courseId(Long courseId) {
        this.courseId = courseId;
        return this;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getUserId() {
        return userId;
    }

    public Cart userId(String userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getSubscriptionId() {
        return subscriptionId;
    }

    public Cart subscriptionId(Long subscriptionId) {
        this.subscriptionId = subscriptionId;
        return this;
    }

    public void setSubscriptionId(Long subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public Long getPromoId() {
        return promoId;
    }

    public Cart promoId(Long promoId) {
        this.promoId = promoId;
        return this;
    }

    public void setPromoId(Long promoId) {
        this.promoId = promoId;
    }

    public Double getDiscountPercentage() {
        return discountPercentage;
    }

    public Cart discountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
        return this;
    }

    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Instant getCheckoutDate() {
        return checkoutDate;
    }

    public Cart checkoutDate(Instant checkoutDate) {
        this.checkoutDate = checkoutDate;
        return this;
    }

    public void setCheckoutDate(Instant checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public String getCheckOutStatus() {
        return checkOutStatus;
    }

    public Cart checkOutStatus(String checkOutStatus) {
        this.checkOutStatus = checkOutStatus;
        return this;
    }

    public void setCheckOutStatus(String checkOutStatus) {
        this.checkOutStatus = checkOutStatus;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public Cart creationDate(Instant creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cart)) {
            return false;
        }
        return id != null && id.equals(((Cart) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Cart{" +
            "id=" + getId() +
            ", courseId=" + getCourseId() +
            ", userId='" + getUserId() + "'" +
            ", subscriptionId=" + getSubscriptionId() +
            ", promoId=" + getPromoId() +
            ", discountPercentage=" + getDiscountPercentage() +
            ", checkoutDate='" + getCheckoutDate() + "'" +
            ", checkOutStatus='" + getCheckOutStatus() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            "}";
    }
}
