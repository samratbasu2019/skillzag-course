package com.org.skillzag.web.rest;

import com.org.skillzag.service.UserActivityService;
import com.org.skillzag.web.rest.errors.BadRequestAlertException;
import com.org.skillzag.service.dto.UserActivityDTO;

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
 * REST controller for managing {@link com.org.skillzag.domain.UserActivity}.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserActivityResource {

    private final Logger log = LoggerFactory.getLogger(UserActivityResource.class);

    private static final String ENTITY_NAME = "skillzagcoursemanagementUserActivity";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserActivityService userActivityService;

    public UserActivityResource(UserActivityService userActivityService) {
        this.userActivityService = userActivityService;
    }

    /**
     * {@code POST  /user-activities} : Create a new userActivity.
     *
     * @param userActivityDTO the userActivityDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userActivityDTO, or with status {@code 400 (Bad Request)} if the userActivity has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/user-activities")
    public ResponseEntity<UserActivityDTO> createUserActivity(@RequestBody UserActivityDTO userActivityDTO) throws URISyntaxException {
        log.debug("REST request to save UserActivity : {}", userActivityDTO);
        if (userActivityDTO.getId() != null) {
            throw new BadRequestAlertException("A new userActivity cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserActivityDTO result = userActivityService.save(userActivityDTO);
        return ResponseEntity.created(new URI("/api/user-activities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /user-activities} : Updates an existing userActivity.
     *
     * @param userActivityDTO the userActivityDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userActivityDTO,
     * or with status {@code 400 (Bad Request)} if the userActivityDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userActivityDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/user-activities")
    public ResponseEntity<UserActivityDTO> updateUserActivity(@RequestBody UserActivityDTO userActivityDTO) throws URISyntaxException {
        log.debug("REST request to update UserActivity : {}", userActivityDTO);
        if (userActivityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserActivityDTO result = userActivityService.save(userActivityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, userActivityDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /user-activities} : get all the userActivities.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userActivities in body.
     */
    @GetMapping("/user-activities")
    public ResponseEntity<List<UserActivityDTO>> getAllUserActivities(Pageable pageable) {
        log.debug("REST request to get a page of UserActivities");
        Page<UserActivityDTO> page = userActivityService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /user-activities/:id} : get the "id" userActivity.
     *
     * @param id the id of the userActivityDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userActivityDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-activities/{id}")
    public ResponseEntity<UserActivityDTO> getUserActivity(@PathVariable Long id) {
        log.debug("REST request to get UserActivity : {}", id);
        Optional<UserActivityDTO> userActivityDTO = userActivityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userActivityDTO);
    }

    /**
     * {@code DELETE  /user-activities/:id} : delete the "id" userActivity.
     *
     * @param id the id of the userActivityDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/user-activities/{id}")
    public ResponseEntity<Void> deleteUserActivity(@PathVariable Long id) {
        log.debug("REST request to delete UserActivity : {}", id);
        userActivityService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
