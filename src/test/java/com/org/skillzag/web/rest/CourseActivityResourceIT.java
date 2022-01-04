package com.org.skillzag.web.rest;

import com.org.skillzag.SkillzagcoursemanagementApp;
import com.org.skillzag.config.TestSecurityConfiguration;
import com.org.skillzag.domain.CourseActivity;
import com.org.skillzag.repository.CourseActivityRepository;
import com.org.skillzag.service.CourseActivityService;
import com.org.skillzag.service.dto.CourseActivityDTO;
import com.org.skillzag.service.mapper.CourseActivityMapper;

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
 * Integration tests for the {@link CourseActivityResource} REST controller.
 */
@SpringBootTest(classes = { SkillzagcoursemanagementApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class CourseActivityResourceIT {

    private static final Long DEFAULT_COURSE_ID = 1L;
    private static final Long UPDATED_COURSE_ID = 2L;

    private static final Long DEFAULT_INSTITUTE_ID = 1L;
    private static final Long UPDATED_INSTITUTE_ID = 2L;

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

    private static final Long DEFAULT_SUBSCRIPTION_ID = 1L;
    private static final Long UPDATED_SUBSCRIPTION_ID = 2L;

    private static final Instant DEFAULT_ACTIVITY_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ACTIVITY_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private CourseActivityRepository courseActivityRepository;

    @Autowired
    private CourseActivityMapper courseActivityMapper;

    @Autowired
    private CourseActivityService courseActivityService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCourseActivityMockMvc;

    private CourseActivity courseActivity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CourseActivity createEntity(EntityManager em) {
        CourseActivity courseActivity = new CourseActivity()
            .courseId(DEFAULT_COURSE_ID)
            .instituteId(DEFAULT_INSTITUTE_ID)
            .status(DEFAULT_STATUS)
            .userId(DEFAULT_USER_ID)
            .subscriptionId(DEFAULT_SUBSCRIPTION_ID)
            .activityDate(DEFAULT_ACTIVITY_DATE);
        return courseActivity;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CourseActivity createUpdatedEntity(EntityManager em) {
        CourseActivity courseActivity = new CourseActivity()
            .courseId(UPDATED_COURSE_ID)
            .instituteId(UPDATED_INSTITUTE_ID)
            .status(UPDATED_STATUS)
            .userId(UPDATED_USER_ID)
            .subscriptionId(UPDATED_SUBSCRIPTION_ID)
            .activityDate(UPDATED_ACTIVITY_DATE);
        return courseActivity;
    }

    @BeforeEach
    public void initTest() {
        courseActivity = createEntity(em);
    }

    @Test
    @Transactional
    public void createCourseActivity() throws Exception {
        int databaseSizeBeforeCreate = courseActivityRepository.findAll().size();
        // Create the CourseActivity
        CourseActivityDTO courseActivityDTO = courseActivityMapper.toDto(courseActivity);
        restCourseActivityMockMvc.perform(post("/api/course-activities").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(courseActivityDTO)))
            .andExpect(status().isCreated());

        // Validate the CourseActivity in the database
        List<CourseActivity> courseActivityList = courseActivityRepository.findAll();
        assertThat(courseActivityList).hasSize(databaseSizeBeforeCreate + 1);
        CourseActivity testCourseActivity = courseActivityList.get(courseActivityList.size() - 1);
        assertThat(testCourseActivity.getCourseId()).isEqualTo(DEFAULT_COURSE_ID);
        assertThat(testCourseActivity.getInstituteId()).isEqualTo(DEFAULT_INSTITUTE_ID);
        assertThat(testCourseActivity.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testCourseActivity.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testCourseActivity.getSubscriptionId()).isEqualTo(DEFAULT_SUBSCRIPTION_ID);
        assertThat(testCourseActivity.getActivityDate()).isEqualTo(DEFAULT_ACTIVITY_DATE);
    }

    @Test
    @Transactional
    public void createCourseActivityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = courseActivityRepository.findAll().size();

        // Create the CourseActivity with an existing ID
        courseActivity.setId(1L);
        CourseActivityDTO courseActivityDTO = courseActivityMapper.toDto(courseActivity);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCourseActivityMockMvc.perform(post("/api/course-activities").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(courseActivityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CourseActivity in the database
        List<CourseActivity> courseActivityList = courseActivityRepository.findAll();
        assertThat(courseActivityList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCourseActivities() throws Exception {
        // Initialize the database
        courseActivityRepository.saveAndFlush(courseActivity);

        // Get all the courseActivityList
        restCourseActivityMockMvc.perform(get("/api/course-activities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(courseActivity.getId().intValue())))
            .andExpect(jsonPath("$.[*].courseId").value(hasItem(DEFAULT_COURSE_ID.intValue())))
            .andExpect(jsonPath("$.[*].instituteId").value(hasItem(DEFAULT_INSTITUTE_ID.intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)))
            .andExpect(jsonPath("$.[*].subscriptionId").value(hasItem(DEFAULT_SUBSCRIPTION_ID.intValue())))
            .andExpect(jsonPath("$.[*].activityDate").value(hasItem(DEFAULT_ACTIVITY_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getCourseActivity() throws Exception {
        // Initialize the database
        courseActivityRepository.saveAndFlush(courseActivity);

        // Get the courseActivity
        restCourseActivityMockMvc.perform(get("/api/course-activities/{id}", courseActivity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(courseActivity.getId().intValue()))
            .andExpect(jsonPath("$.courseId").value(DEFAULT_COURSE_ID.intValue()))
            .andExpect(jsonPath("$.instituteId").value(DEFAULT_INSTITUTE_ID.intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID))
            .andExpect(jsonPath("$.subscriptionId").value(DEFAULT_SUBSCRIPTION_ID.intValue()))
            .andExpect(jsonPath("$.activityDate").value(DEFAULT_ACTIVITY_DATE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingCourseActivity() throws Exception {
        // Get the courseActivity
        restCourseActivityMockMvc.perform(get("/api/course-activities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCourseActivity() throws Exception {
        // Initialize the database
        courseActivityRepository.saveAndFlush(courseActivity);

        int databaseSizeBeforeUpdate = courseActivityRepository.findAll().size();

        // Update the courseActivity
        CourseActivity updatedCourseActivity = courseActivityRepository.findById(courseActivity.getId()).get();
        // Disconnect from session so that the updates on updatedCourseActivity are not directly saved in db
        em.detach(updatedCourseActivity);
        updatedCourseActivity
            .courseId(UPDATED_COURSE_ID)
            .instituteId(UPDATED_INSTITUTE_ID)
            .status(UPDATED_STATUS)
            .userId(UPDATED_USER_ID)
            .subscriptionId(UPDATED_SUBSCRIPTION_ID)
            .activityDate(UPDATED_ACTIVITY_DATE);
        CourseActivityDTO courseActivityDTO = courseActivityMapper.toDto(updatedCourseActivity);

        restCourseActivityMockMvc.perform(put("/api/course-activities").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(courseActivityDTO)))
            .andExpect(status().isOk());

        // Validate the CourseActivity in the database
        List<CourseActivity> courseActivityList = courseActivityRepository.findAll();
        assertThat(courseActivityList).hasSize(databaseSizeBeforeUpdate);
        CourseActivity testCourseActivity = courseActivityList.get(courseActivityList.size() - 1);
        assertThat(testCourseActivity.getCourseId()).isEqualTo(UPDATED_COURSE_ID);
        assertThat(testCourseActivity.getInstituteId()).isEqualTo(UPDATED_INSTITUTE_ID);
        assertThat(testCourseActivity.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testCourseActivity.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testCourseActivity.getSubscriptionId()).isEqualTo(UPDATED_SUBSCRIPTION_ID);
        assertThat(testCourseActivity.getActivityDate()).isEqualTo(UPDATED_ACTIVITY_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingCourseActivity() throws Exception {
        int databaseSizeBeforeUpdate = courseActivityRepository.findAll().size();

        // Create the CourseActivity
        CourseActivityDTO courseActivityDTO = courseActivityMapper.toDto(courseActivity);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCourseActivityMockMvc.perform(put("/api/course-activities").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(courseActivityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CourseActivity in the database
        List<CourseActivity> courseActivityList = courseActivityRepository.findAll();
        assertThat(courseActivityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCourseActivity() throws Exception {
        // Initialize the database
        courseActivityRepository.saveAndFlush(courseActivity);

        int databaseSizeBeforeDelete = courseActivityRepository.findAll().size();

        // Delete the courseActivity
        restCourseActivityMockMvc.perform(delete("/api/course-activities/{id}", courseActivity.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CourseActivity> courseActivityList = courseActivityRepository.findAll();
        assertThat(courseActivityList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
