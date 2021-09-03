package com.org.skillzag.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SessionManagementMapperTest {

    private SessionManagementMapper sessionManagementMapper;

    @BeforeEach
    public void setUp() {
        sessionManagementMapper = new SessionManagementMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(sessionManagementMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(sessionManagementMapper.fromId(null)).isNull();
    }
}
