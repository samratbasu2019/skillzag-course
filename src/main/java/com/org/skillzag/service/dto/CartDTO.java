package com.org.skillzag.service.dto;

import java.time.Instant;
import java.io.Serializable;

/**
 * A DTO for the {@link com.org.skillzag.domain.Cart} entity.
 */
public class CartDTO implements Serializable {

    private Long id;

    private Long courseId;

    private String userId;

    private Long subcriptionId;

    private Instant checkoutDate;

    private String checkOutStatus;


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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getSubcriptionId() {
        return subcriptionId;
    }

    public void setSubcriptionId(Long subcriptionId) {
        this.subcriptionId = subcriptionId;
    }

    public Instant getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Instant checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public String getCheckOutStatus() {
        return checkOutStatus;
    }

    public void setCheckOutStatus(String checkOutStatus) {
        this.checkOutStatus = checkOutStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CartDTO)) {
            return false;
        }

        return id != null && id.equals(((CartDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CartDTO{" +
            "id=" + getId() +
            ", courseId=" + getCourseId() +
            ", userId=" + getUserId() +
            ", subcriptionId=" + getSubcriptionId() +
            ", checkoutDate='" + getCheckoutDate() + "'" +
            ", checkOutStatus='" + getCheckOutStatus() + "'" +
            "}";
    }
}
