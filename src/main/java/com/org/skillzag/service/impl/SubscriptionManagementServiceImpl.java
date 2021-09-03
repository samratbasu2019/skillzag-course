package com.org.skillzag.service.impl;

import com.org.skillzag.service.SubscriptionManagementService;
import com.org.skillzag.domain.SubscriptionManagement;
import com.org.skillzag.repository.SubscriptionManagementRepository;
import com.org.skillzag.service.dto.SubscriptionManagementDTO;
import com.org.skillzag.service.mapper.SubscriptionManagementMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SubscriptionManagement}.
 */
@Service
@Transactional
public class SubscriptionManagementServiceImpl implements SubscriptionManagementService {

    private final Logger log = LoggerFactory.getLogger(SubscriptionManagementServiceImpl.class);

    private final SubscriptionManagementRepository subscriptionManagementRepository;

    private final SubscriptionManagementMapper subscriptionManagementMapper;

    public SubscriptionManagementServiceImpl(SubscriptionManagementRepository subscriptionManagementRepository, SubscriptionManagementMapper subscriptionManagementMapper) {
        this.subscriptionManagementRepository = subscriptionManagementRepository;
        this.subscriptionManagementMapper = subscriptionManagementMapper;
    }

    @Override
    public SubscriptionManagementDTO save(SubscriptionManagementDTO subscriptionManagementDTO) {
        log.debug("Request to save SubscriptionManagement : {}", subscriptionManagementDTO);
        SubscriptionManagement subscriptionManagement = subscriptionManagementMapper.toEntity(subscriptionManagementDTO);
        subscriptionManagement = subscriptionManagementRepository.save(subscriptionManagement);
        return subscriptionManagementMapper.toDto(subscriptionManagement);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SubscriptionManagementDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SubscriptionManagements");
        return subscriptionManagementRepository.findAll(pageable)
            .map(subscriptionManagementMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<SubscriptionManagementDTO> findOne(Long id) {
        log.debug("Request to get SubscriptionManagement : {}", id);
        return subscriptionManagementRepository.findById(id)
            .map(subscriptionManagementMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete SubscriptionManagement : {}", id);
        subscriptionManagementRepository.deleteById(id);
    }
}
