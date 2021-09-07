package com.org.skillzag.repository;

import com.org.skillzag.domain.Promo;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Promo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PromoRepository extends JpaRepository<Promo, Long> {
}
