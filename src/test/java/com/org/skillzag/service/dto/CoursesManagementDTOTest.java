package com.org.skillzag.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.org.skillzag.web.rest.TestUtil;

public class CoursesManagementDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CoursesManagementDTO.class);
        CoursesManagementDTO coursesManagementDTO1 = new CoursesManagementDTO();
        coursesManagementDTO1.setId(1L);
        CoursesManagementDTO coursesManagementDTO2 = new CoursesManagementDTO();
        assertThat(coursesManagementDTO1).isNotEqualTo(coursesManagementDTO2);
        coursesManagementDTO2.setId(coursesManagementDTO1.getId());
        assertThat(coursesManagementDTO1).isEqualTo(coursesManagementDTO2);
        coursesManagementDTO2.setId(2L);
        assertThat(coursesManagementDTO1).isNotEqualTo(coursesManagementDTO2);
        coursesManagementDTO1.setId(null);
        assertThat(coursesManagementDTO1).isNotEqualTo(coursesManagementDTO2);
    }
}
