package com.org.skillzag.service.impl;

import com.org.skillzag.repository.CRUDCartUserRepository;
import com.org.skillzag.service.CourseSessionService;
import com.org.skillzag.service.UserCourseCartService;
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
public class UserCourseCartServiceImpl implements UserCourseCartService {

    private final Logger log = LoggerFactory.getLogger(UserCourseCartServiceImpl.class);


    private final CRUDCartUserRepository crudCourseSessionRepository;

    public UserCourseCartServiceImpl(CRUDCartUserRepository crudCourseSessionRepository) {
        this.crudCourseSessionRepository = crudCourseSessionRepository;
    }

    @Override
    public List<Map<String, Object>> findCartDetailsByUserID(String name) {
        log.debug("event=findCartDetailsByUserID userID {}", name);
        return crudCourseSessionRepository.findCartDetailsByUserID(name);
    }

    @Override
    public List<Map<String, Object>> findSubscriptionDetailsByUserID(String name) {
        log.debug("event=findPaidCourseDetailsByUserID userID {}", name);
        return crudCourseSessionRepository.findSubscriptionDetailsByUserID(name);
    }
}
