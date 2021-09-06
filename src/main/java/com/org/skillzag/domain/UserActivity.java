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

    @Column(name = "enrollment")
    private String enrollment;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "subcription_id")
    private Long subcriptionId;

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

    public String getEnrollment() {
        return enrollment;
    }

    public UserActivity enrollment(String enrollment) {
        this.enrollment = enrollment;
        return this;
    }

    public void setEnrollment(String enrollment) {
        this.enrollment = enrollment;
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

    public Long getSubcriptionId() {
        return subcriptionId;
    }

    public UserActivity subcriptionId(Long subcriptionId) {
        this.subcriptionId = subcriptionId;
        return this;
    }

    public void setSubcriptionId(Long subcriptionId) {
        this.subcriptionId = subcriptionId;
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
            ", enrollment='" + getEnrollment() + "'" +
            ", userId='" + getUserId() + "'" +
            ", subcriptionId=" + getSubcriptionId() +
            ", activityDate='" + getActivityDate() + "'" +
            "}";
    }
}
