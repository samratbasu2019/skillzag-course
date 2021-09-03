package com.org.skillzag.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

import com.org.skillzag.domain.enumeration.SessionStatus;

import com.org.skillzag.domain.enumeration.SubscriptionRequired;

/**
 * A SessionManagement.
 */
@Entity
@Table(name = "session_management")
public class SessionManagement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "session_number")
    private Integer sessionNumber;

    @Column(name = "session_name")
    private String sessionName;

    @Column(name = "session_description")
    private String sessionDescription;

    @Column(name = "session_url")
    private String sessionUrl;

    @Column(name = "session_video_url")
    private String sessionVideoUrl;

    @Column(name = "quiz")
    private String quiz;

    @Column(name = "session_logo")
    private String sessionLogo;

    @Enumerated(EnumType.STRING)
    @Column(name = "session_status")
    private SessionStatus sessionStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "subscription_required")
    private SubscriptionRequired subscriptionRequired;

    @ManyToOne
    @JsonIgnoreProperties(value = "sessionManagements", allowSetters = true)
    private CoursesManagement coursesManagement;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSessionNumber() {
        return sessionNumber;
    }

    public SessionManagement sessionNumber(Integer sessionNumber) {
        this.sessionNumber = sessionNumber;
        return this;
    }

    public void setSessionNumber(Integer sessionNumber) {
        this.sessionNumber = sessionNumber;
    }

    public String getSessionName() {
        return sessionName;
    }

    public SessionManagement sessionName(String sessionName) {
        this.sessionName = sessionName;
        return this;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public String getSessionDescription() {
        return sessionDescription;
    }

    public SessionManagement sessionDescription(String sessionDescription) {
        this.sessionDescription = sessionDescription;
        return this;
    }

    public void setSessionDescription(String sessionDescription) {
        this.sessionDescription = sessionDescription;
    }

    public String getSessionUrl() {
        return sessionUrl;
    }

    public SessionManagement sessionUrl(String sessionUrl) {
        this.sessionUrl = sessionUrl;
        return this;
    }

    public void setSessionUrl(String sessionUrl) {
        this.sessionUrl = sessionUrl;
    }

    public String getSessionVideoUrl() {
        return sessionVideoUrl;
    }

    public SessionManagement sessionVideoUrl(String sessionVideoUrl) {
        this.sessionVideoUrl = sessionVideoUrl;
        return this;
    }

    public void setSessionVideoUrl(String sessionVideoUrl) {
        this.sessionVideoUrl = sessionVideoUrl;
    }

    public String getQuiz() {
        return quiz;
    }

    public SessionManagement quiz(String quiz) {
        this.quiz = quiz;
        return this;
    }

    public void setQuiz(String quiz) {
        this.quiz = quiz;
    }

    public String getSessionLogo() {
        return sessionLogo;
    }

    public SessionManagement sessionLogo(String sessionLogo) {
        this.sessionLogo = sessionLogo;
        return this;
    }

    public void setSessionLogo(String sessionLogo) {
        this.sessionLogo = sessionLogo;
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    public SessionManagement sessionStatus(SessionStatus sessionStatus) {
        this.sessionStatus = sessionStatus;
        return this;
    }

    public void setSessionStatus(SessionStatus sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

    public SubscriptionRequired getSubscriptionRequired() {
        return subscriptionRequired;
    }

    public SessionManagement subscriptionRequired(SubscriptionRequired subscriptionRequired) {
        this.subscriptionRequired = subscriptionRequired;
        return this;
    }

    public void setSubscriptionRequired(SubscriptionRequired subscriptionRequired) {
        this.subscriptionRequired = subscriptionRequired;
    }

    public CoursesManagement getCoursesManagement() {
        return coursesManagement;
    }

    public SessionManagement coursesManagement(CoursesManagement coursesManagement) {
        this.coursesManagement = coursesManagement;
        return this;
    }

    public void setCoursesManagement(CoursesManagement coursesManagement) {
        this.coursesManagement = coursesManagement;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SessionManagement)) {
            return false;
        }
        return id != null && id.equals(((SessionManagement) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SessionManagement{" +
            "id=" + getId() +
            ", sessionNumber=" + getSessionNumber() +
            ", sessionName='" + getSessionName() + "'" +
            ", sessionDescription='" + getSessionDescription() + "'" +
            ", sessionUrl='" + getSessionUrl() + "'" +
            ", sessionVideoUrl='" + getSessionVideoUrl() + "'" +
            ", quiz='" + getQuiz() + "'" +
            ", sessionLogo='" + getSessionLogo() + "'" +
            ", sessionStatus='" + getSessionStatus() + "'" +
            ", subscriptionRequired='" + getSubscriptionRequired() + "'" +
            "}";
    }
}
