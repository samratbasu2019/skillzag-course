package com.org.skillzag.web.rest;

import com.org.skillzag.SkillzagcoursemanagementApp;
import com.org.skillzag.config.TestSecurityConfiguration;
import com.org.skillzag.domain.Cart;
import com.org.skillzag.repository.CartRepository;
import com.org.skillzag.service.CartService;
import com.org.skillzag.service.dto.CartDTO;
import com.org.skillzag.service.mapper.CartMapper;

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
 * Integration tests for the {@link CartResource} REST controller.
 */
@SpringBootTest(classes = { SkillzagcoursemanagementApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class CartResourceIT {

    private static final Long DEFAULT_COURSE_ID = 1L;
    private static final Long UPDATED_COURSE_ID = 2L;

    private static final Long DEFAULT_USER_ID = 1L;
    private static final Long UPDATED_USER_ID = 2L;

    private static final Long DEFAULT_SUBCRIPTION_ID = 1L;
    private static final Long UPDATED_SUBCRIPTION_ID = 2L;

    private static final Instant DEFAULT_CHECKOUT_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CHECKOUT_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CHECK_OUT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_CHECK_OUT_STATUS = "BBBBBBBBBB";

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private CartService cartService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCartMockMvc;

    private Cart cart;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cart createEntity(EntityManager em) {
        Cart cart = new Cart()
            .courseId(DEFAULT_COURSE_ID)
            .userId(DEFAULT_USER_ID)
            .subcriptionId(DEFAULT_SUBCRIPTION_ID)
            .checkoutDate(DEFAULT_CHECKOUT_DATE)
            .checkOutStatus(DEFAULT_CHECK_OUT_STATUS);
        return cart;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cart createUpdatedEntity(EntityManager em) {
        Cart cart = new Cart()
            .courseId(UPDATED_COURSE_ID)
            .userId(UPDATED_USER_ID)
            .subcriptionId(UPDATED_SUBCRIPTION_ID)
            .checkoutDate(UPDATED_CHECKOUT_DATE)
            .checkOutStatus(UPDATED_CHECK_OUT_STATUS);
        return cart;
    }

    @BeforeEach
    public void initTest() {
        cart = createEntity(em);
    }

    @Test
    @Transactional
    public void createCart() throws Exception {
        int databaseSizeBeforeCreate = cartRepository.findAll().size();
        // Create the Cart
        CartDTO cartDTO = cartMapper.toDto(cart);
        restCartMockMvc.perform(post("/api/carts").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cartDTO)))
            .andExpect(status().isCreated());

        // Validate the Cart in the database
        List<Cart> cartList = cartRepository.findAll();
        assertThat(cartList).hasSize(databaseSizeBeforeCreate + 1);
        Cart testCart = cartList.get(cartList.size() - 1);
        assertThat(testCart.getCourseId()).isEqualTo(DEFAULT_COURSE_ID);
        assertThat(testCart.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testCart.getSubcriptionId()).isEqualTo(DEFAULT_SUBCRIPTION_ID);
        assertThat(testCart.getCheckoutDate()).isEqualTo(DEFAULT_CHECKOUT_DATE);
        assertThat(testCart.getCheckOutStatus()).isEqualTo(DEFAULT_CHECK_OUT_STATUS);
    }

    @Test
    @Transactional
    public void createCartWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cartRepository.findAll().size();

        // Create the Cart with an existing ID
        cart.setId(1L);
        CartDTO cartDTO = cartMapper.toDto(cart);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCartMockMvc.perform(post("/api/carts").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cartDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Cart in the database
        List<Cart> cartList = cartRepository.findAll();
        assertThat(cartList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCarts() throws Exception {
        // Initialize the database
        cartRepository.saveAndFlush(cart);

        // Get all the cartList
        restCartMockMvc.perform(get("/api/carts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cart.getId().intValue())))
            .andExpect(jsonPath("$.[*].courseId").value(hasItem(DEFAULT_COURSE_ID.intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].subcriptionId").value(hasItem(DEFAULT_SUBCRIPTION_ID.intValue())))
            .andExpect(jsonPath("$.[*].checkoutDate").value(hasItem(DEFAULT_CHECKOUT_DATE.toString())))
            .andExpect(jsonPath("$.[*].checkOutStatus").value(hasItem(DEFAULT_CHECK_OUT_STATUS)));
    }
    
    @Test
    @Transactional
    public void getCart() throws Exception {
        // Initialize the database
        cartRepository.saveAndFlush(cart);

        // Get the cart
        restCartMockMvc.perform(get("/api/carts/{id}", cart.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cart.getId().intValue()))
            .andExpect(jsonPath("$.courseId").value(DEFAULT_COURSE_ID.intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.intValue()))
            .andExpect(jsonPath("$.subcriptionId").value(DEFAULT_SUBCRIPTION_ID.intValue()))
            .andExpect(jsonPath("$.checkoutDate").value(DEFAULT_CHECKOUT_DATE.toString()))
            .andExpect(jsonPath("$.checkOutStatus").value(DEFAULT_CHECK_OUT_STATUS));
    }
    @Test
    @Transactional
    public void getNonExistingCart() throws Exception {
        // Get the cart
        restCartMockMvc.perform(get("/api/carts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCart() throws Exception {
        // Initialize the database
        cartRepository.saveAndFlush(cart);

        int databaseSizeBeforeUpdate = cartRepository.findAll().size();

        // Update the cart
        Cart updatedCart = cartRepository.findById(cart.getId()).get();
        // Disconnect from session so that the updates on updatedCart are not directly saved in db
        em.detach(updatedCart);
        updatedCart
            .courseId(UPDATED_COURSE_ID)
            .userId(UPDATED_USER_ID)
            .subcriptionId(UPDATED_SUBCRIPTION_ID)
            .checkoutDate(UPDATED_CHECKOUT_DATE)
            .checkOutStatus(UPDATED_CHECK_OUT_STATUS);
        CartDTO cartDTO = cartMapper.toDto(updatedCart);

        restCartMockMvc.perform(put("/api/carts").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cartDTO)))
            .andExpect(status().isOk());

        // Validate the Cart in the database
        List<Cart> cartList = cartRepository.findAll();
        assertThat(cartList).hasSize(databaseSizeBeforeUpdate);
        Cart testCart = cartList.get(cartList.size() - 1);
        assertThat(testCart.getCourseId()).isEqualTo(UPDATED_COURSE_ID);
        assertThat(testCart.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testCart.getSubcriptionId()).isEqualTo(UPDATED_SUBCRIPTION_ID);
        assertThat(testCart.getCheckoutDate()).isEqualTo(UPDATED_CHECKOUT_DATE);
        assertThat(testCart.getCheckOutStatus()).isEqualTo(UPDATED_CHECK_OUT_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingCart() throws Exception {
        int databaseSizeBeforeUpdate = cartRepository.findAll().size();

        // Create the Cart
        CartDTO cartDTO = cartMapper.toDto(cart);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCartMockMvc.perform(put("/api/carts").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cartDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Cart in the database
        List<Cart> cartList = cartRepository.findAll();
        assertThat(cartList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCart() throws Exception {
        // Initialize the database
        cartRepository.saveAndFlush(cart);

        int databaseSizeBeforeDelete = cartRepository.findAll().size();

        // Delete the cart
        restCartMockMvc.perform(delete("/api/carts/{id}", cart.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Cart> cartList = cartRepository.findAll();
        assertThat(cartList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
