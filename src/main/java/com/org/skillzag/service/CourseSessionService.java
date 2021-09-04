package com.org.skillzag.service;

import java.util.List;
import java.util.Map;

/**
 * Service Interface for managing {@link com.org.skillzag.service}.
 */
public interface CourseSessionService {

    /**
     * Get the "id" questions.
     * @return the entity.
     */
    List<Map<String, Object>> findAllCourseSession();

    /**
     * Get the "id" questions.
     *
     * @param name the id of the entity.
     * @return the entity.
     */
    List<Map<String, Object>>  findCourseSessionByCourseName(String name);

    /**
     * Get the "id" questions.
     *
     * @param courseID the id of the entity.
     * @return the entity.
     */
    List<Map<String, Object>>  findCourseSessionByCourseID(Long courseID);
}
