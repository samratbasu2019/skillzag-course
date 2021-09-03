package com.org.skillzag.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CoursesManagementMapperTest {

    private CoursesManagementMapper coursesManagementMapper;

    @BeforeEach
    public void setUp() {
        coursesManagementMapper = new CoursesManagementMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(coursesManagementMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(coursesManagementMapper.fromId(null)).isNull();
    }
}
