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
     * {@code GET  /questions/:questionSetId} : get the "id" questions.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the questionsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/course-session/all")
    public ResponseEntity<?> getAllCourseSession() {
        log.debug("REST request to get all course and session");
        List<Map<String, Object>>  questionsDTO = courseSessionService.findAllCourseSession();
        return ResponseEntity.ok().body(questionsDTO);
    }

    /**
     * {@code GET  /questions/:type} : get the "id" questions.
     *
     * @param courseName the id of the questionsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the questionsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/question-answer-bytype/{courseName}")
    public ResponseEntity<?> getQuestionAnswerType(@PathVariable String courseName) {
        log.debug("REST request to get Course Session by CourseName : {}", courseName);
        List<Map<String, Object>>  questionsDTO = courseSessionService.findCourseSessionByCourseName(courseName);
        return ResponseEntity.ok().body(questionsDTO);
    }
}