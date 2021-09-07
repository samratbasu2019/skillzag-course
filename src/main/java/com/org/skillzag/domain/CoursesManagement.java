package com.org.skillzag.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.org.skillzag.domain.enumeration.CourseStatus;

import com.org.skillzag.domain.enumeration.RecommendedStatus;

/**
 * A CoursesManagement.
 */
@Entity
@Table(name = "courses_management")
public class CoursesManagement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "valid_from")
    private Instant validFrom;

    @Column(name = "valid_to")
    private Instant validTo;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "video_url")
    private String videoUrl;

    @Column(name = "course_description")
    private String courseDescription;

    @Column(name = "course_objective")
    private String courseObjective;

    @Column(name = "url_1")
    private String url1;

    @Column(name = "url_2")
    private String url2;

    @Column(name = "url_3")
    private String url3;

    @Column(name = "quizb_4_course")
    private String quizb4Course;

    @Column(name = "quiza_4_course")
    private String quiza4Course;

    @Enumerated(EnumType.STRING)
    @Column(name = "course_status")
    private CourseStatus courseStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "recommended_status")
    private RecommendedStatus recommendedStatus;

    @Column(name = "subscription_id")
    private Long subscriptionId;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "creation_date")
    private Instant creationDate;

    @OneToMany(mappedBy = "coursesManagement")
    private Set<SessionManagement> sessionManagements = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public CoursesManagement courseName(String courseName) {
        this.courseName = courseName;
        return this;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Instant getValidFrom() {
        return validFrom;
    }

    public CoursesManagement validFrom(Instant validFrom) {
        this.validFrom = validFrom;
        return this;
    }

    public void setValidFrom(Instant validFrom) {
        this.validFrom = validFrom;
    }

    public Instant getValidTo() {
        return validTo;
    }

    public CoursesManagement validTo(Instant validTo) {
        this.validTo = validTo;
        return this;
    }

    public void setValidTo(Instant validTo) {
        this.validTo = validTo;
    }

    public String getImagePath() {
        return imagePath;
    }

    public CoursesManagement imagePath(String imagePath) {
        this.imagePath = imagePath;
        return this;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public CoursesManagement videoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public CoursesManagement courseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
        return this;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getCourseObjective() {
        return courseObjective;
    }

    public CoursesManagement courseObjective(String courseObjective) {
        this.courseObjective = courseObjective;
        return this;
    }

    public void setCourseObjective(String courseObjective) {
        this.courseObjective = courseObjective;
    }

    public String getUrl1() {
        return url1;
    }

    public CoursesManagement url1(String url1) {
        this.url1 = url1;
        return this;
    }

    public void setUrl1(String url1) {
        this.url1 = url1;
    }

    public String getUrl2() {
        return url2;
    }

    public CoursesManagement url2(String url2) {
        this.url2 = url2;
        return this;
    }

    public void setUrl2(String url2) {
        this.url2 = url2;
    }

    public String getUrl3() {
        return url3;
    }

    public CoursesManagement url3(String url3) {
        this.url3 = url3;
        return this;
    }

    public void setUrl3(String url3) {
        this.url3 = url3;
    }

    public String getQuizb4Course() {
        return quizb4Course;
    }

    public CoursesManagement quizb4Course(String quizb4Course) {
        this.quizb4Course = quizb4Course;
        return this;
    }

    public void setQuizb4Course(String quizb4Course) {
        this.quizb4Course = quizb4Course;
    }

    public String getQuiza4Course() {
        return quiza4Course;
    }

    public CoursesManagement quiza4Course(String quiza4Course) {
        this.quiza4Course = quiza4Course;
        return this;
    }

    public void setQuiza4Course(String quiza4Course) {
        this.quiza4Course = quiza4Course;
    }

    public CourseStatus getCourseStatus() {
        return courseStatus;
    }

    public CoursesManagement courseStatus(CourseStatus courseStatus) {
        this.courseStatus = courseStatus;
        return this;
    }

    public void setCourseStatus(CourseStatus courseStatus) {
        this.courseStatus = courseStatus;
    }

    public RecommendedStatus getRecommendedStatus() {
        return recommendedStatus;
    }

    public CoursesManagement recommendedStatus(RecommendedStatus recommendedStatus) {
        this.recommendedStatus = recommendedStatus;
        return this;
    }

    public void setRecommendedStatus(RecommendedStatus recommendedStatus) {
        this.recommendedStatus = recommendedStatus;
    }

    public Long getSubscriptionId() {
        return subscriptionId;
    }

    public CoursesManagement subscriptionId(Long subscriptionId) {
        this.subscriptionId = subscriptionId;
        return this;
    }

    public void setSubscriptionId(Long subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public CoursesManagement createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public CoursesManagement creationDate(Instant creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public Set<SessionManagement> getSessionManagements() {
        return sessionManagements;
    }

    public CoursesManagement sessionManagements(Set<SessionManagement> sessionManagements) {
        this.sessionManagements = sessionManagements;
        return this;
    }

    public CoursesManagement addSessionManagement(SessionManagement sessionManagement) {
        this.sessionManagements.add(sessionManagement);
        sessionManagement.setCoursesManagement(this);
        return this;
    }

    public CoursesManagement removeSessionManagement(SessionManagement sessionManagement) {
        this.sessionManagements.remove(sessionManagement);
        sessionManagement.setCoursesManagement(null);
        return this;
    }

    public void setSessionManagements(Set<SessionManagement> sessionManagements) {
        this.sessionManagements = sessionManagements;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CoursesManagement)) {
            return false;
        }
        return id != null && id.equals(((CoursesManagement) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CoursesManagement{" +
            "id=" + getId() +
            ", courseName='" + getCourseName() + "'" +
            ", validFrom='" + getValidFrom() + "'" +
            ", validTo='" + getValidTo() + "'" +
            ", imagePath='" + getImagePath() + "'" +
            ", videoUrl='" + getVideoUrl() + "'" +
            ", courseDescription='" + getCourseDescription() + "'" +
            ", courseObjective='" + getCourseObjective() + "'" +
            ", url1='" + getUrl1() + "'" +
            ", url2='" + getUrl2() + "'" +
            ", url3='" + getUrl3() + "'" +
            ", quizb4Course='" + getQuizb4Course() + "'" +
            ", quiza4Course='" + getQuiza4Course() + "'" +
            ", courseStatus='" + getCourseStatus() + "'" +
            ", recommendedStatus='" + getRecommendedStatus() + "'" +
            ", subscriptionId=" + getSubscriptionId() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            "}";
    }
}
