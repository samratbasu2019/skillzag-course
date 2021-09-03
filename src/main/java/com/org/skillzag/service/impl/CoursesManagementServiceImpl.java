package com.org.skillzag.service.impl;

import com.org.skillzag.service.CoursesManagementService;
import com.org.skillzag.domain.CoursesManagement;
import com.org.skillzag.repository.CoursesManagementRepository;
import com.org.skillzag.service.dto.CoursesManagementDTO;
import com.org.skillzag.service.mapper.CoursesManagementMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CoursesManagement}.
 */
@Service
@Transactional
public class CoursesManagementServiceImpl implements CoursesManagementService {

    private final Logger log = LoggerFactory.getLogger(CoursesManagementServiceImpl.class);

    private final CoursesManagementRepository coursesManagementRepository;

    private final CoursesManagementMapper coursesManagementMapper;

    public CoursesManagementServiceImpl(CoursesManagementRepository coursesManagementRepository, CoursesManagementMapper coursesManagementMapper) {
        this.coursesManagementRepository = coursesManagementRepository;
        this.coursesManagementMapper = coursesManagementMapper;
    }

    @Override
    public CoursesManagementDTO save(CoursesManagementDTO coursesManagementDTO) {
        log.debug("Request to save CoursesManagement : {}", coursesManagementDTO);
        CoursesManagement coursesManagement = coursesManagementMapper.toEntity(coursesManagementDTO);
        coursesManagement = coursesManagementRepository.save(coursesManagement);
        return coursesManagementMapper.toDto(coursesManagement);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CoursesManagementDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CoursesManagements");
        return coursesManagementRepository.findAll(pageable)
            .map(coursesManagementMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<CoursesManagementDTO> findOne(Long id) {
        log.debug("Request to get CoursesManagement : {}", id);
        return coursesManagementRepository.findById(id)
            .map(coursesManagementMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete CoursesManagement : {}", id);
        coursesManagementRepository.deleteById(id);
    }
}
