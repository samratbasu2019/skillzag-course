package com.org.skillzag.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A UserActivity.
 */
@Entity
@Table(name = "user_activity")
public class UserActivity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "institute_id")
    private Long instituteId;

    @Column(name = "enrollment_status")
    private String enrollmentStatus;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "subscription_id")
    private Long subscriptionId;

    @Column(name = "activity_date")
    private Instant activityDate;

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

    public UserActivity courseId(Long courseId) {
        this.courseId = courseId;
        return this;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getInstituteId() {
        return instituteId;
    }

    public UserActivity instituteId(Long instituteId) {
        this.instituteId = instituteId;
        return this;
    }

    public void setInstituteId(Long instituteId) {
        this.instituteId = instituteId;
    }

    public String getEnrollmentStatus() {
        return enrollmentStatus;
    }

    public UserActivity enrollmentStatus(String enrollmentStatus) {
        this.enrollmentStatus = enrollmentStatus;
        return this;
    }

    public void setEnrollmentStatus(String enrollmentStatus) {
        this.enrollmentStatus = enrollmentStatus;
    }

    public String getUserId() {
        return userId;
    }

    public UserActivity userId(String userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getSubscriptionId() {
        return subscriptionId;
    }

    public UserActivity subscriptionId(Long subscriptionId) {
        this.subscriptionId = subscriptionId;
        return this;
    }

    public void setSubscriptionId(Long subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public Instant getActivityDate() {
        return activityDate;
    }

    public UserActivity activityDate(Instant activityDate) {
        this.activityDate = activityDate;
        return this;
    }

    public void setActivityDate(Instant activityDate) {
        this.activityDate = activityDate;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserActivity)) {
            return false;
        }
        return id != null && id.equals(((UserActivity) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserActivity{" +
            "id=" + getId() +
            ", courseId=" + getCourseId() +
            ", instituteId=" + getInstituteId() +
            ", enrollmentStatus='" + getEnrollmentStatus() + "'" +
            ", userId='" + getUserId() + "'" +
            ", subscriptionId=" + getSubscriptionId() +
            ", activityDate='" + getActivityDate() + "'" +
            "}";
    }
}
