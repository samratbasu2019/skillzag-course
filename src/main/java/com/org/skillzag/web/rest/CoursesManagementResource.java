package com.org.skillzag.web.rest;

import com.org.skillzag.service.CoursesManagementService;
import com.org.skillzag.web.rest.errors.BadRequestAlertException;
import com.org.skillzag.service.dto.CoursesManagementDTO;

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
 * REST controller for managing {@link com.org.skillzag.domain.CoursesManagement}.
 */
@RestController
@RequestMapping("/api")
public class CoursesManagementResource {

    private final Logger log = LoggerFactory.getLogger(CoursesManagementResource.class);

    private static final String ENTITY_NAME = "skillzagcoursemanagementCoursesManagement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CoursesManagementService coursesManagementService;

    public CoursesManagementResource(CoursesManagementService coursesManagementService) {
        this.coursesManagementService = coursesManagementService;
    }

    /**
     * {@code POST  /courses-managements} : Create a new coursesManagement.
     *
     * @param coursesManagementDTO the coursesManagementDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new coursesManagementDTO, or with status {@code 400 (Bad Request)} if the coursesManagement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/courses-managements")
    public ResponseEntity<CoursesManagementDTO> createCoursesManagement(@RequestBody CoursesManagementDTO coursesManagementDTO) throws URISyntaxException {
        log.debug("REST request to save CoursesManagement : {}", coursesManagementDTO);
        if (coursesManagementDTO.getId() != null) {
            throw new BadRequestAlertException("A new coursesManagement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CoursesManagementDTO result = coursesManagementService.save(coursesManagementDTO);
        return ResponseEntity.created(new URI("/api/courses-managements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /courses-managements} : Updates an existing coursesManagement.
     *
     * @param coursesManagementDTO the coursesManagementDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated coursesManagementDTO,
     * or with status {@code 400 (Bad Request)} if the coursesManagementDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the coursesManagementDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/courses-managements")
    public ResponseEntity<CoursesManagementDTO> updateCoursesManagement(@RequestBody CoursesManagementDTO coursesManagementDTO) throws URISyntaxException {
        log.debug("REST request to update CoursesManagement : {}", coursesManagementDTO);
        if (coursesManagementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CoursesManagementDTO result = coursesManagementService.save(coursesManagementDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, coursesManagementDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /courses-managements} : get all the coursesManagements.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of coursesManagements in body.
     */
    @GetMapping("/courses-managements")
    public ResponseEntity<List<CoursesManagementDTO>> getAllCoursesManagements(Pageable pageable) {
        log.debug("REST request to get a page of CoursesManagements");
        Page<CoursesManagementDTO> page = coursesManagementService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /courses-managements/:id} : get the "id" coursesManagement.
     *
     * @param id the id of the coursesManagementDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the coursesManagementDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/courses-managements/{id}")
    public ResponseEntity<CoursesManagementDTO> getCoursesManagement(@PathVariable Long id) {
        log.debug("REST request to get CoursesManagement : {}", id);
        Optional<CoursesManagementDTO> coursesManagementDTO = coursesManagementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(coursesManagementDTO);
    }

    /**
     * {@code DELETE  /courses-managements/:id} : delete the "id" coursesManagement.
     *
     * @param id the id of the coursesManagementDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/courses-managements/{id}")
    public ResponseEntity<Void> deleteCoursesManagement(@PathVariable Long id) {
        log.debug("REST request to delete CoursesManagement : {}", id);
        coursesManagementService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
