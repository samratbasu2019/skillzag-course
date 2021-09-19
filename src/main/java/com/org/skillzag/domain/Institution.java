package com.org.skillzag.domain;


import javax.persistence.*;

import java.io.Serializable;

/**
 * A Institution.
 */
@Entity
@Table(name = "institution")
public class Institution implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "institution_address")
    private String institutionAddress;

    @Column(name = "institution_description")
    private String institutionDescription;

    @Column(name = "institution_email")
    private String institutionEmail;

    @Column(name = "institution_logo")
    private String institutionLogo;

    @Column(name = "institution_misc")
    private String institutionMisc;

    @Column(name = "institution_name")
    private String institutionName;

    @Column(name = "institution_no_of_students")
    private Integer institutionNoOfStudents;

    @Column(name = "institution_phonenumber")
    private String institutionPhonenumber;

    @Column(name = "institution_secondaryemail")
    private String institutionSecondaryemail;

    @Column(name = "institution_url")
    private String institutionUrl;

    @Column(name = "institution_url_1")
    private String institutionUrl1;

    @Column(name = "institution_url_2")
    private String institutionUrl2;

    @Column(name = "institution_video")
    private String institutionVideo;

    @Column(name = "institution_website")
    private String institutionWebsite;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInstitutionAddress() {
        return institutionAddress;
    }

    public Institution institutionAddress(String institutionAddress) {
        this.institutionAddress = institutionAddress;
        return this;
    }

    public void setInstitutionAddress(String institutionAddress) {
        this.institutionAddress = institutionAddress;
    }

    public String getInstitutionDescription() {
        return institutionDescription;
    }

    public Institution institutionDescription(String institutionDescription) {
        this.institutionDescription = institutionDescription;
        return this;
    }

    public void setInstitutionDescription(String institutionDescription) {
        this.institutionDescription = institutionDescription;
    }

    public String getInstitutionEmail() {
        return institutionEmail;
    }

    public Institution institutionEmail(String institutionEmail) {
        this.institutionEmail = institutionEmail;
        return this;
    }

    public void setInstitutionEmail(String institutionEmail) {
        this.institutionEmail = institutionEmail;
    }

    public String getInstitutionLogo() {
        return institutionLogo;
    }

    public Institution institutionLogo(String institutionLogo) {
        this.institutionLogo = institutionLogo;
        return this;
    }

    public void setInstitutionLogo(String institutionLogo) {
        this.institutionLogo = institutionLogo;
    }

    public String getInstitutionMisc() {
        return institutionMisc;
    }

    public Institution institutionMisc(String institutionMisc) {
        this.institutionMisc = institutionMisc;
        return this;
    }

    public void setInstitutionMisc(String institutionMisc) {
        this.institutionMisc = institutionMisc;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public Institution institutionName(String institutionName) {
        this.institutionName = institutionName;
        return this;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public Integer getInstitutionNoOfStudents() {
        return institutionNoOfStudents;
    }

    public Institution institutionNoOfStudents(Integer institutionNoOfStudents) {
        this.institutionNoOfStudents = institutionNoOfStudents;
        return this;
    }

    public void setInstitutionNoOfStudents(Integer institutionNoOfStudents) {
        this.institutionNoOfStudents = institutionNoOfStudents;
    }

    public String getInstitutionPhonenumber() {
        return institutionPhonenumber;
    }

    public Institution institutionPhonenumber(String institutionPhonenumber) {
        this.institutionPhonenumber = institutionPhonenumber;
        return this;
    }

    public void setInstitutionPhonenumber(String institutionPhonenumber) {
        this.institutionPhonenumber = institutionPhonenumber;
    }

    public String getInstitutionSecondaryemail() {
        return institutionSecondaryemail;
    }

    public Institution institutionSecondaryemail(String institutionSecondaryemail) {
        this.institutionSecondaryemail = institutionSecondaryemail;
        return this;
    }

    public void setInstitutionSecondaryemail(String institutionSecondaryemail) {
        this.institutionSecondaryemail = institutionSecondaryemail;
    }

    public String getInstitutionUrl() {
        return institutionUrl;
    }

    public Institution institutionUrl(String institutionUrl) {
        this.institutionUrl = institutionUrl;
        return this;
    }

    public void setInstitutionUrl(String institutionUrl) {
        this.institutionUrl = institutionUrl;
    }

    public String getInstitutionUrl1() {
        return institutionUrl1;
    }

    public Institution institutionUrl1(String institutionUrl1) {
        this.institutionUrl1 = institutionUrl1;
        return this;
    }

    public void setInstitutionUrl1(String institutionUrl1) {
        this.institutionUrl1 = institutionUrl1;
    }

    public String getInstitutionUrl2() {
        return institutionUrl2;
    }

    public Institution institutionUrl2(String institutionUrl2) {
        this.institutionUrl2 = institutionUrl2;
        return this;
    }

    public void setInstitutionUrl2(String institutionUrl2) {
        this.institutionUrl2 = institutionUrl2;
    }

    public String getInstitutionVideo() {
        return institutionVideo;
    }

    public Institution institutionVideo(String institutionVideo) {
        this.institutionVideo = institutionVideo;
        return this;
    }

    public void setInstitutionVideo(String institutionVideo) {
        this.institutionVideo = institutionVideo;
    }

    public String getInstitutionWebsite() {
        return institutionWebsite;
    }

    public Institution institutionWebsite(String institutionWebsite) {
        this.institutionWebsite = institutionWebsite;
        return this;
    }

    public void setInstitutionWebsite(String institutionWebsite) {
        this.institutionWebsite = institutionWebsite;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Institution)) {
            return false;
        }
        return id != null && id.equals(((Institution) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Institution{" +
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
