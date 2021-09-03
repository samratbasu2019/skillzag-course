package com.org.skillzag.service;

import com.org.skillzag.service.dto.CoursesManagementDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.org.skillzag.domain.CoursesManagement}.
 */
public interface CoursesManagementService {

    /**
     * Save a coursesManagement.
     *
     * @param coursesManagementDTO the entity to save.
     * @return the persisted entity.
     */
    CoursesManagementDTO save(CoursesManagementDTO coursesManagementDTO);

    /**
     * Get all the coursesManagements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CoursesManagementDTO> findAll(Pageable pageable);


    /**
     * Get the "id" coursesManagement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CoursesManagementDTO> findOne(Long id);

    /**
     * Delete the "id" coursesManagement.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
