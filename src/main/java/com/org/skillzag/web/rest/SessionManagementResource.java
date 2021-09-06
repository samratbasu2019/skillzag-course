package com.org.skillzag.web.rest;

import com.org.skillzag.service.SessionManagementService;
import com.org.skillzag.web.rest.errors.BadRequestAlertException;
import com.org.skillzag.service.dto.SessionManagementDTO;

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
 * REST controller for managing {@link com.org.skillzag.domain.SessionManagement}.
 */
@RestController
@RequestMapping("/api")
public class SessionManagementResource {

    private final Logger log = LoggerFactory.getLogger(SessionManagementResource.class);

    private static final String ENTITY_NAME = "skillzagcoursemanagementSessionManagement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SessionManagementService sessionManagementService;

    public SessionManagementResource(SessionManagementService sessionManagementService) {
        this.sessionManagementService = sessionManagementService;
    }

    /**
     * {@code POST  /session-managements} : Create a new sessionManagement.
     *
     * @param sessionManagementDTO the sessionManagementDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sessionManagementDTO, or with status {@code 400 (Bad Request)} if the sessionManagement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/session-managements")
    public ResponseEntity<SessionManagementDTO> createSessionManagement(@RequestBody SessionManagementDTO sessionManagementDTO) throws URISyntaxException {
        log.debug("REST request to save SessionManagement : {}", sessionManagementDTO);
        if (sessionManagementDTO.getId() != null) {
            throw new BadRequestAlertException("A new sessionManagement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SessionManagementDTO result = sessionManagementService.save(sessionManagementDTO);
        return ResponseEntity.created(new URI("/api/session-managements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /session-managements} : Updates an existing sessionManagement.
     *
     * @param sessionManagementDTO the sessionManagementDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sessionManagementDTO,
     * or with status {@code 400 (Bad Request)} if the sessionManagementDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sessionManagementDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/session-managements")
    public ResponseEntity<SessionManagementDTO> updateSessionManagement(@RequestBody SessionManagementDTO sessionManagementDTO) throws URISyntaxException {
        log.debug("REST request to update SessionManagement : {}", sessionManagementDTO);
        if (sessionManagementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SessionManagementDTO result = sessionManagementService.save(sessionManagementDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, sessionManagementDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /session-managements} : get all the sessionManagements.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sessionManagements in body.
     */
    @GetMapping("/session-managements")
    public ResponseEntity<List<SessionManagementDTO>> getAllSessionManagements(Pageable pageable) {
        log.debug("REST request to get a page of SessionManagements");
        Page<SessionManagementDTO> page = sessionManagementService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /session-managements/:id} : get the "id" sessionManagement.
     *
     * @param id the id of the sessionManagementDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sessionManagementDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/session-managements/{id}")
    public ResponseEntity<SessionManagementDTO> getSessionManagement(@PathVariable Long id) {
        log.debug("REST request to get SessionManagement : {}", id);
        Optional<SessionManagementDTO> sessionManagementDTO = sessionManagementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sessionManagementDTO);
    }

    /**
     * {@code DELETE  /session-managements/:id} : delete the "id" sessionManagement.
     *
     * @param id the id of the sessionManagementDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/session-managements/{id}")
    public ResponseEntity<Void> deleteSessionManagement(@PathVariable Long id) {
        log.debug("REST request to delete SessionManagement : {}", id);
        sessionManagementService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
