package com.org.skillzag.service;

import java.util.List;
import java.util.Map;

/**
 * Service Interface for managing {@link com.org.skillzag.service}.
 */
public interface UserCourseCartService {

    /**
     * Get the "id" questions.
     *
     * @param name the id of the entity.
     * @return the entity.
     */
    List<Map<String, Object>>  findCartDetailsByUserID(String name);

    /**
     * Get the "id" questions.
     *
     * @param name the id of the entity.
     * @return the entity.
     */
    List<Map<String, Object>> findSubscriptionDetailsByUserID(String name);
}
