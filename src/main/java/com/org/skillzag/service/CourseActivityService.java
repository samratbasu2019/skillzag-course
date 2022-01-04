package com.org.skillzag.service;

import com.org.skillzag.service.dto.CourseActivityDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.org.skillzag.domain.CourseActivity}.
 */
public interface CourseActivityService {

    /**
     * Save a courseActivity.
     *
     * @param courseActivityDTO the entity to save.
     * @return the persisted entity.
     */
    CourseActivityDTO save(CourseActivityDTO courseActivityDTO);

    /**
     * Get all the courseActivities.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CourseActivityDTO> findAll(Pageable pageable);


    /**
     * Get the "id" courseActivity.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CourseActivityDTO> findOne(Long id);

    /**
     * Delete the "id" courseActivity.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
