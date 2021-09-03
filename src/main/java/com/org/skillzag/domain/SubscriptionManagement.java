package com.org.skillzag.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

import com.org.skillzag.domain.enumeration.BlockSubscription;

/**
 * A SubscriptionManagement.
 */
@Entity
@Table(name = "subscription_management")
public class SubscriptionManagement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "subscription_type")
    private String subscriptionType;

    @Column(name = "subscription_status")
    private String subscriptionStatus;

    @Column(name = "subscription_startdate")
    private Instant subscriptionStartdate;

    @Column(name = "subscription_enddate")
    private Instant subscriptionEnddate;

    @Column(name = "payment_status")
    private String paymentStatus;

    @Column(name = "subscription_date")
    private Instant subscriptionDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "block_subscription")
    private BlockSubscription blockSubscription;

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

    public SubscriptionManagement courseId(Long courseId) {
        this.courseId = courseId;
        return this;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getUserId() {
        return userId;
    }

    public SubscriptionManagement userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public SubscriptionManagement subscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
        return this;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public String getSubscriptionStatus() {
        return subscriptionStatus;
    }

    public SubscriptionManagement subscriptionStatus(String subscriptionStatus) {
        this.subscriptionStatus = subscriptionStatus;
        return this;
    }

    public void setSubscriptionStatus(String subscriptionStatus) {
        this.subscriptionStatus = subscriptionStatus;
    }

    public Instant getSubscriptionStartdate() {
        return subscriptionStartdate;
    }

    public SubscriptionManagement subscriptionStartdate(Instant subscriptionStartdate) {
        this.subscriptionStartdate = subscriptionStartdate;
        return this;
    }

    public void setSubscriptionStartdate(Instant subscriptionStartdate) {
        this.subscriptionStartdate = subscriptionStartdate;
    }

    public Instant getSubscriptionEnddate() {
        return subscriptionEnddate;
    }

    public SubscriptionManagement subscriptionEnddate(Instant subscriptionEnddate) {
        this.subscriptionEnddate = subscriptionEnddate;
        return this;
    }

    public void setSubscriptionEnddate(Instant subscriptionEnddate) {
        this.subscriptionEnddate = subscriptionEnddate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public SubscriptionManagement paymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
        return this;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Instant getSubscriptionDate() {
        return subscriptionDate;
    }

    public SubscriptionManagement subscriptionDate(Instant subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
        return this;
    }

    public void setSubscriptionDate(Instant subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public BlockSubscription getBlockSubscription() {
        return blockSubscription;
    }

    public SubscriptionManagement blockSubscription(BlockSubscription blockSubscription) {
        this.blockSubscription = blockSubscription;
        return this;
    }

    public void setBlockSubscription(BlockSubscription blockSubscription) {
        this.blockSubscription = blockSubscription;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SubscriptionManagement)) {
            return false;
        }
        return id != null && id.equals(((SubscriptionManagement) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SubscriptionManagement{" +
            "id=" + getId() +
            ", courseId=" + getCourseId() +
            ", userId=" + getUserId() +
            ", subscriptionType='" + getSubscriptionType() + "'" +
            ", subscriptionStatus='" + getSubscriptionStatus() + "'" +
            ", subscriptionStartdate='" + getSubscriptionStartdate() + "'" +
            ", subscriptionEnddate='" + getSubscriptionEnddate() + "'" +
            ", paymentStatus='" + getPaymentStatus() + "'" +
            ", subscriptionDate='" + getSubscriptionDate() + "'" +
            ", blockSubscription='" + getBlockSubscription() + "'" +
            "}";
    }
}
