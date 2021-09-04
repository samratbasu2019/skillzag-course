package com.org.skillzag.service.impl;

import com.org.skillzag.repository.CRUDCourseSessionRepository;
import com.org.skillzag.service.CourseSessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Service Implementation for managing {@link CourseSessionService}.
 */
@Service
@Transactional
public class CourseSessionServiceImpl implements CourseSessionService {

    private final Logger log = LoggerFactory.getLogger(CourseSessionServiceImpl.class);


    private final CRUDCourseSessionRepository crudCourseSessionRepository;

    public CourseSessionServiceImpl(CRUDCourseSessionRepository crudCourseSessionRepository) {
        this.crudCourseSessionRepository = crudCourseSessionRepository;
    }
    @Override
    public List<Map<String, Object>> findAllCourseSession() {
        return crudCourseSessionRepository.findAllCourseSession();
    }

    @Override
    public List<Map<String, Object>> findCourseSessionByCourseName(String name) {
        log.debug("REST request to findCourseSessionByCourseName : {}", name);
        return crudCourseSessionRepository.findCourseSessionByCourseName(name);
    }
}
