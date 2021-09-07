package com.org.skillzag.service.mapper;


import com.org.skillzag.domain.*;
import com.org.skillzag.service.dto.PromoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Promo} and its DTO {@link PromoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PromoMapper extends EntityMapper<PromoDTO, Promo> {



    default Promo fromId(Long id) {
        if (id == null) {
            return null;
        }
        Promo promo = new Promo();
        promo.setId(id);
        return promo;
    }
}
