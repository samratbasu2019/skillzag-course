package com.org.skillzag.web.rest;

import com.org.skillzag.service.CourseActivityService;
import com.org.skillzag.web.rest.errors.BadRequestAlertException;
import com.org.skillzag.service.dto.CourseActivityDTO;

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
 * REST controller for managing {@link com.org.skillzag.domain.CourseActivity}.
 */
@RestController
@RequestMapping("/api")
public class CourseActivityResource {

    private final Logger log = LoggerFactory.getLogger(CourseActivityResource.class);

    private static final String ENTITY_NAME = "skillzagcoursemanagementCourseActivity";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CourseActivityService courseActivityService;

    public CourseActivityResource(CourseActivityService courseActivityService) {
        this.courseActivityService = courseActivityService;
    }

    /**
     * {@code POST  /course-activities} : Create a new courseActivity.
     *
     * @param courseActivityDTO the courseActivityDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new courseActivityDTO, or with status {@code 400 (Bad Request)} if the courseActivity has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/course-activities")
    public ResponseEntity<CourseActivityDTO> createCourseActivity(@RequestBody CourseActivityDTO courseActivityDTO) throws URISyntaxException {
        log.debug("REST request to save CourseActivity : {}", courseActivityDTO);
        if (courseActivityDTO.getId() != null) {
            throw new BadRequestAlertException("A new courseActivity cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CourseActivityDTO result = courseActivityService.save(courseActivityDTO);
        return ResponseEntity.created(new URI("/api/course-activities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /course-activities} : Updates an existing courseActivity.
     *
     * @param courseActivityDTO the courseActivityDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated courseActivityDTO,
     * or with status {@code 400 (Bad Request)} if the courseActivityDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the courseActivityDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/course-activities")
    public ResponseEntity<CourseActivityDTO> updateCourseActivity(@RequestBody CourseActivityDTO courseActivityDTO) throws URISyntaxException {
        log.debug("REST request to update CourseActivity : {}", courseActivityDTO);
        if (courseActivityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CourseActivityDTO result = courseActivityService.save(courseActivityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, courseActivityDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /course-activities} : get all the courseActivities.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of courseActivities in body.
     */
    @GetMapping("/course-activities")
    public ResponseEntity<List<CourseActivityDTO>> getAllCourseActivities(Pageable pageable) {
        log.debug("REST request to get a page of CourseActivities");
        Page<CourseActivityDTO> page = courseActivityService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /course-activities/:id} : get the "id" courseActivity.
     *
     * @param id the id of the courseActivityDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the courseActivityDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/course-activities/{id}")
    public ResponseEntity<CourseActivityDTO> getCourseActivity(@PathVariable Long id) {
        log.debug("REST request to get CourseActivity : {}", id);
        Optional<CourseActivityDTO> courseActivityDTO = courseActivityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(courseActivityDTO);
    }

    /**
     * {@code DELETE  /course-activities/:id} : delete the "id" courseActivity.
     *
     * @param id the id of the courseActivityDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/course-activities/{id}")
    public ResponseEntity<Void> deleteCourseActivity(@PathVariable Long id) {
        log.debug("REST request to delete CourseActivity : {}", id);
        courseActivityService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
