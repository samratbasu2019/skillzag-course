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

    @Column(name = "user_id")
    private String userId;

    @Column(name = "subscription_type")
    private String subscriptionType;

    @Column(name = "subscription_name")
    private String subscriptionName;

    @Column(name = "subscription_description")
    private String subscriptionDescription;

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

    @Column(name = "amount")
    private Double amount;

    @Column(name = "discount_percentage")
    private Double discountPercentage;

    @Column(name = "type")
    private String type;

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

    public String getUserId() {
        return userId;
    }

    public SubscriptionManagement userId(String userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(String userId) {
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

    public String getSubscriptionName() {
        return subscriptionName;
    }

    public SubscriptionManagement subscriptionName(String subscriptionName) {
        this.subscriptionName = subscriptionName;
        return this;
    }

    public void setSubscriptionName(String subscriptionName) {
        this.subscriptionName = subscriptionName;
    }

    public String getSubscriptionDescription() {
        return subscriptionDescription;
    }

    public SubscriptionManagement subscriptionDescription(String subscriptionDescription) {
        this.subscriptionDescription = subscriptionDescription;
        return this;
    }

    public void setSubscriptionDescription(String subscriptionDescription) {
        this.subscriptionDescription = subscriptionDescription;
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

    public Double getAmount() {
        return amount;
    }

    public SubscriptionManagement amount(Double amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getDiscountPercentage() {
        return discountPercentage;
    }

    public SubscriptionManagement discountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
        return this;
    }

    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public String getType() {
        return type;
    }

    public SubscriptionManagement type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
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
            ", userId='" + getUserId() + "'" +
            ", subscriptionType='" + getSubscriptionType() + "'" +
            ", subscriptionName='" + getSubscriptionName() + "'" +
            ", subscriptionDescription='" + getSubscriptionDescription() + "'" +
            ", subscriptionStatus='" + getSubscriptionStatus() + "'" +
            ", subscriptionStartdate='" + getSubscriptionStartdate() + "'" +
            ", subscriptionEnddate='" + getSubscriptionEnddate() + "'" +
            ", paymentStatus='" + getPaymentStatus() + "'" +
            ", subscriptionDate='" + getSubscriptionDate() + "'" +
            ", amount=" + getAmount() +
            ", discountPercentage=" + getDiscountPercentage() +
            ", type='" + getType() + "'" +
            ", blockSubscription='" + getBlockSubscription() + "'" +
            "}";
    }
}
