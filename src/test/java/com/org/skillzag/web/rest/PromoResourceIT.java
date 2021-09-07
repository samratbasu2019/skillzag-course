package com.org.skillzag.web.rest;

import com.org.skillzag.SkillzagcoursemanagementApp;
import com.org.skillzag.config.TestSecurityConfiguration;
import com.org.skillzag.domain.Promo;
import com.org.skillzag.repository.PromoRepository;
import com.org.skillzag.service.PromoService;
import com.org.skillzag.service.dto.PromoDTO;
import com.org.skillzag.service.mapper.PromoMapper;

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
 * Integration tests for the {@link PromoResource} REST controller.
 */
@SpringBootTest(classes = { SkillzagcoursemanagementApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class PromoResourceIT {

    private static final Long DEFAULT_CART_ID = 1L;
    private static final Long UPDATED_CART_ID = 2L;

    private static final String DEFAULT_PROMO_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PROMO_CODE = "BBBBBBBBBB";

    private static final Instant DEFAULT_VALID_FROM = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_VALID_FROM = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_VALID_TO = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_VALID_TO = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private PromoRepository promoRepository;

    @Autowired
    private PromoMapper promoMapper;

    @Autowired
    private PromoService promoService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPromoMockMvc;

    private Promo promo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Promo createEntity(EntityManager em) {
        Promo promo = new Promo()
            .cartId(DEFAULT_CART_ID)
            .promoCode(DEFAULT_PROMO_CODE)
            .validFrom(DEFAULT_VALID_FROM)
            .validTo(DEFAULT_VALID_TO)
            .createdBy(DEFAULT_CREATED_BY)
            .creationDate(DEFAULT_CREATION_DATE);
        return promo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Promo createUpdatedEntity(EntityManager em) {
        Promo promo = new Promo()
            .cartId(UPDATED_CART_ID)
            .promoCode(UPDATED_PROMO_CODE)
            .validFrom(UPDATED_VALID_FROM)
            .validTo(UPDATED_VALID_TO)
            .createdBy(UPDATED_CREATED_BY)
            .creationDate(UPDATED_CREATION_DATE);
        return promo;
    }

    @BeforeEach
    public void initTest() {
        promo = createEntity(em);
    }

    @Test
    @Transactional
    public void createPromo() throws Exception {
        int databaseSizeBeforeCreate = promoRepository.findAll().size();
        // Create the Promo
        PromoDTO promoDTO = promoMapper.toDto(promo);
        restPromoMockMvc.perform(post("/api/promos").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(promoDTO)))
            .andExpect(status().isCreated());

        // Validate the Promo in the database
        List<Promo> promoList = promoRepository.findAll();
        assertThat(promoList).hasSize(databaseSizeBeforeCreate + 1);
        Promo testPromo = promoList.get(promoList.size() - 1);
        assertThat(testPromo.getCartId()).isEqualTo(DEFAULT_CART_ID);
        assertThat(testPromo.getPromoCode()).isEqualTo(DEFAULT_PROMO_CODE);
        assertThat(testPromo.getValidFrom()).isEqualTo(DEFAULT_VALID_FROM);
        assertThat(testPromo.getValidTo()).isEqualTo(DEFAULT_VALID_TO);
        assertThat(testPromo.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testPromo.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
    }

    @Test
    @Transactional
    public void createPromoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = promoRepository.findAll().size();

        // Create the Promo with an existing ID
        promo.setId(1L);
        PromoDTO promoDTO = promoMapper.toDto(promo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPromoMockMvc.perform(post("/api/promos").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(promoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Promo in the database
        List<Promo> promoList = promoRepository.findAll();
        assertThat(promoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPromos() throws Exception {
        // Initialize the database
        promoRepository.saveAndFlush(promo);

        // Get all the promoList
        restPromoMockMvc.perform(get("/api/promos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(promo.getId().intValue())))
            .andExpect(jsonPath("$.[*].cartId").value(hasItem(DEFAULT_CART_ID.intValue())))
            .andExpect(jsonPath("$.[*].promoCode").value(hasItem(DEFAULT_PROMO_CODE)))
            .andExpect(jsonPath("$.[*].validFrom").value(hasItem(DEFAULT_VALID_FROM.toString())))
            .andExpect(jsonPath("$.[*].validTo").value(hasItem(DEFAULT_VALID_TO.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getPromo() throws Exception {
        // Initialize the database
        promoRepository.saveAndFlush(promo);

        // Get the promo
        restPromoMockMvc.perform(get("/api/promos/{id}", promo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(promo.getId().intValue()))
            .andExpect(jsonPath("$.cartId").value(DEFAULT_CART_ID.intValue()))
            .andExpect(jsonPath("$.promoCode").value(DEFAULT_PROMO_CODE))
            .andExpect(jsonPath("$.validFrom").value(DEFAULT_VALID_FROM.toString()))
            .andExpect(jsonPath("$.validTo").value(DEFAULT_VALID_TO.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingPromo() throws Exception {
        // Get the promo
        restPromoMockMvc.perform(get("/api/promos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePromo() throws Exception {
        // Initialize the database
        promoRepository.saveAndFlush(promo);

        int databaseSizeBeforeUpdate = promoRepository.findAll().size();

        // Update the promo
        Promo updatedPromo = promoRepository.findById(promo.getId()).get();
        // Disconnect from session so that the updates on updatedPromo are not directly saved in db
        em.detach(updatedPromo);
        updatedPromo
            .cartId(UPDATED_CART_ID)
            .promoCode(UPDATED_PROMO_CODE)
            .validFrom(UPDATED_VALID_FROM)
            .validTo(UPDATED_VALID_TO)
            .createdBy(UPDATED_CREATED_BY)
            .creationDate(UPDATED_CREATION_DATE);
        PromoDTO promoDTO = promoMapper.toDto(updatedPromo);

        restPromoMockMvc.perform(put("/api/promos").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(promoDTO)))
            .andExpect(status().isOk());

        // Validate the Promo in the database
        List<Promo> promoList = promoRepository.findAll();
        assertThat(promoList).hasSize(databaseSizeBeforeUpdate);
        Promo testPromo = promoList.get(promoList.size() - 1);
        assertThat(testPromo.getCartId()).isEqualTo(UPDATED_CART_ID);
        assertThat(testPromo.getPromoCode()).isEqualTo(UPDATED_PROMO_CODE);
        assertThat(testPromo.getValidFrom()).isEqualTo(UPDATED_VALID_FROM);
        assertThat(testPromo.getValidTo()).isEqualTo(UPDATED_VALID_TO);
        assertThat(testPromo.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testPromo.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingPromo() throws Exception {
        int databaseSizeBeforeUpdate = promoRepository.findAll().size();

        // Create the Promo
        PromoDTO promoDTO = promoMapper.toDto(promo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPromoMockMvc.perform(put("/api/promos").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(promoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Promo in the database
        List<Promo> promoList = promoRepository.findAll();
        assertThat(promoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePromo() throws Exception {
        // Initialize the database
        promoRepository.saveAndFlush(promo);

        int databaseSizeBeforeDelete = promoRepository.findAll().size();

        // Delete the promo
        restPromoMockMvc.perform(delete("/api/promos/{id}", promo.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Promo> promoList = promoRepository.findAll();
        assertThat(promoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
