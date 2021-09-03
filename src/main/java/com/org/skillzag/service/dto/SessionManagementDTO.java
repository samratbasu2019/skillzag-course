package com.org.skillzag.service.dto;

import java.io.Serializable;
import com.org.skillzag.domain.enumeration.SessionStatus;
import com.org.skillzag.domain.enumeration.SubscriptionRequired;

/**
 * A DTO for the {@link com.org.skillzag.domain.SessionManagement} entity.
 */
public class SessionManagementDTO implements Serializable {
    
    private Long id;

    private Integer sessionNumber;

    private String sessionName;

    private String sessionDescription;

    private String sessionUrl;

    private String sessionVideoUrl;

    private String quiz;

    private String sessionLogo;

    private SessionStatus sessionStatus;

    private SubscriptionRequired subscriptionRequired;


    private Long coursesManagementId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSessionNumber() {
        return sessionNumber;
    }

    public void setSessionNumber(Integer sessionNumber) {
        this.sessionNumber = sessionNumber;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public String getSessionDescription() {
        return sessionDescription;
    }

    public void setSessionDescription(String sessionDescription) {
        this.sessionDescription = sessionDescription;
    }

    public String getSessionUrl() {
        return sessionUrl;
    }

    public void setSessionUrl(String sessionUrl) {
        this.sessionUrl = sessionUrl;
    }

    public String getSessionVideoUrl() {
        return sessionVideoUrl;
    }

    public void setSessionVideoUrl(String sessionVideoUrl) {
        this.sessionVideoUrl = sessionVideoUrl;
    }

    public String getQuiz() {
        return quiz;
    }

    public void setQuiz(String quiz) {
        this.quiz = quiz;
    }

    public String getSessionLogo() {
        return sessionLogo;
    }

    public void setSessionLogo(String sessionLogo) {
        this.sessionLogo = sessionLogo;
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    public void setSessionStatus(SessionStatus sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

    public SubscriptionRequired getSubscriptionRequired() {
        return subscriptionRequired;
    }

    public void setSubscriptionRequired(SubscriptionRequired subscriptionRequired) {
        this.subscriptionRequired = subscriptionRequired;
    }

    public Long getCoursesManagementId() {
        return coursesManagementId;
    }

    public void setCoursesManagementId(Long coursesManagementId) {
        this.coursesManagementId = coursesManagementId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SessionManagementDTO)) {
            return false;
        }

        return id != null && id.equals(((SessionManagementDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SessionManagementDTO{" +
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
            ", coursesManagementId=" + getCoursesManagementId() +
            "}";
    }
}
