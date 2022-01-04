package com.org.skillzag.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CourseActivityMapperTest {

    private CourseActivityMapper courseActivityMapper;

    @BeforeEach
    public void setUp() {
        courseActivityMapper = new CourseActivityMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(courseActivityMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(courseActivityMapper.fromId(null)).isNull();
    }
}
