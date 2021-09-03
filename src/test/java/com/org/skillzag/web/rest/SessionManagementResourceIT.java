package com.org.skillzag.web.rest;

import com.org.skillzag.SkillzagcoursemanagementApp;
import com.org.skillzag.config.TestSecurityConfiguration;
import com.org.skillzag.domain.SessionManagement;
import com.org.skillzag.repository.SessionManagementRepository;
import com.org.skillzag.service.SessionManagementService;
import com.org.skillzag.service.dto.SessionManagementDTO;
import com.org.skillzag.service.mapper.SessionManagementMapper;

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

import com.org.skillzag.domain.enumeration.SessionStatus;
import com.org.skillzag.domain.enumeration.SubscriptionRequired;
/**
 * Integration tests for the {@link SessionManagementResource} REST controller.
 */
@SpringBootTest(classes = { SkillzagcoursemanagementApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class SessionManagementResourceIT {

    private static final Integer DEFAULT_SESSION_NUMBER = 1;
    private static final Integer UPDATED_SESSION_NUMBER = 2;

    private static final String DEFAULT_SESSION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SESSION_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SESSION_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_SESSION_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_SESSION_URL = "AAAAAAAAAA";
    private static final String UPDATED_SESSION_URL = "BBBBBBBBBB";

    private static final String DEFAULT_SESSION_VIDEO_URL = "AAAAAAAAAA";
    private static final String UPDATED_SESSION_VIDEO_URL = "BBBBBBBBBB";

    private static final String DEFAULT_QUIZ = "AAAAAAAAAA";
    private static final String UPDATED_QUIZ = "BBBBBBBBBB";

    private static final String DEFAULT_SESSION_LOGO = "AAAAAAAAAA";
    private static final String UPDATED_SESSION_LOGO = "BBBBBBBBBB";

    private static final SessionStatus DEFAULT_SESSION_STATUS = SessionStatus.ACTIVE;
    private static final SessionStatus UPDATED_SESSION_STATUS = SessionStatus.INPROGRESS;

    private static final SubscriptionRequired DEFAULT_SUBSCRIPTION_REQUIRED = SubscriptionRequired.YES;
    private static final SubscriptionRequired UPDATED_SUBSCRIPTION_REQUIRED = SubscriptionRequired.NO;

    @Autowired
    private SessionManagementRepository sessionManagementRepository;

    @Autowired
    private SessionManagementMapper sessionManagementMapper;

    @Autowired
    private SessionManagementService sessionManagementService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSessionManagementMockMvc;

    private SessionManagement sessionManagement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SessionManagement createEntity(EntityManager em) {
        SessionManagement sessionManagement = new SessionManagement()
            .sessionNumber(DEFAULT_SESSION_NUMBER)
            .sessionName(DEFAULT_SESSION_NAME)
            .sessionDescription(DEFAULT_SESSION_DESCRIPTION)
            .sessionUrl(DEFAULT_SESSION_URL)
            .sessionVideoUrl(DEFAULT_SESSION_VIDEO_URL)
            .quiz(DEFAULT_QUIZ)
            .sessionLogo(DEFAULT_SESSION_LOGO)
            .sessionStatus(DEFAULT_SESSION_STATUS)
            .subscriptionRequired(DEFAULT_SUBSCRIPTION_REQUIRED);
        return sessionManagement;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SessionManagement createUpdatedEntity(EntityManager em) {
        SessionManagement sessionManagement = new SessionManagement()
            .sessionNumber(UPDATED_SESSION_NUMBER)
            .sessionName(UPDATED_SESSION_NAME)
            .sessionDescription(UPDATED_SESSION_DESCRIPTION)
            .sessionUrl(UPDATED_SESSION_URL)
            .sessionVideoUrl(UPDATED_SESSION_VIDEO_URL)
            .quiz(UPDATED_QUIZ)
            .sessionLogo(UPDATED_SESSION_LOGO)
            .sessionStatus(UPDATED_SESSION_STATUS)
            .subscriptionRequired(UPDATED_SUBSCRIPTION_REQUIRED);
        return sessionManagement;
    }

    @BeforeEach
    public void initTest() {
        sessionManagement = createEntity(em);
    }

    @Test
    @Transactional
    public void createSessionManagement() throws Exception {
        int databaseSizeBeforeCreate = sessionManagementRepository.findAll().size();
        // Create the SessionManagement
        SessionManagementDTO sessionManagementDTO = sessionManagementMapper.toDto(sessionManagement);
        restSessionManagementMockMvc.perform(post("/api/session-managements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sessionManagementDTO)))
            .andExpect(status().isCreated());

        // Validate the SessionManagement in the database
        List<SessionManagement> sessionManagementList = sessionManagementRepository.findAll();
        assertThat(sessionManagementList).hasSize(databaseSizeBeforeCreate + 1);
        SessionManagement testSessionManagement = sessionManagementList.get(sessionManagementList.size() - 1);
        assertThat(testSessionManagement.getSessionNumber()).isEqualTo(DEFAULT_SESSION_NUMBER);
        assertThat(testSessionManagement.getSessionName()).isEqualTo(DEFAULT_SESSION_NAME);
        assertThat(testSessionManagement.getSessionDescription()).isEqualTo(DEFAULT_SESSION_DESCRIPTION);
        assertThat(testSessionManagement.getSessionUrl()).isEqualTo(DEFAULT_SESSION_URL);
        assertThat(testSessionManagement.getSessionVideoUrl()).isEqualTo(DEFAULT_SESSION_VIDEO_URL);
        assertThat(testSessionManagement.getQuiz()).isEqualTo(DEFAULT_QUIZ);
        assertThat(testSessionManagement.getSessionLogo()).isEqualTo(DEFAULT_SESSION_LOGO);
        assertThat(testSessionManagement.getSessionStatus()).isEqualTo(DEFAULT_SESSION_STATUS);
        assertThat(testSessionManagement.getSubscriptionRequired()).isEqualTo(DEFAULT_SUBSCRIPTION_REQUIRED);
    }

    @Test
    @Transactional
    public void createSessionManagementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sessionManagementRepository.findAll().size();

        // Create the SessionManagement with an existing ID
        sessionManagement.setId(1L);
        SessionManagementDTO sessionManagementDTO = sessionManagementMapper.toDto(sessionManagement);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSessionManagementMockMvc.perform(post("/api/session-managements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sessionManagementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SessionManagement in the database
        List<SessionManagement> sessionManagementList = sessionManagementRepository.findAll();
        assertThat(sessionManagementList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSessionManagements() throws Exception {
        // Initialize the database
        sessionManagementRepository.saveAndFlush(sessionManagement);

        // Get all the sessionManagementList
        restSessionManagementMockMvc.perform(get("/api/session-managements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sessionManagement.getId().intValue())))
            .andExpect(jsonPath("$.[*].sessionNumber").value(hasItem(DEFAULT_SESSION_NUMBER)))
            .andExpect(jsonPath("$.[*].sessionName").value(hasItem(DEFAULT_SESSION_NAME)))
            .andExpect(jsonPath("$.[*].sessionDescription").value(hasItem(DEFAULT_SESSION_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].sessionUrl").value(hasItem(DEFAULT_SESSION_URL)))
            .andExpect(jsonPath("$.[*].sessionVideoUrl").value(hasItem(DEFAULT_SESSION_VIDEO_URL)))
            .andExpect(jsonPath("$.[*].quiz").value(hasItem(DEFAULT_QUIZ)))
            .andExpect(jsonPath("$.[*].sessionLogo").value(hasItem(DEFAULT_SESSION_LOGO)))
            .andExpect(jsonPath("$.[*].sessionStatus").value(hasItem(DEFAULT_SESSION_STATUS.toString())))
            .andExpect(jsonPath("$.[*].subscriptionRequired").value(hasItem(DEFAULT_SUBSCRIPTION_REQUIRED.toString())));
    }
    
    @Test
    @Transactional
    public void getSessionManagement() throws Exception {
        // Initialize the database
        sessionManagementRepository.saveAndFlush(sessionManagement);

        // Get the sessionManagement
        restSessionManagementMockMvc.perform(get("/api/session-managements/{id}", sessionManagement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sessionManagement.getId().intValue()))
            .andExpect(jsonPath("$.sessionNumber").value(DEFAULT_SESSION_NUMBER))
            .andExpect(jsonPath("$.sessionName").value(DEFAULT_SESSION_NAME))
            .andExpect(jsonPath("$.sessionDescription").value(DEFAULT_SESSION_DESCRIPTION))
            .andExpect(jsonPath("$.sessionUrl").value(DEFAULT_SESSION_URL))
            .andExpect(jsonPath("$.sessionVideoUrl").value(DEFAULT_SESSION_VIDEO_URL))
            .andExpect(jsonPath("$.quiz").value(DEFAULT_QUIZ))
            .andExpect(jsonPath("$.sessionLogo").value(DEFAULT_SESSION_LOGO))
            .andExpect(jsonPath("$.sessionStatus").value(DEFAULT_SESSION_STATUS.toString()))
            .andExpect(jsonPath("$.subscriptionRequired").value(DEFAULT_SUBSCRIPTION_REQUIRED.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingSessionManagement() throws Exception {
        // Get the sessionManagement
        restSessionManagementMockMvc.perform(get("/api/session-managements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSessionManagement() throws Exception {
        // Initialize the database
        sessionManagementRepository.saveAndFlush(sessionManagement);

        int databaseSizeBeforeUpdate = sessionManagementRepository.findAll().size();

        // Update the sessionManagement
        SessionManagement updatedSessionManagement = sessionManagementRepository.findById(sessionManagement.getId()).get();
        // Disconnect from session so that the updates on updatedSessionManagement are not directly saved in db
        em.detach(updatedSessionManagement);
        updatedSessionManagement
            .sessionNumber(UPDATED_SESSION_NUMBER)
            .sessionName(UPDATED_SESSION_NAME)
            .sessionDescription(UPDATED_SESSION_DESCRIPTION)
            .sessionUrl(UPDATED_SESSION_URL)
            .sessionVideoUrl(UPDATED_SESSION_VIDEO_URL)
            .quiz(UPDATED_QUIZ)
            .sessionLogo(UPDATED_SESSION_LOGO)
            .sessionStatus(UPDATED_SESSION_STATUS)
            .subscriptionRequired(UPDATED_SUBSCRIPTION_REQUIRED);
        SessionManagementDTO sessionManagementDTO = sessionManagementMapper.toDto(updatedSessionManagement);

        restSessionManagementMockMvc.perform(put("/api/session-managements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sessionManagementDTO)))
            .andExpect(status().isOk());

        // Validate the SessionManagement in the database
        List<SessionManagement> sessionManagementList = sessionManagementRepository.findAll();
        assertThat(sessionManagementList).hasSize(databaseSizeBeforeUpdate);
        SessionManagement testSessionManagement = sessionManagementList.get(sessionManagementList.size() - 1);
        assertThat(testSessionManagement.getSessionNumber()).isEqualTo(UPDATED_SESSION_NUMBER);
        assertThat(testSessionManagement.getSessionName()).isEqualTo(UPDATED_SESSION_NAME);
        assertThat(testSessionManagement.getSessionDescription()).isEqualTo(UPDATED_SESSION_DESCRIPTION);
        assertThat(testSessionManagement.getSessionUrl()).isEqualTo(UPDATED_SESSION_URL);
        assertThat(testSessionManagement.getSessionVideoUrl()).isEqualTo(UPDATED_SESSION_VIDEO_URL);
        assertThat(testSessionManagement.getQuiz()).isEqualTo(UPDATED_QUIZ);
        assertThat(testSessionManagement.getSessionLogo()).isEqualTo(UPDATED_SESSION_LOGO);
        assertThat(testSessionManagement.getSessionStatus()).isEqualTo(UPDATED_SESSION_STATUS);
        assertThat(testSessionManagement.getSubscriptionRequired()).isEqualTo(UPDATED_SUBSCRIPTION_REQUIRED);
    }

    @Test
    @Transactional
    public void updateNonExistingSessionManagement() throws Exception {
        int databaseSizeBeforeUpdate = sessionManagementRepository.findAll().size();

        // Create the SessionManagement
        SessionManagementDTO sessionManagementDTO = sessionManagementMapper.toDto(sessionManagement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSessionManagementMockMvc.perform(put("/api/session-managements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sessionManagementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SessionManagement in the database
        List<SessionManagement> sessionManagementList = sessionManagementRepository.findAll();
        assertThat(sessionManagementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSessionManagement() throws Exception {
        // Initialize the database
        sessionManagementRepository.saveAndFlush(sessionManagement);

        int databaseSizeBeforeDelete = sessionManagementRepository.findAll().size();

        // Delete the sessionManagement
        restSessionManagementMockMvc.perform(delete("/api/session-managements/{id}", sessionManagement.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SessionManagement> sessionManagementList = sessionManagementRepository.findAll();
        assertThat(sessionManagementList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
