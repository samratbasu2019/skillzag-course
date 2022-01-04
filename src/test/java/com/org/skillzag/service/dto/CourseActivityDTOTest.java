package com.org.skillzag.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.org.skillzag.web.rest.TestUtil;

public class CourseActivityDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CourseActivityDTO.class);
        CourseActivityDTO courseActivityDTO1 = new CourseActivityDTO();
        courseActivityDTO1.setId(1L);
        CourseActivityDTO courseActivityDTO2 = new CourseActivityDTO();
        assertThat(courseActivityDTO1).isNotEqualTo(courseActivityDTO2);
        courseActivityDTO2.setId(courseActivityDTO1.getId());
        assertThat(courseActivityDTO1).isEqualTo(courseActivityDTO2);
        courseActivityDTO2.setId(2L);
        assertThat(courseActivityDTO1).isNotEqualTo(courseActivityDTO2);
        courseActivityDTO1.setId(null);
        assertThat(courseActivityDTO1).isNotEqualTo(courseActivityDTO2);
    }
}
