package com.org.skillzag.service.impl;

import com.org.skillzag.service.PromoService;
import com.org.skillzag.domain.Promo;
import com.org.skillzag.repository.PromoRepository;
import com.org.skillzag.service.dto.PromoDTO;
import com.org.skillzag.service.mapper.PromoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Promo}.
 */
@Service
@Transactional
public class PromoServiceImpl implements PromoService {

    private final Logger log = LoggerFactory.getLogger(PromoServiceImpl.class);

    private final PromoRepository promoRepository;

    private final PromoMapper promoMapper;

    public PromoServiceImpl(PromoRepository promoRepository, PromoMapper promoMapper) {
        this.promoRepository = promoRepository;
        this.promoMapper = promoMapper;
    }

    @Override
    public PromoDTO save(PromoDTO promoDTO) {
        log.debug("Request to save Promo : {}", promoDTO);
        Promo promo = promoMapper.toEntity(promoDTO);
        promo = promoRepository.save(promo);
        return promoMapper.toDto(promo);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PromoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Promos");
        return promoRepository.findAll(pageable)
            .map(promoMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<PromoDTO> findOne(Long id) {
        log.debug("Request to get Promo : {}", id);
        return promoRepository.findById(id)
            .map(promoMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Promo : {}", id);
        promoRepository.deleteById(id);
    }
}
