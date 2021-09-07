package com.org.skillzag.web.rest;

import com.org.skillzag.service.UserCourseCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing {@link com.org.skillzag}.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserSubscriptionCartResource {

    private final Logger log = LoggerFactory.getLogger(UserSubscriptionCartResource.class);

    private final UserCourseCartService userCourseCartService;

    public UserSubscriptionCartResource(UserCourseCartService userCourseCartService) {
        this.userCourseCartService = userCourseCartService;
    }

    /**
     * {@code GET  /course-session/all} : get the "id" questions.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the questionsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cart-by-userid/{userID}")
    public ResponseEntity<?> getCartDetailsByUserID(@PathVariable String userID) {
        log.debug("REST request to get getCartDetailsByUserID");
        return ResponseEntity.ok().body(userCourseCartService.findCartDetailsByUserID(userID));
    }

    /**
     * {@code GET  /course-session-bycourse-name/{courseName}} : get the "id" questions.
     *
     * @param userID the id of the questionsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the questionsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/subscription-by-userid/{userID}")
    public ResponseEntity<?> getSubscriptionByUserID(@PathVariable String userID) {
        log.debug("REST request to get getSubscriptionByUserID : {}", userID);
        return ResponseEntity.ok().body(userCourseCartService.findSubscriptionDetailsByUserID(userID));
    }

}
