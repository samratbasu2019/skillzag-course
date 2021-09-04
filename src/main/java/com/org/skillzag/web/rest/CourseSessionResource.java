package com.org.skillzag.web.rest;

import com.org.skillzag.service.CourseSessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST controller for managing {@link com.org.skillzag}.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CourseSessionResource {

    private final Logger log = LoggerFactory.getLogger(CourseSessionResource.class);

    private final CourseSessionService courseSessionService;

    public CourseSessionResource(CourseSessionService courseSessionService) {
        this.courseSessionService = courseSessionService;
    }

    /**
     * {@code GET  /course-session/all} : get the "id" questions.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the questionsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/course-session/all")
    public ResponseEntity<?> getAllCourseSession() {
        log.debug("REST request to get all course and session");
        List<Map<String, Object>>  questionsDTO = courseSessionService.findAllCourseSession();
        return ResponseEntity.ok().body(questionsDTO);
    }

    /**
     * {@code GET  /course-session-bycourse-name/{courseName}} : get the "id" questions.
     *
     * @param courseName the id of the questionsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the questionsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/course-session-bycourse-name/{courseName}")
    public ResponseEntity<?> getCourseSessionByCourseName(@PathVariable String courseName) {
        log.debug("REST request to get Course Session by CourseName : {}", courseName);
        List<Map<String, Object>>  questionsDTO = courseSessionService.findCourseSessionByCourseName(courseName);
        return ResponseEntity.ok().body(questionsDTO);
    }

    /**
     * {@code GET  /course-session-bycourse-name/{courseID}} : get the "id" questions.
     *
     * @param courseID the id of the questionsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the questionsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/course-session-bycourse-id/{courseID}")
    public ResponseEntity<?> getCourseSessionByCourseID(@PathVariable Long courseID) {
        log.debug("REST request to get Course Session by CourseID : {}", courseID);
        List<Map<String, Object>>  questionsDTO = courseSessionService.findCourseSessionByCourseID(courseID);
        return ResponseEntity.ok().body(questionsDTO);
    }
}
