package com.org.skillzag.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PromoMapperTest {

    private PromoMapper promoMapper;

    @BeforeEach
    public void setUp() {
        promoMapper = new PromoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(promoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(promoMapper.fromId(null)).isNull();
    }
}
