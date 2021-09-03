package com.org.skillzag.web.rest;

import com.org.skillzag.SkillzagcoursemanagementApp;
import com.org.skillzag.config.TestSecurityConfiguration;
import com.org.skillzag.domain.CoursesManagement;
import com.org.skillzag.repository.CoursesManagementRepository;
import com.org.skillzag.service.CoursesManagementService;
import com.org.skillzag.service.dto.CoursesManagementDTO;
import com.org.skillzag.service.mapper.CoursesManagementMapper;

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

import com.org.skillzag.domain.enumeration.CourseStatus;
import com.org.skillzag.domain.enumeration.RecommendedStatus;
/**
 * Integration tests for the {@link CoursesManagementResource} REST controller.
 */
@SpringBootTest(classes = { SkillzagcoursemanagementApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class CoursesManagementResourceIT {

    private static final Integer DEFAULT_COURSE_ID = 1;
    private static final Integer UPDATED_COURSE_ID = 2;

    private static final String DEFAULT_COURSE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COURSE_NAME = "BBBBBBBBBB";

    private static final Instant DEFAULT_VALID_FROM = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_VALID_FROM = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_VALID_TO = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_VALID_TO = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_IMAGE_PATH = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_PATH = "BBBBBBBBBB";

    private static final String DEFAULT_VIDEO_URL = "AAAAAAAAAA";
    private static final String UPDATED_VIDEO_URL = "BBBBBBBBBB";

    private static final String DEFAULT_COURSE_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_COURSE_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_COURSE_OBJECTIVE = "AAAAAAAAAA";
    private static final String UPDATED_COURSE_OBJECTIVE = "BBBBBBBBBB";

    private static final String DEFAULT_URL_1 = "AAAAAAAAAA";
    private static final String UPDATED_URL_1 = "BBBBBBBBBB";

    private static final String DEFAULT_URL_2 = "AAAAAAAAAA";
    private static final String UPDATED_URL_2 = "BBBBBBBBBB";

    private static final String DEFAULT_URL_3 = "AAAAAAAAAA";
    private static final String UPDATED_URL_3 = "BBBBBBBBBB";

    private static final String DEFAULT_QUIZB_4_COURSE = "AAAAAAAAAA";
    private static final String UPDATED_QUIZB_4_COURSE = "BBBBBBBBBB";

    private static final String DEFAULT_QUIZA_4_COURSE = "AAAAAAAAAA";
    private static final String UPDATED_QUIZA_4_COURSE = "BBBBBBBBBB";

    private static final CourseStatus DEFAULT_COURSE_STATUS = CourseStatus.ACTIVE;
    private static final CourseStatus UPDATED_COURSE_STATUS = CourseStatus.INACTIVE;

    private static final RecommendedStatus DEFAULT_RECOMMENDED_STATUS = RecommendedStatus.ACTIVE;
    private static final RecommendedStatus UPDATED_RECOMMENDED_STATUS = RecommendedStatus.RECOMMENDED;

    @Autowired
    private CoursesManagementRepository coursesManagementRepository;

    @Autowired
    private CoursesManagementMapper coursesManagementMapper;

    @Autowired
    private CoursesManagementService coursesManagementService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCoursesManagementMockMvc;

    private CoursesManagement coursesManagement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CoursesManagement createEntity(EntityManager em) {
        CoursesManagement coursesManagement = new CoursesManagement()
            .courseId(DEFAULT_COURSE_ID)
            .courseName(DEFAULT_COURSE_NAME)
            .validFrom(DEFAULT_VALID_FROM)
            .validTo(DEFAULT_VALID_TO)
            .imagePath(DEFAULT_IMAGE_PATH)
            .videoUrl(DEFAULT_VIDEO_URL)
            .courseDescription(DEFAULT_COURSE_DESCRIPTION)
            .courseObjective(DEFAULT_COURSE_OBJECTIVE)
            .url1(DEFAULT_URL_1)
            .url2(DEFAULT_URL_2)
            .url3(DEFAULT_URL_3)
            .quizb4Course(DEFAULT_QUIZB_4_COURSE)
            .quiza4Course(DEFAULT_QUIZA_4_COURSE)
            .courseStatus(DEFAULT_COURSE_STATUS)
            .recommendedStatus(DEFAULT_RECOMMENDED_STATUS);
        return coursesManagement;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CoursesManagement createUpdatedEntity(EntityManager em) {
        CoursesManagement coursesManagement = new CoursesManagement()
            .courseId(UPDATED_COURSE_ID)
            .courseName(UPDATED_COURSE_NAME)
            .validFrom(UPDATED_VALID_FROM)
            .validTo(UPDATED_VALID_TO)
            .imagePath(UPDATED_IMAGE_PATH)
            .videoUrl(UPDATED_VIDEO_URL)
            .courseDescription(UPDATED_COURSE_DESCRIPTION)
            .courseObjective(UPDATED_COURSE_OBJECTIVE)
            .url1(UPDATED_URL_1)
            .url2(UPDATED_URL_2)
            .url3(UPDATED_URL_3)
            .quizb4Course(UPDATED_QUIZB_4_COURSE)
            .quiza4Course(UPDATED_QUIZA_4_COURSE)
            .courseStatus(UPDATED_COURSE_STATUS)
            .recommendedStatus(UPDATED_RECOMMENDED_STATUS);
        return coursesManagement;
    }

    @BeforeEach
    public void initTest() {
        coursesManagement = createEntity(em);
    }

    @Test
    @Transactional
    public void createCoursesManagement() throws Exception {
        int databaseSizeBeforeCreate = coursesManagementRepository.findAll().size();
        // Create the CoursesManagement
        CoursesManagementDTO coursesManagementDTO = coursesManagementMapper.toDto(coursesManagement);
        restCoursesManagementMockMvc.perform(post("/api/courses-managements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(coursesManagementDTO)))
            .andExpect(status().isCreated());

        // Validate the CoursesManagement in the database
        List<CoursesManagement> coursesManagementList = coursesManagementRepository.findAll();
        assertThat(coursesManagementList).hasSize(databaseSizeBeforeCreate + 1);
        CoursesManagement testCoursesManagement = coursesManagementList.get(coursesManagementList.size() - 1);
        assertThat(testCoursesManagement.getCourseId()).isEqualTo(DEFAULT_COURSE_ID);
        assertThat(testCoursesManagement.getCourseName()).isEqualTo(DEFAULT_COURSE_NAME);
        assertThat(testCoursesManagement.getValidFrom()).isEqualTo(DEFAULT_VALID_FROM);
        assertThat(testCoursesManagement.getValidTo()).isEqualTo(DEFAULT_VALID_TO);
        assertThat(testCoursesManagement.getImagePath()).isEqualTo(DEFAULT_IMAGE_PATH);
        assertThat(testCoursesManagement.getVideoUrl()).isEqualTo(DEFAULT_VIDEO_URL);
        assertThat(testCoursesManagement.getCourseDescription()).isEqualTo(DEFAULT_COURSE_DESCRIPTION);
        assertThat(testCoursesManagement.getCourseObjective()).isEqualTo(DEFAULT_COURSE_OBJECTIVE);
        assertThat(testCoursesManagement.getUrl1()).isEqualTo(DEFAULT_URL_1);
        assertThat(testCoursesManagement.getUrl2()).isEqualTo(DEFAULT_URL_2);
        assertThat(testCoursesManagement.getUrl3()).isEqualTo(DEFAULT_URL_3);
        assertThat(testCoursesManagement.getQuizb4Course()).isEqualTo(DEFAULT_QUIZB_4_COURSE);
        assertThat(testCoursesManagement.getQuiza4Course()).isEqualTo(DEFAULT_QUIZA_4_COURSE);
        assertThat(testCoursesManagement.getCourseStatus()).isEqualTo(DEFAULT_COURSE_STATUS);
        assertThat(testCoursesManagement.getRecommendedStatus()).isEqualTo(DEFAULT_RECOMMENDED_STATUS);
    }

    @Test
    @Transactional
    public void createCoursesManagementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = coursesManagementRepository.findAll().size();

        // Create the CoursesManagement with an existing ID
        coursesManagement.setId(1L);
        CoursesManagementDTO coursesManagementDTO = coursesManagementMapper.toDto(coursesManagement);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCoursesManagementMockMvc.perform(post("/api/courses-managements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(coursesManagementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CoursesManagement in the database
        List<CoursesManagement> coursesManagementList = coursesManagementRepository.findAll();
        assertThat(coursesManagementList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCoursesManagements() throws Exception {
        // Initialize the database
        coursesManagementRepository.saveAndFlush(coursesManagement);

        // Get all the coursesManagementList
        restCoursesManagementMockMvc.perform(get("/api/courses-managements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(coursesManagement.getId().intValue())))
            .andExpect(jsonPath("$.[*].courseId").value(hasItem(DEFAULT_COURSE_ID)))
            .andExpect(jsonPath("$.[*].courseName").value(hasItem(DEFAULT_COURSE_NAME)))
            .andExpect(jsonPath("$.[*].validFrom").value(hasItem(DEFAULT_VALID_FROM.toString())))
            .andExpect(jsonPath("$.[*].validTo").value(hasItem(DEFAULT_VALID_TO.toString())))
            .andExpect(jsonPath("$.[*].imagePath").value(hasItem(DEFAULT_IMAGE_PATH)))
            .andExpect(jsonPath("$.[*].videoUrl").value(hasItem(DEFAULT_VIDEO_URL)))
            .andExpect(jsonPath("$.[*].courseDescription").value(hasItem(DEFAULT_COURSE_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].courseObjective").value(hasItem(DEFAULT_COURSE_OBJECTIVE)))
            .andExpect(jsonPath("$.[*].url1").value(hasItem(DEFAULT_URL_1)))
            .andExpect(jsonPath("$.[*].url2").value(hasItem(DEFAULT_URL_2)))
            .andExpect(jsonPath("$.[*].url3").value(hasItem(DEFAULT_URL_3)))
            .andExpect(jsonPath("$.[*].quizb4Course").value(hasItem(DEFAULT_QUIZB_4_COURSE)))
            .andExpect(jsonPath("$.[*].quiza4Course").value(hasItem(DEFAULT_QUIZA_4_COURSE)))
            .andExpect(jsonPath("$.[*].courseStatus").value(hasItem(DEFAULT_COURSE_STATUS.toString())))
            .andExpect(jsonPath("$.[*].recommendedStatus").value(hasItem(DEFAULT_RECOMMENDED_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getCoursesManagement() throws Exception {
        // Initialize the database
        coursesManagementRepository.saveAndFlush(coursesManagement);

        // Get the coursesManagement
        restCoursesManagementMockMvc.perform(get("/api/courses-managements/{id}", coursesManagement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(coursesManagement.getId().intValue()))
            .andExpect(jsonPath("$.courseId").value(DEFAULT_COURSE_ID))
            .andExpect(jsonPath("$.courseName").value(DEFAULT_COURSE_NAME))
            .andExpect(jsonPath("$.validFrom").value(DEFAULT_VALID_FROM.toString()))
            .andExpect(jsonPath("$.validTo").value(DEFAULT_VALID_TO.toString()))
            .andExpect(jsonPath("$.imagePath").value(DEFAULT_IMAGE_PATH))
            .andExpect(jsonPath("$.videoUrl").value(DEFAULT_VIDEO_URL))
            .andExpect(jsonPath("$.courseDescription").value(DEFAULT_COURSE_DESCRIPTION))
            .andExpect(jsonPath("$.courseObjective").value(DEFAULT_COURSE_OBJECTIVE))
            .andExpect(jsonPath("$.url1").value(DEFAULT_URL_1))
            .andExpect(jsonPath("$.url2").value(DEFAULT_URL_2))
            .andExpect(jsonPath("$.url3").value(DEFAULT_URL_3))
            .andExpect(jsonPath("$.quizb4Course").value(DEFAULT_QUIZB_4_COURSE))
            .andExpect(jsonPath("$.quiza4Course").value(DEFAULT_QUIZA_4_COURSE))
            .andExpect(jsonPath("$.courseStatus").value(DEFAULT_COURSE_STATUS.toString()))
            .andExpect(jsonPath("$.recommendedStatus").value(DEFAULT_RECOMMENDED_STATUS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingCoursesManagement() throws Exception {
        // Get the coursesManagement
        restCoursesManagementMockMvc.perform(get("/api/courses-managements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCoursesManagement() throws Exception {
        // Initialize the database
        coursesManagementRepository.saveAndFlush(coursesManagement);

        int databaseSizeBeforeUpdate = coursesManagementRepository.findAll().size();

        // Update the coursesManagement
        CoursesManagement updatedCoursesManagement = coursesManagementRepository.findById(coursesManagement.getId()).get();
        // Disconnect from session so that the updates on updatedCoursesManagement are not directly saved in db
        em.detach(updatedCoursesManagement);
        updatedCoursesManagement
            .courseId(UPDATED_COURSE_ID)
            .courseName(UPDATED_COURSE_NAME)
            .validFrom(UPDATED_VALID_FROM)
            .validTo(UPDATED_VALID_TO)
            .imagePath(UPDATED_IMAGE_PATH)
            .videoUrl(UPDATED_VIDEO_URL)
            .courseDescription(UPDATED_COURSE_DESCRIPTION)
            .courseObjective(UPDATED_COURSE_OBJECTIVE)
            .url1(UPDATED_URL_1)
            .url2(UPDATED_URL_2)
            .url3(UPDATED_URL_3)
            .quizb4Course(UPDATED_QUIZB_4_COURSE)
            .quiza4Course(UPDATED_QUIZA_4_COURSE)
            .courseStatus(UPDATED_COURSE_STATUS)
            .recommendedStatus(UPDATED_RECOMMENDED_STATUS);
        CoursesManagementDTO coursesManagementDTO = coursesManagementMapper.toDto(updatedCoursesManagement);

        restCoursesManagementMockMvc.perform(put("/api/courses-managements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(coursesManagementDTO)))
            .andExpect(status().isOk());

        // Validate the CoursesManagement in the database
        List<CoursesManagement> coursesManagementList = coursesManagementRepository.findAll();
        assertThat(coursesManagementList).hasSize(databaseSizeBeforeUpdate);
        CoursesManagement testCoursesManagement = coursesManagementList.get(coursesManagementList.size() - 1);
        assertThat(testCoursesManagement.getCourseId()).isEqualTo(UPDATED_COURSE_ID);
        assertThat(testCoursesManagement.getCourseName()).isEqualTo(UPDATED_COURSE_NAME);
        assertThat(testCoursesManagement.getValidFrom()).isEqualTo(UPDATED_VALID_FROM);
        assertThat(testCoursesManagement.getValidTo()).isEqualTo(UPDATED_VALID_TO);
        assertThat(testCoursesManagement.getImagePath()).isEqualTo(UPDATED_IMAGE_PATH);
        assertThat(testCoursesManagement.getVideoUrl()).isEqualTo(UPDATED_VIDEO_URL);
        assertThat(testCoursesManagement.getCourseDescription()).isEqualTo(UPDATED_COURSE_DESCRIPTION);
        assertThat(testCoursesManagement.getCourseObjective()).isEqualTo(UPDATED_COURSE_OBJECTIVE);
        assertThat(testCoursesManagement.getUrl1()).isEqualTo(UPDATED_URL_1);
        assertThat(testCoursesManagement.getUrl2()).isEqualTo(UPDATED_URL_2);
        assertThat(testCoursesManagement.getUrl3()).isEqualTo(UPDATED_URL_3);
        assertThat(testCoursesManagement.getQuizb4Course()).isEqualTo(UPDATED_QUIZB_4_COURSE);
        assertThat(testCoursesManagement.getQuiza4Course()).isEqualTo(UPDATED_QUIZA_4_COURSE);
        assertThat(testCoursesManagement.getCourseStatus()).isEqualTo(UPDATED_COURSE_STATUS);
        assertThat(testCoursesManagement.getRecommendedStatus()).isEqualTo(UPDATED_RECOMMENDED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingCoursesManagement() throws Exception {
        int databaseSizeBeforeUpdate = coursesManagementRepository.findAll().size();

        // Create the CoursesManagement
        CoursesManagementDTO coursesManagementDTO = coursesManagementMapper.toDto(coursesManagement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCoursesManagementMockMvc.perform(put("/api/courses-managements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(coursesManagementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CoursesManagement in the database
        List<CoursesManagement> coursesManagementList = coursesManagementRepository.findAll();
        assertThat(coursesManagementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCoursesManagement() throws Exception {
        // Initialize the database
        coursesManagementRepository.saveAndFlush(coursesManagement);

        int databaseSizeBeforeDelete = coursesManagementRepository.findAll().size();

        // Delete the coursesManagement
        restCoursesManagementMockMvc.perform(delete("/api/courses-managements/{id}", coursesManagement.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CoursesManagement> coursesManagementList = coursesManagementRepository.findAll();
        assertThat(coursesManagementList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
