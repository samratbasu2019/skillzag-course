package com.org.skillzag.web.rest;

import com.org.skillzag.service.SubscriptionManagementService;
import com.org.skillzag.web.rest.errors.BadRequestAlertException;
import com.org.skillzag.service.dto.SubscriptionManagementDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.org.skillzag.domain.SubscriptionManagement}.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SubscriptionManagementResource {

    private final Logger log = LoggerFactory.getLogger(SubscriptionManagementResource.class);

    private static final String ENTITY_NAME = "skillzagcoursemanagementSubscriptionManagement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SubscriptionManagementService subscriptionManagementService;

    public SubscriptionManagementResource(SubscriptionManagementService subscriptionManagementService) {
        this.subscriptionManagementService = subscriptionManagementService;
    }

    /**
     * {@code POST  /subscription-managements} : Create a new subscriptionManagement.
     *
     * @param subscriptionManagementDTO the subscriptionManagementDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new subscriptionManagementDTO, or with status {@code 400 (Bad Request)} if the subscriptionManagement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/subscription-managements")
    public ResponseEntity<SubscriptionManagementDTO> createSubscriptionManagement(@RequestBody SubscriptionManagementDTO subscriptionManagementDTO) throws URISyntaxException {
        log.debug("REST request to save SubscriptionManagement : {}", subscriptionManagementDTO);
        if (subscriptionManagementDTO.getId() != null) {
            throw new BadRequestAlertException("A new subscriptionManagement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SubscriptionManagementDTO result = subscriptionManagementService.save(subscriptionManagementDTO);
        return ResponseEntity.created(new URI("/api/subscription-managements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /subscription-managements} : Updates an existing subscriptionManagement.
     *
     * @param subscriptionManagementDTO the subscriptionManagementDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated subscriptionManagementDTO,
     * or with status {@code 400 (Bad Request)} if the subscriptionManagementDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the subscriptionManagementDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/subscription-managements")
    public ResponseEntity<SubscriptionManagementDTO> updateSubscriptionManagement(@RequestBody SubscriptionManagementDTO subscriptionManagementDTO) throws URISyntaxException {
        log.debug("REST request to update SubscriptionManagement : {}", subscriptionManagementDTO);
        if (subscriptionManagementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SubscriptionManagementDTO result = subscriptionManagementService.save(subscriptionManagementDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, subscriptionManagementDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /subscription-managements} : get all the subscriptionManagements.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of subscriptionManagements in body.
     */
    @GetMapping("/subscription-managements")
    public ResponseEntity<List<SubscriptionManagementDTO>> getAllSubscriptionManagements(Pageable pageable) {
        log.debug("REST request to get a page of SubscriptionManagements");
        Page<SubscriptionManagementDTO> page = subscriptionManagementService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /subscription-managements/:id} : get the "id" subscriptionManagement.
     *
     * @param id the id of the subscriptionManagementDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the subscriptionManagementDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/subscription-managements/{id}")
    public ResponseEntity<SubscriptionManagementDTO> getSubscriptionManagement(@PathVariable Long id) {
        log.debug("REST request to get SubscriptionManagement : {}", id);
        Optional<SubscriptionManagementDTO> subscriptionManagementDTO = subscriptionManagementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(subscriptionManagementDTO);
    }

    /**
     * {@code DELETE  /subscription-managements/:id} : delete the "id" subscriptionManagement.
     *
     * @param id the id of the subscriptionManagementDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/subscription-managements/{id}")
    public ResponseEntity<Void> deleteSubscriptionManagement(@PathVariable Long id) {
        log.debug("REST request to delete SubscriptionManagement : {}", id);
        subscriptionManagementService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
