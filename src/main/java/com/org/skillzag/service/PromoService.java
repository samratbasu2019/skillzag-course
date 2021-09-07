package com.org.skillzag.service;

import com.org.skillzag.service.dto.PromoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.org.skillzag.domain.Promo}.
 */
public interface PromoService {

    /**
     * Save a promo.
     *
     * @param promoDTO the entity to save.
     * @return the persisted entity.
     */
    PromoDTO save(PromoDTO promoDTO);

    /**
     * Get all the promos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PromoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" promo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PromoDTO> findOne(Long id);

    /**
     * Delete the "id" promo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
