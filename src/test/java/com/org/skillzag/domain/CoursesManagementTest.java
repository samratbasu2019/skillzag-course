package com.org.skillzag.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.org.skillzag.web.rest.TestUtil;

public class CoursesManagementTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CoursesManagement.class);
        CoursesManagement coursesManagement1 = new CoursesManagement();
        coursesManagement1.setId(1L);
        CoursesManagement coursesManagement2 = new CoursesManagement();
        coursesManagement2.setId(coursesManagement1.getId());
        assertThat(coursesManagement1).isEqualTo(coursesManagement2);
        coursesManagement2.setId(2L);
        assertThat(coursesManagement1).isNotEqualTo(coursesManagement2);
        coursesManagement1.setId(null);
        assertThat(coursesManagement1).isNotEqualTo(coursesManagement2);
    }
}
