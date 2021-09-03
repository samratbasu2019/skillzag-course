package com.org.skillzag.service.dto;

import java.time.Instant;
import java.io.Serializable;

/**
 * A DTO for the {@link com.org.skillzag.domain.Payment} entity.
 */
public class PaymentDTO implements Serializable {
    
    private Long id;

    private Long courseId;

    private Long userId;

    private Long subcriptionId;

    private Instant cartUsedDate;

    private String paymentStatus;

    private Double amount;

    private Instant paymentDate;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSubcriptionId() {
        return subcriptionId;
    }

    public void setSubcriptionId(Long subcriptionId) {
        this.subcriptionId = subcriptionId;
    }

    public Instant getCartUsedDate() {
        return cartUsedDate;
    }

    public void setCartUsedDate(Instant cartUsedDate) {
        this.cartUsedDate = cartUsedDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Instant getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Instant paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PaymentDTO)) {
            return false;
        }

        return id != null && id.equals(((PaymentDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PaymentDTO{" +
            "id=" + getId() +
            ", courseId=" + getCourseId() +
            ", userId=" + getUserId() +
            ", subcriptionId=" + getSubcriptionId() +
            ", cartUsedDate='" + getCartUsedDate() + "'" +
            ", paymentStatus='" + getPaymentStatus() + "'" +
            ", amount=" + getAmount() +
            ", paymentDate='" + getPaymentDate() + "'" +
            "}";
    }
}
