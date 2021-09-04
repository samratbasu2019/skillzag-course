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

    @Column(name = "subcription_id")
    private Long subcriptionId;

    @Column(name = "checkout_date")
    private Instant checkoutDate;

    @Column(name = "check_out_status")
    private String checkOutStatus;

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

    public Long getSubcriptionId() {
        return subcriptionId;
    }

    public Cart subcriptionId(Long subcriptionId) {
        this.subcriptionId = subcriptionId;
        return this;
    }

    public void setSubcriptionId(Long subcriptionId) {
        this.subcriptionId = subcriptionId;
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
            ", userId=" + getUserId() +
            ", subcriptionId=" + getSubcriptionId() +
            ", checkoutDate='" + getCheckoutDate() + "'" +
            ", checkOutStatus='" + getCheckOutStatus() + "'" +
            "}";
    }
}
