package com.org.skillzag.web.rest;

import com.org.skillzag.SkillzagcoursemanagementApp;
import com.org.skillzag.config.TestSecurityConfiguration;
import com.org.skillzag.domain.Institution;
import com.org.skillzag.repository.InstitutionRepository;
import com.org.skillzag.service.InstitutionService;
import com.org.skillzag.service.dto.InstitutionDTO;
import com.org.skillzag.service.mapper.InstitutionMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link InstitutionResource} REST controller.
 */
@SpringBootTest(classes = { SkillzagcoursemanagementApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class InstitutionResourceIT {

    private static final String DEFAULT_INSTITUTION_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_INSTITUTION_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_INSTITUTION_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_INSTITUTION_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_INSTITUTION_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_INSTITUTION_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_INSTITUTION_LOGO = "AAAAAAAAAA";
    private static final String UPDATED_INSTITUTION_LOGO = "BBBBBBBBBB";

    private static final String DEFAULT_INSTITUTION_MISC = "AAAAAAAAAA";
    private static final String UPDATED_INSTITUTION_MISC = "BBBBBBBBBB";

    private static final String DEFAULT_INSTITUTION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_INSTITUTION_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_INSTITUTION_NO_OF_STUDENTS = 1;
    private static final Integer UPDATED_INSTITUTION_NO_OF_STUDENTS = 2;

    private static final String DEFAULT_INSTITUTION_PHONENUMBER = "AAAAAAAAAA";
    private static final String UPDATED_INSTITUTION_PHONENUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_INSTITUTION_SECONDARYEMAIL = "AAAAAAAAAA";
    private static final String UPDATED_INSTITUTION_SECONDARYEMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_INSTITUTION_URL = "AAAAAAAAAA";
    private static final String UPDATED_INSTITUTION_URL = "BBBBBBBBBB";

    private static final String DEFAULT_INSTITUTION_URL_1 = "AAAAAAAAAA";
    private static final String UPDATED_INSTITUTION_URL_1 = "BBBBBBBBBB";

    private static final String DEFAULT_INSTITUTION_URL_2 = "AAAAAAAAAA";
    private static final String UPDATED_INSTITUTION_URL_2 = "BBBBBBBBBB";

    private static final String DEFAULT_INSTITUTION_VIDEO = "AAAAAAAAAA";
    private static final String UPDATED_INSTITUTION_VIDEO = "BBBBBBBBBB";

    private static final String DEFAULT_INSTITUTION_WEBSITE = "AAAAAAAAAA";
    private static final String UPDATED_INSTITUTION_WEBSITE = "BBBBBBBBBB";

    @Autowired
    private InstitutionRepository institutionRepository;

    @Autowired
    private InstitutionMapper institutionMapper;

    @Autowired
    private InstitutionService institutionService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInstitutionMockMvc;

    private Institution institution;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Institution createEntity(EntityManager em) {
        Institution institution = new Institution()
            .institutionAddress(DEFAULT_INSTITUTION_ADDRESS)
            .institutionDescription(DEFAULT_INSTITUTION_DESCRIPTION)
            .institutionEmail(DEFAULT_INSTITUTION_EMAIL)
            .institutionLogo(DEFAULT_INSTITUTION_LOGO)
            .institutionMisc(DEFAULT_INSTITUTION_MISC)
            .institutionName(DEFAULT_INSTITUTION_NAME)
            .institutionNoOfStudents(DEFAULT_INSTITUTION_NO_OF_STUDENTS)
            .institutionPhonenumber(DEFAULT_INSTITUTION_PHONENUMBER)
            .institutionSecondaryemail(DEFAULT_INSTITUTION_SECONDARYEMAIL)
            .institutionUrl(DEFAULT_INSTITUTION_URL)
            .institutionUrl1(DEFAULT_INSTITUTION_URL_1)
            .institutionUrl2(DEFAULT_INSTITUTION_URL_2)
            .institutionVideo(DEFAULT_INSTITUTION_VIDEO)
            .institutionWebsite(DEFAULT_INSTITUTION_WEBSITE);
        return institution;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Institution createUpdatedEntity(EntityManager em) {
        Institution institution = new Institution()
            .institutionAddress(UPDATED_INSTITUTION_ADDRESS)
            .institutionDescription(UPDATED_INSTITUTION_DESCRIPTION)
            .institutionEmail(UPDATED_INSTITUTION_EMAIL)
            .institutionLogo(UPDATED_INSTITUTION_LOGO)
            .institutionMisc(UPDATED_INSTITUTION_MISC)
            .institutionName(UPDATED_INSTITUTION_NAME)
            .institutionNoOfStudents(UPDATED_INSTITUTION_NO_OF_STUDENTS)
            .institutionPhonenumber(UPDATED_INSTITUTION_PHONENUMBER)
            .institutionSecondaryemail(UPDATED_INSTITUTION_SECONDARYEMAIL)
            .institutionUrl(UPDATED_INSTITUTION_URL)
            .institutionUrl1(UPDATED_INSTITUTION_URL_1)
            .institutionUrl2(UPDATED_INSTITUTION_URL_2)
            .institutionVideo(UPDATED_INSTITUTION_VIDEO)
            .institutionWebsite(UPDATED_INSTITUTION_WEBSITE);
        return institution;
    }

    @BeforeEach
    public void initTest() {
        institution = createEntity(em);
    }

    @Test
    @Transactional
    public void createInstitution() throws Exception {
        int databaseSizeBeforeCreate = institutionRepository.findAll().size();
        // Create the Institution
        InstitutionDTO institutionDTO = institutionMapper.toDto(institution);
        restInstitutionMockMvc.perform(post("/api/institutions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(institutionDTO)))
            .andExpect(status().isCreated());

        // Validate the Institution in the database
        List<Institution> institutionList = institutionRepository.findAll();
        assertThat(institutionList).hasSize(databaseSizeBeforeCreate + 1);
        Institution testInstitution = institutionList.get(institutionList.size() - 1);
        assertThat(testInstitution.getInstitutionAddress()).isEqualTo(DEFAULT_INSTITUTION_ADDRESS);
        assertThat(testInstitution.getInstitutionDescription()).isEqualTo(DEFAULT_INSTITUTION_DESCRIPTION);
        assertThat(testInstitution.getInstitutionEmail()).isEqualTo(DEFAULT_INSTITUTION_EMAIL);
        assertThat(testInstitution.getInstitutionLogo()).isEqualTo(DEFAULT_INSTITUTION_LOGO);
        assertThat(testInstitution.getInstitutionMisc()).isEqualTo(DEFAULT_INSTITUTION_MISC);
        assertThat(testInstitution.getInstitutionName()).isEqualTo(DEFAULT_INSTITUTION_NAME);
        assertThat(testInstitution.getInstitutionNoOfStudents()).isEqualTo(DEFAULT_INSTITUTION_NO_OF_STUDENTS);
        assertThat(testInstitution.getInstitutionPhonenumber()).isEqualTo(DEFAULT_INSTITUTION_PHONENUMBER);
        assertThat(testInstitution.getInstitutionSecondaryemail()).isEqualTo(DEFAULT_INSTITUTION_SECONDARYEMAIL);
        assertThat(testInstitution.getInstitutionUrl()).isEqualTo(DEFAULT_INSTITUTION_URL);
        assertThat(testInstitution.getInstitutionUrl1()).isEqualTo(DEFAULT_INSTITUTION_URL_1);
        assertThat(testInstitution.getInstitutionUrl2()).isEqualTo(DEFAULT_INSTITUTION_URL_2);
        assertThat(testInstitution.getInstitutionVideo()).isEqualTo(DEFAULT_INSTITUTION_VIDEO);
        assertThat(testInstitution.getInstitutionWebsite()).isEqualTo(DEFAULT_INSTITUTION_WEBSITE);
    }

    @Test
    @Transactional
    public void createInstitutionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = institutionRepository.findAll().size();

        // Create the Institution with an existing ID
        institution.setId(1L);
        InstitutionDTO institutionDTO = institutionMapper.toDto(institution);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInstitutionMockMvc.perform(post("/api/institutions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(institutionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Institution in the database
        List<Institution> institutionList = institutionRepository.findAll();
        assertThat(institutionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllInstitutions() throws Exception {
        // Initialize the database
        institutionRepository.saveAndFlush(institution);

        // Get all the institutionList
        restInstitutionMockMvc.perform(get("/api/institutions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(institution.getId().intValue())))
            .andExpect(jsonPath("$.[*].institutionAddress").value(hasItem(DEFAULT_INSTITUTION_ADDRESS)))
            .andExpect(jsonPath("$.[*].institutionDescription").value(hasItem(DEFAULT_INSTITUTION_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].institutionEmail").value(hasItem(DEFAULT_INSTITUTION_EMAIL)))
            .andExpect(jsonPath("$.[*].institutionLogo").value(hasItem(DEFAULT_INSTITUTION_LOGO)))
            .andExpect(jsonPath("$.[*].institutionMisc").value(hasItem(DEFAULT_INSTITUTION_MISC)))
            .andExpect(jsonPath("$.[*].institutionName").value(hasItem(DEFAULT_INSTITUTION_NAME)))
            .andExpect(jsonPath("$.[*].institutionNoOfStudents").value(hasItem(DEFAULT_INSTITUTION_NO_OF_STUDENTS)))
            .andExpect(jsonPath("$.[*].institutionPhonenumber").value(hasItem(DEFAULT_INSTITUTION_PHONENUMBER)))
            .andExpect(jsonPath("$.[*].institutionSecondaryemail").value(hasItem(DEFAULT_INSTITUTION_SECONDARYEMAIL)))
            .andExpect(jsonPath("$.[*].institutionUrl").value(hasItem(DEFAULT_INSTITUTION_URL)))
            .andExpect(jsonPath("$.[*].institutionUrl1").value(hasItem(DEFAULT_INSTITUTION_URL_1)))
            .andExpect(jsonPath("$.[*].institutionUrl2").value(hasItem(DEFAULT_INSTITUTION_URL_2)))
            .andExpect(jsonPath("$.[*].institutionVideo").value(hasItem(DEFAULT_INSTITUTION_VIDEO)))
            .andExpect(jsonPath("$.[*].institutionWebsite").value(hasItem(DEFAULT_INSTITUTION_WEBSITE)));
    }
    
    @Test
    @Transactional
    public void getInstitution() throws Exception {
        // Initialize the database
        institutionRepository.saveAndFlush(institution);

        // Get the institution
        restInstitutionMockMvc.perform(get("/api/institutions/{id}", institution.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(institution.getId().intValue()))
            .andExpect(jsonPath("$.institutionAddress").value(DEFAULT_INSTITUTION_ADDRESS))
            .andExpect(jsonPath("$.institutionDescription").value(DEFAULT_INSTITUTION_DESCRIPTION))
            .andExpect(jsonPath("$.institutionEmail").value(DEFAULT_INSTITUTION_EMAIL))
            .andExpect(jsonPath("$.institutionLogo").value(DEFAULT_INSTITUTION_LOGO))
            .andExpect(jsonPath("$.institutionMisc").value(DEFAULT_INSTITUTION_MISC))
            .andExpect(jsonPath("$.institutionName").value(DEFAULT_INSTITUTION_NAME))
            .andExpect(jsonPath("$.institutionNoOfStudents").value(DEFAULT_INSTITUTION_NO_OF_STUDENTS))
            .andExpect(jsonPath("$.institutionPhonenumber").value(DEFAULT_INSTITUTION_PHONENUMBER))
            .andExpect(jsonPath("$.institutionSecondaryemail").value(DEFAULT_INSTITUTION_SECONDARYEMAIL))
            .andExpect(jsonPath("$.institutionUrl").value(DEFAULT_INSTITUTION_URL))
            .andExpect(jsonPath("$.institutionUrl1").value(DEFAULT_INSTITUTION_URL_1))
            .andExpect(jsonPath("$.institutionUrl2").value(DEFAULT_INSTITUTION_URL_2))
            .andExpect(jsonPath("$.institutionVideo").value(DEFAULT_INSTITUTION_VIDEO))
            .andExpect(jsonPath("$.institutionWebsite").value(DEFAULT_INSTITUTION_WEBSITE));
    }
    @Test
    @Transactional
    public void getNonExistingInstitution() throws Exception {
        // Get the institution
        restInstitutionMockMvc.perform(get("/api/institutions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInstitution() throws Exception {
        // Initialize the database
        institutionRepository.saveAndFlush(institution);

        int databaseSizeBeforeUpdate = institutionRepository.findAll().size();

        // Update the institution
        Institution updatedInstitution = institutionRepository.findById(institution.getId()).get();
        // Disconnect from session so that the updates on updatedInstitution are not directly saved in db
        em.detach(updatedInstitution);
        updatedInstitution
            .institutionAddress(UPDATED_INSTITUTION_ADDRESS)
            .institutionDescription(UPDATED_INSTITUTION_DESCRIPTION)
            .institutionEmail(UPDATED_INSTITUTION_EMAIL)
            .institutionLogo(UPDATED_INSTITUTION_LOGO)
            .institutionMisc(UPDATED_INSTITUTION_MISC)
            .institutionName(UPDATED_INSTITUTION_NAME)
            .institutionNoOfStudents(UPDATED_INSTITUTION_NO_OF_STUDENTS)
            .institutionPhonenumber(UPDATED_INSTITUTION_PHONENUMBER)
            .institutionSecondaryemail(UPDATED_INSTITUTION_SECONDARYEMAIL)
            .institutionUrl(UPDATED_INSTITUTION_URL)
            .institutionUrl1(UPDATED_INSTITUTION_URL_1)
            .institutionUrl2(UPDATED_INSTITUTION_URL_2)
            .institutionVideo(UPDATED_INSTITUTION_VIDEO)
            .institutionWebsite(UPDATED_INSTITUTION_WEBSITE);
        InstitutionDTO institutionDTO = institutionMapper.toDto(updatedInstitution);

        restInstitutionMockMvc.perform(put("/api/institutions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(institutionDTO)))
            .andExpect(status().isOk());

        // Validate the Institution in the database
        List<Institution> institutionList = institutionRepository.findAll();
        assertThat(institutionList).hasSize(databaseSizeBeforeUpdate);
        Institution testInstitution = institutionList.get(institutionList.size() - 1);
        assertThat(testInstitution.getInstitutionAddress()).isEqualTo(UPDATED_INSTITUTION_ADDRESS);
        assertThat(testInstitution.getInstitutionDescription()).isEqualTo(UPDATED_INSTITUTION_DESCRIPTION);
        assertThat(testInstitution.getInstitutionEmail()).isEqualTo(UPDATED_INSTITUTION_EMAIL);
        assertThat(testInstitution.getInstitutionLogo()).isEqualTo(UPDATED_INSTITUTION_LOGO);
        assertThat(testInstitution.getInstitutionMisc()).isEqualTo(UPDATED_INSTITUTION_MISC);
        assertThat(testInstitution.getInstitutionName()).isEqualTo(UPDATED_INSTITUTION_NAME);
        assertThat(testInstitution.getInstitutionNoOfStudents()).isEqualTo(UPDATED_INSTITUTION_NO_OF_STUDENTS);
        assertThat(testInstitution.getInstitutionPhonenumber()).isEqualTo(UPDATED_INSTITUTION_PHONENUMBER);
        assertThat(testInstitution.getInstitutionSecondaryemail()).isEqualTo(UPDATED_INSTITUTION_SECONDARYEMAIL);
        assertThat(testInstitution.getInstitutionUrl()).isEqualTo(UPDATED_INSTITUTION_URL);
        assertThat(testInstitution.getInstitutionUrl1()).isEqualTo(UPDATED_INSTITUTION_URL_1);
        assertThat(testInstitution.getInstitutionUrl2()).isEqualTo(UPDATED_INSTITUTION_URL_2);
        assertThat(testInstitution.getInstitutionVideo()).isEqualTo(UPDATED_INSTITUTION_VIDEO);
        assertThat(testInstitution.getInstitutionWebsite()).isEqualTo(UPDATED_INSTITUTION_WEBSITE);
    }

    @Test
    @Transactional
    public void updateNonExistingInstitution() throws Exception {
        int databaseSizeBeforeUpdate = institutionRepository.findAll().size();

        // Create the Institution
        InstitutionDTO institutionDTO = institutionMapper.toDto(institution);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInstitutionMockMvc.perform(put("/api/institutions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(institutionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Institution in the database
        List<Institution> institutionList = institutionRepository.findAll();
        assertThat(institutionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInstitution() throws Exception {
        // Initialize the database
        institutionRepository.saveAndFlush(institution);

        int databaseSizeBeforeDelete = institutionRepository.findAll().size();

        // Delete the institution
        restInstitutionMockMvc.perform(delete("/api/institutions/{id}", institution.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Institution> institutionList = institutionRepository.findAll();
        assertThat(institutionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
