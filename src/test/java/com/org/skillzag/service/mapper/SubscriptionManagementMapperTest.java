package com.org.skillzag.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SubscriptionManagementMapperTest {

    private SubscriptionManagementMapper subscriptionManagementMapper;

    @BeforeEach
    public void setUp() {
        subscriptionManagementMapper = new SubscriptionManagementMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(subscriptionManagementMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(subscriptionManagementMapper.fromId(null)).isNull();
    }
}
