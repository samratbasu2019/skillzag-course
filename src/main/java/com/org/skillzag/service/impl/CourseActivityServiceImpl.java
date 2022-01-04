package com.org.skillzag.service.impl;

import com.org.skillzag.service.CourseActivityService;
import com.org.skillzag.domain.CourseActivity;
import com.org.skillzag.repository.CourseActivityRepository;
import com.org.skillzag.service.dto.CourseActivityDTO;
import com.org.skillzag.service.mapper.CourseActivityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CourseActivity}.
 */
@Service
@Transactional
public class CourseActivityServiceImpl implements CourseActivityService {

    private final Logger log = LoggerFactory.getLogger(CourseActivityServiceImpl.class);

    private final CourseActivityRepository courseActivityRepository;

    private final CourseActivityMapper courseActivityMapper;

    public CourseActivityServiceImpl(CourseActivityRepository courseActivityRepository, CourseActivityMapper courseActivityMapper) {
        this.courseActivityRepository = courseActivityRepository;
        this.courseActivityMapper = courseActivityMapper;
    }

    @Override
    public CourseActivityDTO save(CourseActivityDTO courseActivityDTO) {
        log.debug("Request to save CourseActivity : {}", courseActivityDTO);
        CourseActivity courseActivity = courseActivityMapper.toEntity(courseActivityDTO);
        courseActivity = courseActivityRepository.save(courseActivity);
        return courseActivityMapper.toDto(courseActivity);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CourseActivityDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CourseActivities");
        return courseActivityRepository.findAll(pageable)
            .map(courseActivityMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<CourseActivityDTO> findOne(Long id) {
        log.debug("Request to get CourseActivity : {}", id);
        return courseActivityRepository.findById(id)
            .map(courseActivityMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete CourseActivity : {}", id);
        courseActivityRepository.deleteById(id);
    }
}
