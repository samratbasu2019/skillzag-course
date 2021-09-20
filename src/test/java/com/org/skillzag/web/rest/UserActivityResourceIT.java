package com.org.skillzag.web.rest;

import com.org.skillzag.SkillzagcoursemanagementApp;
import com.org.skillzag.config.TestSecurityConfiguration;
import com.org.skillzag.domain.UserActivity;
import com.org.skillzag.repository.UserActivityRepository;
import com.org.skillzag.service.UserActivityService;
import com.org.skillzag.service.dto.UserActivityDTO;
import com.org.skillzag.service.mapper.UserActivityMapper;

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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link UserActivityResource} REST controller.
 */
@SpringBootTest(classes = { SkillzagcoursemanagementApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class UserActivityResourceIT {

    private static final Long DEFAULT_COURSE_ID = 1L;
    private static final Long UPDATED_COURSE_ID = 2L;

    private static final Long DEFAULT_INSTITUTE_ID = 1L;
    private static final Long UPDATED_INSTITUTE_ID = 2L;

    private static final String DEFAULT_ENROLLMENT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_ENROLLMENT_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

    private static final Long DEFAULT_SUBSCRIPTION_ID = 1L;
    private static final Long UPDATED_SUBSCRIPTION_ID = 2L;

    private static final Instant DEFAULT_ACTIVITY_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ACTIVITY_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private UserActivityRepository userActivityRepository;

    @Autowired
    private UserActivityMapper userActivityMapper;

    @Autowired
    private UserActivityService userActivityService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUserActivityMockMvc;

    private UserActivity userActivity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserActivity createEntity(EntityManager em) {
        UserActivity userActivity = new UserActivity()
            .courseId(DEFAULT_COURSE_ID)
            .instituteId(DEFAULT_INSTITUTE_ID)
            .enrollmentStatus(DEFAULT_ENROLLMENT_STATUS)
            .userId(DEFAULT_USER_ID)
            .subscriptionId(DEFAULT_SUBSCRIPTION_ID)
            .activityDate(DEFAULT_ACTIVITY_DATE);
        return userActivity;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserActivity createUpdatedEntity(EntityManager em) {
        UserActivity userActivity = new UserActivity()
            .courseId(UPDATED_COURSE_ID)
            .instituteId(UPDATED_INSTITUTE_ID)
            .enrollmentStatus(UPDATED_ENROLLMENT_STATUS)
            .userId(UPDATED_USER_ID)
            .subscriptionId(UPDATED_SUBSCRIPTION_ID)
            .activityDate(UPDATED_ACTIVITY_DATE);
        return userActivity;
    }

    @BeforeEach
    public void initTest() {
        userActivity = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserActivity() throws Exception {
        int databaseSizeBeforeCreate = userActivityRepository.findAll().size();
        // Create the UserActivity
        UserActivityDTO userActivityDTO = userActivityMapper.toDto(userActivity);
        restUserActivityMockMvc.perform(post("/api/user-activities").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userActivityDTO)))
            .andExpect(status().isCreated());

        // Validate the UserActivity in the database
        List<UserActivity> userActivityList = userActivityRepository.findAll();
        assertThat(userActivityList).hasSize(databaseSizeBeforeCreate + 1);
        UserActivity testUserActivity = userActivityList.get(userActivityList.size() - 1);
        assertThat(testUserActivity.getCourseId()).isEqualTo(DEFAULT_COURSE_ID);
        assertThat(testUserActivity.getInstituteId()).isEqualTo(DEFAULT_INSTITUTE_ID);
        assertThat(testUserActivity.getEnrollmentStatus()).isEqualTo(DEFAULT_ENROLLMENT_STATUS);
        assertThat(testUserActivity.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testUserActivity.getSubscriptionId()).isEqualTo(DEFAULT_SUBSCRIPTION_ID);
        assertThat(testUserActivity.getActivityDate()).isEqualTo(DEFAULT_ACTIVITY_DATE);
    }

    @Test
    @Transactional
    public void createUserActivityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userActivityRepository.findAll().size();

        // Create the UserActivity with an existing ID
        userActivity.setId(1L);
        UserActivityDTO userActivityDTO = userActivityMapper.toDto(userActivity);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserActivityMockMvc.perform(post("/api/user-activities").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userActivityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserActivity in the database
        List<UserActivity> userActivityList = userActivityRepository.findAll();
        assertThat(userActivityList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllUserActivities() throws Exception {
        // Initialize the database
        userActivityRepository.saveAndFlush(userActivity);

        // Get all the userActivityList
        restUserActivityMockMvc.perform(get("/api/user-activities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userActivity.getId().intValue())))
            .andExpect(jsonPath("$.[*].courseId").value(hasItem(DEFAULT_COURSE_ID.intValue())))
            .andExpect(jsonPath("$.[*].instituteId").value(hasItem(DEFAULT_INSTITUTE_ID.intValue())))
            .andExpect(jsonPath("$.[*].enrollmentStatus").value(hasItem(DEFAULT_ENROLLMENT_STATUS)))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)))
            .andExpect(jsonPath("$.[*].subscriptionId").value(hasItem(DEFAULT_SUBSCRIPTION_ID.intValue())))
            .andExpect(jsonPath("$.[*].activityDate").value(hasItem(DEFAULT_ACTIVITY_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getUserActivity() throws Exception {
        // Initialize the database
        userActivityRepository.saveAndFlush(userActivity);

        // Get the userActivity
        restUserActivityMockMvc.perform(get("/api/user-activities/{id}", userActivity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userActivity.getId().intValue()))
            .andExpect(jsonPath("$.courseId").value(DEFAULT_COURSE_ID.intValue()))
            .andExpect(jsonPath("$.instituteId").value(DEFAULT_INSTITUTE_ID.intValue()))
            .andExpect(jsonPath("$.enrollmentStatus").value(DEFAULT_ENROLLMENT_STATUS))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID))
            .andExpect(jsonPath("$.subscriptionId").value(DEFAULT_SUBSCRIPTION_ID.intValue()))
            .andExpect(jsonPath("$.activityDate").value(DEFAULT_ACTIVITY_DATE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingUserActivity() throws Exception {
        // Get the userActivity
        restUserActivityMockMvc.perform(get("/api/user-activities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserActivity() throws Exception {
        // Initialize the database
        userActivityRepository.saveAndFlush(userActivity);

        int databaseSizeBeforeUpdate = userActivityRepository.findAll().size();

        // Update the userActivity
        UserActivity updatedUserActivity = userActivityRepository.findById(userActivity.getId()).get();
        // Disconnect from session so that the updates on updatedUserActivity are not directly saved in db
        em.detach(updatedUserActivity);
        updatedUserActivity
            .courseId(UPDATED_COURSE_ID)
            .instituteId(UPDATED_INSTITUTE_ID)
            .enrollmentStatus(UPDATED_ENROLLMENT_STATUS)
            .userId(UPDATED_USER_ID)
            .subscriptionId(UPDATED_SUBSCRIPTION_ID)
            .activityDate(UPDATED_ACTIVITY_DATE);
        UserActivityDTO userActivityDTO = userActivityMapper.toDto(updatedUserActivity);

        restUserActivityMockMvc.perform(put("/api/user-activities").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userActivityDTO)))
            .andExpect(status().isOk());

        // Validate the UserActivity in the database
        List<UserActivity> userActivityList = userActivityRepository.findAll();
        assertThat(userActivityList).hasSize(databaseSizeBeforeUpdate);
        UserActivity testUserActivity = userActivityList.get(userActivityList.size() - 1);
        assertThat(testUserActivity.getCourseId()).isEqualTo(UPDATED_COURSE_ID);
        assertThat(testUserActivity.getInstituteId()).isEqualTo(UPDATED_INSTITUTE_ID);
        assertThat(testUserActivity.getEnrollmentStatus()).isEqualTo(UPDATED_ENROLLMENT_STATUS);
        assertThat(testUserActivity.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testUserActivity.getSubscriptionId()).isEqualTo(UPDATED_SUBSCRIPTION_ID);
        assertThat(testUserActivity.getActivityDate()).isEqualTo(UPDATED_ACTIVITY_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingUserActivity() throws Exception {
        int databaseSizeBeforeUpdate = userActivityRepository.findAll().size();

        // Create the UserActivity
        UserActivityDTO userActivityDTO = userActivityMapper.toDto(userActivity);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserActivityMockMvc.perform(put("/api/user-activities").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(userActivityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserActivity in the database
        List<UserActivity> userActivityList = userActivityRepository.findAll();
        assertThat(userActivityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUserActivity() throws Exception {
        // Initialize the database
        userActivityRepository.saveAndFlush(userActivity);

        int databaseSizeBeforeDelete = userActivityRepository.findAll().size();

        // Delete the userActivity
        restUserActivityMockMvc.perform(delete("/api/user-activities/{id}", userActivity.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UserActivity> userActivityList = userActivityRepository.findAll();
        assertThat(userActivityList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
