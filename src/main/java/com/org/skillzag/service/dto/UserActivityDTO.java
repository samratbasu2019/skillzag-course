package com.org.skillzag.service.dto;

import java.time.Instant;
import java.io.Serializable;

/**
 * A DTO for the {@link com.org.skillzag.domain.UserActivity} entity.
 */
public class UserActivityDTO implements Serializable {
    
    private Long id;

    private Long courseId;

    private String enrollmentStatus;

    private String userId;

    private Long subscriptionId;

    private Instant activityDate;

    
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

    public String getEnrollmentStatus() {
        return enrollmentStatus;
    }

    public void setEnrollmentStatus(String enrollmentStatus) {
        this.enrollmentStatus = enrollmentStatus;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(Long subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public Instant getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Instant activityDate) {
        this.activityDate = activityDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserActivityDTO)) {
            return false;
        }

        return id != null && id.equals(((UserActivityDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserActivityDTO{" +
            "id=" + getId() +
            ", courseId=" + getCourseId() +
            ", enrollmentStatus='" + getEnrollmentStatus() + "'" +
            ", userId='" + getUserId() + "'" +
            ", subscriptionId=" + getSubscriptionId() +
            ", activityDate='" + getActivityDate() + "'" +
            "}";
    }
}
