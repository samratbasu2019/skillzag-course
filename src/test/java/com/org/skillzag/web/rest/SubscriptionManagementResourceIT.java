package com.org.skillzag.web.rest;

import com.org.skillzag.SkillzagcoursemanagementApp;
import com.org.skillzag.config.TestSecurityConfiguration;
import com.org.skillzag.domain.SubscriptionManagement;
import com.org.skillzag.repository.SubscriptionManagementRepository;
import com.org.skillzag.service.SubscriptionManagementService;
import com.org.skillzag.service.dto.SubscriptionManagementDTO;
import com.org.skillzag.service.mapper.SubscriptionManagementMapper;

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

import com.org.skillzag.domain.enumeration.BlockSubscription;
/**
 * Integration tests for the {@link SubscriptionManagementResource} REST controller.
 */
@SpringBootTest(classes = { SkillzagcoursemanagementApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class SubscriptionManagementResourceIT {

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SUBSCRIPTION_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIPTION_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_SUBSCRIPTION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIPTION_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SUBSCRIPTION_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIPTION_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_SUBSCRIPTION_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_SUBSCRIPTION_STATUS = "BBBBBBBBBB";

    private static final Instant DEFAULT_SUBSCRIPTION_STARTDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_SUBSCRIPTION_STARTDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_SUBSCRIPTION_ENDDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_SUBSCRIPTION_ENDDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_PAYMENT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_STATUS = "BBBBBBBBBB";

    private static final Instant DEFAULT_SUBSCRIPTION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_SUBSCRIPTION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Double DEFAULT_AMOUNT = 1D;
    private static final Double UPDATED_AMOUNT = 2D;

    private static final Double DEFAULT_DISCOUNT_PERCENTAGE = 1D;
    private static final Double UPDATED_DISCOUNT_PERCENTAGE = 2D;

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final BlockSubscription DEFAULT_BLOCK_SUBSCRIPTION = BlockSubscription.YES;
    private static final BlockSubscription UPDATED_BLOCK_SUBSCRIPTION = BlockSubscription.NO;

    @Autowired
    private SubscriptionManagementRepository subscriptionManagementRepository;

    @Autowired
    private SubscriptionManagementMapper subscriptionManagementMapper;

    @Autowired
    private SubscriptionManagementService subscriptionManagementService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSubscriptionManagementMockMvc;

    private SubscriptionManagement subscriptionManagement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SubscriptionManagement createEntity(EntityManager em) {
        SubscriptionManagement subscriptionManagement = new SubscriptionManagement()
            .userId(DEFAULT_USER_ID)
            .subscriptionType(DEFAULT_SUBSCRIPTION_TYPE)
            .subscriptionName(DEFAULT_SUBSCRIPTION_NAME)
            .subscriptionDescription(DEFAULT_SUBSCRIPTION_DESCRIPTION)
            .subscriptionStatus(DEFAULT_SUBSCRIPTION_STATUS)
            .subscriptionStartdate(DEFAULT_SUBSCRIPTION_STARTDATE)
            .subscriptionEnddate(DEFAULT_SUBSCRIPTION_ENDDATE)
            .paymentStatus(DEFAULT_PAYMENT_STATUS)
            .subscriptionDate(DEFAULT_SUBSCRIPTION_DATE)
            .amount(DEFAULT_AMOUNT)
            .discountPercentage(DEFAULT_DISCOUNT_PERCENTAGE)
            .type(DEFAULT_TYPE)
            .blockSubscription(DEFAULT_BLOCK_SUBSCRIPTION);
        return subscriptionManagement;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SubscriptionManagement createUpdatedEntity(EntityManager em) {
        SubscriptionManagement subscriptionManagement = new SubscriptionManagement()
            .userId(UPDATED_USER_ID)
            .subscriptionType(UPDATED_SUBSCRIPTION_TYPE)
            .subscriptionName(UPDATED_SUBSCRIPTION_NAME)
            .subscriptionDescription(UPDATED_SUBSCRIPTION_DESCRIPTION)
            .subscriptionStatus(UPDATED_SUBSCRIPTION_STATUS)
            .subscriptionStartdate(UPDATED_SUBSCRIPTION_STARTDATE)
            .subscriptionEnddate(UPDATED_SUBSCRIPTION_ENDDATE)
            .paymentStatus(UPDATED_PAYMENT_STATUS)
            .subscriptionDate(UPDATED_SUBSCRIPTION_DATE)
            .amount(UPDATED_AMOUNT)
            .discountPercentage(UPDATED_DISCOUNT_PERCENTAGE)
            .type(UPDATED_TYPE)
            .blockSubscription(UPDATED_BLOCK_SUBSCRIPTION);
        return subscriptionManagement;
    }

    @BeforeEach
    public void initTest() {
        subscriptionManagement = createEntity(em);
    }

    @Test
    @Transactional
    public void createSubscriptionManagement() throws Exception {
        int databaseSizeBeforeCreate = subscriptionManagementRepository.findAll().size();
        // Create the SubscriptionManagement
        SubscriptionManagementDTO subscriptionManagementDTO = subscriptionManagementMapper.toDto(subscriptionManagement);
        restSubscriptionManagementMockMvc.perform(post("/api/subscription-managements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionManagementDTO)))
            .andExpect(status().isCreated());

        // Validate the SubscriptionManagement in the database
        List<SubscriptionManagement> subscriptionManagementList = subscriptionManagementRepository.findAll();
        assertThat(subscriptionManagementList).hasSize(databaseSizeBeforeCreate + 1);
        SubscriptionManagement testSubscriptionManagement = subscriptionManagementList.get(subscriptionManagementList.size() - 1);
        assertThat(testSubscriptionManagement.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testSubscriptionManagement.getSubscriptionType()).isEqualTo(DEFAULT_SUBSCRIPTION_TYPE);
        assertThat(testSubscriptionManagement.getSubscriptionName()).isEqualTo(DEFAULT_SUBSCRIPTION_NAME);
        assertThat(testSubscriptionManagement.getSubscriptionDescription()).isEqualTo(DEFAULT_SUBSCRIPTION_DESCRIPTION);
        assertThat(testSubscriptionManagement.getSubscriptionStatus()).isEqualTo(DEFAULT_SUBSCRIPTION_STATUS);
        assertThat(testSubscriptionManagement.getSubscriptionStartdate()).isEqualTo(DEFAULT_SUBSCRIPTION_STARTDATE);
        assertThat(testSubscriptionManagement.getSubscriptionEnddate()).isEqualTo(DEFAULT_SUBSCRIPTION_ENDDATE);
        assertThat(testSubscriptionManagement.getPaymentStatus()).isEqualTo(DEFAULT_PAYMENT_STATUS);
        assertThat(testSubscriptionManagement.getSubscriptionDate()).isEqualTo(DEFAULT_SUBSCRIPTION_DATE);
        assertThat(testSubscriptionManagement.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testSubscriptionManagement.getDiscountPercentage()).isEqualTo(DEFAULT_DISCOUNT_PERCENTAGE);
        assertThat(testSubscriptionManagement.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testSubscriptionManagement.getBlockSubscription()).isEqualTo(DEFAULT_BLOCK_SUBSCRIPTION);
    }

    @Test
    @Transactional
    public void createSubscriptionManagementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = subscriptionManagementRepository.findAll().size();

        // Create the SubscriptionManagement with an existing ID
        subscriptionManagement.setId(1L);
        SubscriptionManagementDTO subscriptionManagementDTO = subscriptionManagementMapper.toDto(subscriptionManagement);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSubscriptionManagementMockMvc.perform(post("/api/subscription-managements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionManagementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SubscriptionManagement in the database
        List<SubscriptionManagement> subscriptionManagementList = subscriptionManagementRepository.findAll();
        assertThat(subscriptionManagementList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSubscriptionManagements() throws Exception {
        // Initialize the database
        subscriptionManagementRepository.saveAndFlush(subscriptionManagement);

        // Get all the subscriptionManagementList
        restSubscriptionManagementMockMvc.perform(get("/api/subscription-managements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(subscriptionManagement.getId().intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)))
            .andExpect(jsonPath("$.[*].subscriptionType").value(hasItem(DEFAULT_SUBSCRIPTION_TYPE)))
            .andExpect(jsonPath("$.[*].subscriptionName").value(hasItem(DEFAULT_SUBSCRIPTION_NAME)))
            .andExpect(jsonPath("$.[*].subscriptionDescription").value(hasItem(DEFAULT_SUBSCRIPTION_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].subscriptionStatus").value(hasItem(DEFAULT_SUBSCRIPTION_STATUS)))
            .andExpect(jsonPath("$.[*].subscriptionStartdate").value(hasItem(DEFAULT_SUBSCRIPTION_STARTDATE.toString())))
            .andExpect(jsonPath("$.[*].subscriptionEnddate").value(hasItem(DEFAULT_SUBSCRIPTION_ENDDATE.toString())))
            .andExpect(jsonPath("$.[*].paymentStatus").value(hasItem(DEFAULT_PAYMENT_STATUS)))
            .andExpect(jsonPath("$.[*].subscriptionDate").value(hasItem(DEFAULT_SUBSCRIPTION_DATE.toString())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].discountPercentage").value(hasItem(DEFAULT_DISCOUNT_PERCENTAGE.doubleValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].blockSubscription").value(hasItem(DEFAULT_BLOCK_SUBSCRIPTION.toString())));
    }
    
    @Test
    @Transactional
    public void getSubscriptionManagement() throws Exception {
        // Initialize the database
        subscriptionManagementRepository.saveAndFlush(subscriptionManagement);

        // Get the subscriptionManagement
        restSubscriptionManagementMockMvc.perform(get("/api/subscription-managements/{id}", subscriptionManagement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(subscriptionManagement.getId().intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID))
            .andExpect(jsonPath("$.subscriptionType").value(DEFAULT_SUBSCRIPTION_TYPE))
            .andExpect(jsonPath("$.subscriptionName").value(DEFAULT_SUBSCRIPTION_NAME))
            .andExpect(jsonPath("$.subscriptionDescription").value(DEFAULT_SUBSCRIPTION_DESCRIPTION))
            .andExpect(jsonPath("$.subscriptionStatus").value(DEFAULT_SUBSCRIPTION_STATUS))
            .andExpect(jsonPath("$.subscriptionStartdate").value(DEFAULT_SUBSCRIPTION_STARTDATE.toString()))
            .andExpect(jsonPath("$.subscriptionEnddate").value(DEFAULT_SUBSCRIPTION_ENDDATE.toString()))
            .andExpect(jsonPath("$.paymentStatus").value(DEFAULT_PAYMENT_STATUS))
            .andExpect(jsonPath("$.subscriptionDate").value(DEFAULT_SUBSCRIPTION_DATE.toString()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.discountPercentage").value(DEFAULT_DISCOUNT_PERCENTAGE.doubleValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.blockSubscription").value(DEFAULT_BLOCK_SUBSCRIPTION.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingSubscriptionManagement() throws Exception {
        // Get the subscriptionManagement
        restSubscriptionManagementMockMvc.perform(get("/api/subscription-managements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSubscriptionManagement() throws Exception {
        // Initialize the database
        subscriptionManagementRepository.saveAndFlush(subscriptionManagement);

        int databaseSizeBeforeUpdate = subscriptionManagementRepository.findAll().size();

        // Update the subscriptionManagement
        SubscriptionManagement updatedSubscriptionManagement = subscriptionManagementRepository.findById(subscriptionManagement.getId()).get();
        // Disconnect from session so that the updates on updatedSubscriptionManagement are not directly saved in db
        em.detach(updatedSubscriptionManagement);
        updatedSubscriptionManagement
            .userId(UPDATED_USER_ID)
            .subscriptionType(UPDATED_SUBSCRIPTION_TYPE)
            .subscriptionName(UPDATED_SUBSCRIPTION_NAME)
            .subscriptionDescription(UPDATED_SUBSCRIPTION_DESCRIPTION)
            .subscriptionStatus(UPDATED_SUBSCRIPTION_STATUS)
            .subscriptionStartdate(UPDATED_SUBSCRIPTION_STARTDATE)
            .subscriptionEnddate(UPDATED_SUBSCRIPTION_ENDDATE)
            .paymentStatus(UPDATED_PAYMENT_STATUS)
            .subscriptionDate(UPDATED_SUBSCRIPTION_DATE)
            .amount(UPDATED_AMOUNT)
            .discountPercentage(UPDATED_DISCOUNT_PERCENTAGE)
            .type(UPDATED_TYPE)
            .blockSubscription(UPDATED_BLOCK_SUBSCRIPTION);
        SubscriptionManagementDTO subscriptionManagementDTO = subscriptionManagementMapper.toDto(updatedSubscriptionManagement);

        restSubscriptionManagementMockMvc.perform(put("/api/subscription-managements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionManagementDTO)))
            .andExpect(status().isOk());

        // Validate the SubscriptionManagement in the database
        List<SubscriptionManagement> subscriptionManagementList = subscriptionManagementRepository.findAll();
        assertThat(subscriptionManagementList).hasSize(databaseSizeBeforeUpdate);
        SubscriptionManagement testSubscriptionManagement = subscriptionManagementList.get(subscriptionManagementList.size() - 1);
        assertThat(testSubscriptionManagement.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testSubscriptionManagement.getSubscriptionType()).isEqualTo(UPDATED_SUBSCRIPTION_TYPE);
        assertThat(testSubscriptionManagement.getSubscriptionName()).isEqualTo(UPDATED_SUBSCRIPTION_NAME);
        assertThat(testSubscriptionManagement.getSubscriptionDescription()).isEqualTo(UPDATED_SUBSCRIPTION_DESCRIPTION);
        assertThat(testSubscriptionManagement.getSubscriptionStatus()).isEqualTo(UPDATED_SUBSCRIPTION_STATUS);
        assertThat(testSubscriptionManagement.getSubscriptionStartdate()).isEqualTo(UPDATED_SUBSCRIPTION_STARTDATE);
        assertThat(testSubscriptionManagement.getSubscriptionEnddate()).isEqualTo(UPDATED_SUBSCRIPTION_ENDDATE);
        assertThat(testSubscriptionManagement.getPaymentStatus()).isEqualTo(UPDATED_PAYMENT_STATUS);
        assertThat(testSubscriptionManagement.getSubscriptionDate()).isEqualTo(UPDATED_SUBSCRIPTION_DATE);
        assertThat(testSubscriptionManagement.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testSubscriptionManagement.getDiscountPercentage()).isEqualTo(UPDATED_DISCOUNT_PERCENTAGE);
        assertThat(testSubscriptionManagement.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testSubscriptionManagement.getBlockSubscription()).isEqualTo(UPDATED_BLOCK_SUBSCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingSubscriptionManagement() throws Exception {
        int databaseSizeBeforeUpdate = subscriptionManagementRepository.findAll().size();

        // Create the SubscriptionManagement
        SubscriptionManagementDTO subscriptionManagementDTO = subscriptionManagementMapper.toDto(subscriptionManagement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSubscriptionManagementMockMvc.perform(put("/api/subscription-managements").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subscriptionManagementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SubscriptionManagement in the database
        List<SubscriptionManagement> subscriptionManagementList = subscriptionManagementRepository.findAll();
        assertThat(subscriptionManagementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSubscriptionManagement() throws Exception {
        // Initialize the database
        subscriptionManagementRepository.saveAndFlush(subscriptionManagement);

        int databaseSizeBeforeDelete = subscriptionManagementRepository.findAll().size();

        // Delete the subscriptionManagement
        restSubscriptionManagementMockMvc.perform(delete("/api/subscription-managements/{id}", subscriptionManagement.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SubscriptionManagement> subscriptionManagementList = subscriptionManagementRepository.findAll();
        assertThat(subscriptionManagementList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
