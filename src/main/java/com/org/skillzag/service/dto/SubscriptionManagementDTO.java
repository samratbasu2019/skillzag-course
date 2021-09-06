package com.org.skillzag.service.dto;

import java.time.Instant;
import java.io.Serializable;
import com.org.skillzag.domain.enumeration.BlockSubscription;

/**
 * A DTO for the {@link com.org.skillzag.domain.SubscriptionManagement} entity.
 */
public class SubscriptionManagementDTO implements Serializable {
    
    private Long id;

    private String userId;

    private String subscriptionType;

    private String subscriptionName;

    private String subscriptionDescription;

    private String subscriptionStatus;

    private Instant subscriptionStartdate;

    private Instant subscriptionEnddate;

    private String paymentStatus;

    private Instant subscriptionDate;

    private Double amount;

    private Double discountPercentage;

    private String type;

    private BlockSubscription blockSubscription;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public String getSubscriptionName() {
        return subscriptionName;
    }

    public void setSubscriptionName(String subscriptionName) {
        this.subscriptionName = subscriptionName;
    }

    public String getSubscriptionDescription() {
        return subscriptionDescription;
    }

    public void setSubscriptionDescription(String subscriptionDescription) {
        this.subscriptionDescription = subscriptionDescription;
    }

    public String getSubscriptionStatus() {
        return subscriptionStatus;
    }

    public void setSubscriptionStatus(String subscriptionStatus) {
        this.subscriptionStatus = subscriptionStatus;
    }

    public Instant getSubscriptionStartdate() {
        return subscriptionStartdate;
    }

    public void setSubscriptionStartdate(Instant subscriptionStartdate) {
        this.subscriptionStartdate = subscriptionStartdate;
    }

    public Instant getSubscriptionEnddate() {
        return subscriptionEnddate;
    }

    public void setSubscriptionEnddate(Instant subscriptionEnddate) {
        this.subscriptionEnddate = subscriptionEnddate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Instant getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Instant subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BlockSubscription getBlockSubscription() {
        return blockSubscription;
    }

    public void setBlockSubscription(BlockSubscription blockSubscription) {
        this.blockSubscription = blockSubscription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SubscriptionManagementDTO)) {
            return false;
        }

        return id != null && id.equals(((SubscriptionManagementDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SubscriptionManagementDTO{" +
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
