package com.org.skillzag.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.org.skillzag.domain.Institution} entity.
 */
public class InstitutionDTO implements Serializable {
    
    private Long id;

    private String institutionAddress;

    private String institutionDescription;

    private String institutionEmail;

    private String institutionLogo;

    private String institutionMisc;

    private String institutionName;

    private Integer institutionNoOfStudents;

    private String institutionPhonenumber;

    private String institutionSecondaryemail;

    private String institutionUrl;

    private String institutionUrl1;

    private String institutionUrl2;

    private String institutionVideo;

    private String institutionWebsite;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInstitutionAddress() {
        return institutionAddress;
    }

    public void setInstitutionAddress(String institutionAddress) {
        this.institutionAddress = institutionAddress;
    }

    public String getInstitutionDescription() {
        return institutionDescription;
    }

    public void setInstitutionDescription(String institutionDescription) {
        this.institutionDescription = institutionDescription;
    }

    public String getInstitutionEmail() {
        return institutionEmail;
    }

    public void setInstitutionEmail(String institutionEmail) {
        this.institutionEmail = institutionEmail;
    }

    public String getInstitutionLogo() {
        return institutionLogo;
    }

    public void setInstitutionLogo(String institutionLogo) {
        this.institutionLogo = institutionLogo;
    }

    public String getInstitutionMisc() {
        return institutionMisc;
    }

    public void setInstitutionMisc(String institutionMisc) {
        this.institutionMisc = institutionMisc;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public Integer getInstitutionNoOfStudents() {
        return institutionNoOfStudents;
    }

    public void setInstitutionNoOfStudents(Integer institutionNoOfStudents) {
        this.institutionNoOfStudents = institutionNoOfStudents;
    }

    public String getInstitutionPhonenumber() {
        return institutionPhonenumber;
    }

    public void setInstitutionPhonenumber(String institutionPhonenumber) {
        this.institutionPhonenumber = institutionPhonenumber;
    }

    public String getInstitutionSecondaryemail() {
        return institutionSecondaryemail;
    }

    public void setInstitutionSecondaryemail(String institutionSecondaryemail) {
        this.institutionSecondaryemail = institutionSecondaryemail;
    }

    public String getInstitutionUrl() {
        return institutionUrl;
    }

    public void setInstitutionUrl(String institutionUrl) {
        this.institutionUrl = institutionUrl;
    }

    public String getInstitutionUrl1() {
        return institutionUrl1;
    }

    public void setInstitutionUrl1(String institutionUrl1) {
        this.institutionUrl1 = institutionUrl1;
    }

    public String getInstitutionUrl2() {
        return institutionUrl2;
    }

    public void setInstitutionUrl2(String institutionUrl2) {
        this.institutionUrl2 = institutionUrl2;
    }

    public String getInstitutionVideo() {
        return institutionVideo;
    }

    public void setInstitutionVideo(String institutionVideo) {
        this.institutionVideo = institutionVideo;
    }

    public String getInstitutionWebsite() {
        return institutionWebsite;
    }

    public void setInstitutionWebsite(String institutionWebsite) {
        this.institutionWebsite = institutionWebsite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InstitutionDTO)) {
            return false;
        }

        return id != null && id.equals(((InstitutionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InstitutionDTO{" +
            "id=" + getId() +
            ", institutionAddress='" + getInstitutionAddress() + "'" +
            ", institutionDescription='" + getInstitutionDescription() + "'" +
            ", institutionEmail='" + getInstitutionEmail() + "'" +
            ", institutionLogo='" + getInstitutionLogo() + "'" +
            ", institutionMisc='" + getInstitutionMisc() + "'" +
            ", institutionName='" + getInstitutionName() + "'" +
            ", institutionNoOfStudents=" + getInstitutionNoOfStudents() +
            ", institutionPhonenumber='" + getInstitutionPhonenumber() + "'" +
            ", institutionSecondaryemail='" + getInstitutionSecondaryemail() + "'" +
            ", institutionUrl='" + getInstitutionUrl() + "'" +
            ", institutionUrl1='" + getInstitutionUrl1() + "'" +
            ", institutionUrl2='" + getInstitutionUrl2() + "'" +
            ", institutionVideo='" + getInstitutionVideo() + "'" +
            ", institutionWebsite='" + getInstitutionWebsite() + "'" +
            "}";
    }
}
