package com.org.skillzag.service.dto;

import java.time.Instant;
import java.io.Serializable;
import com.org.skillzag.domain.enumeration.CourseStatus;
import com.org.skillzag.domain.enumeration.RecommendedStatus;

/**
 * A DTO for the {@link com.org.skillzag.domain.CoursesManagement} entity.
 */
public class CoursesManagementDTO implements Serializable {
    
    private Long id;

    private Integer courseId;

    private String courseName;

    private Instant validFrom;

    private Instant validTo;

    private String imagePath;

    private String videoUrl;

    private String courseDescription;

    private String courseObjective;

    private String url1;

    private String url2;

    private String url3;

    private String quizb4Course;

    private String quiza4Course;

    private CourseStatus courseStatus;

    private RecommendedStatus recommendedStatus;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Instant getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Instant validFrom) {
        this.validFrom = validFrom;
    }

    public Instant getValidTo() {
        return validTo;
    }

    public void setValidTo(Instant validTo) {
        this.validTo = validTo;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getCourseObjective() {
        return courseObjective;
    }

    public void setCourseObjective(String courseObjective) {
        this.courseObjective = courseObjective;
    }

    public String getUrl1() {
        return url1;
    }

    public void setUrl1(String url1) {
        this.url1 = url1;
    }

    public String getUrl2() {
        return url2;
    }

    public void setUrl2(String url2) {
        this.url2 = url2;
    }

    public String getUrl3() {
        return url3;
    }

    public void setUrl3(String url3) {
        this.url3 = url3;
    }

    public String getQuizb4Course() {
        return quizb4Course;
    }

    public void setQuizb4Course(String quizb4Course) {
        this.quizb4Course = quizb4Course;
    }

    public String getQuiza4Course() {
        return quiza4Course;
    }

    public void setQuiza4Course(String quiza4Course) {
        this.quiza4Course = quiza4Course;
    }

    public CourseStatus getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(CourseStatus courseStatus) {
        this.courseStatus = courseStatus;
    }

    public RecommendedStatus getRecommendedStatus() {
        return recommendedStatus;
    }

    public void setRecommendedStatus(RecommendedStatus recommendedStatus) {
        this.recommendedStatus = recommendedStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CoursesManagementDTO)) {
            return false;
        }

        return id != null && id.equals(((CoursesManagementDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CoursesManagementDTO{" +
            "id=" + getId() +
            ", courseId=" + getCourseId() +
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
            "}";
    }
}
