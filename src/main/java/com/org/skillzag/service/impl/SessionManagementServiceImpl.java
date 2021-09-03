package com.org.skillzag.service.impl;

import com.org.skillzag.service.SessionManagementService;
import com.org.skillzag.domain.SessionManagement;
import com.org.skillzag.repository.SessionManagementRepository;
import com.org.skillzag.service.dto.SessionManagementDTO;
import com.org.skillzag.service.mapper.SessionManagementMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SessionManagement}.
 */
@Service
@Transactional
public class SessionManagementServiceImpl implements SessionManagementService {

    private final Logger log = LoggerFactory.getLogger(SessionManagementServiceImpl.class);

    private final SessionManagementRepository sessionManagementRepository;

    private final SessionManagementMapper sessionManagementMapper;

    public SessionManagementServiceImpl(SessionManagementRepository sessionManagementRepository, SessionManagementMapper sessionManagementMapper) {
        this.sessionManagementRepository = sessionManagementRepository;
        this.sessionManagementMapper = sessionManagementMapper;
    }

    @Override
    public SessionManagementDTO save(SessionManagementDTO sessionManagementDTO) {
        log.debug("Request to save SessionManagement : {}", sessionManagementDTO);
        SessionManagement sessionManagement = sessionManagementMapper.toEntity(sessionManagementDTO);
        sessionManagement = sessionManagementRepository.save(sessionManagement);
        return sessionManagementMapper.toDto(sessionManagement);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SessionManagementDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SessionManagements");
        return sessionManagementRepository.findAll(pageable)
            .map(sessionManagementMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<SessionManagementDTO> findOne(Long id) {
        log.debug("Request to get SessionManagement : {}", id);
        return sessionManagementRepository.findById(id)
            .map(sessionManagementMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete SessionManagement : {}", id);
        sessionManagementRepository.deleteById(id);
    }
}
