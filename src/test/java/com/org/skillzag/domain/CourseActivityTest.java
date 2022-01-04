package com.org.skillzag.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.org.skillzag.web.rest.TestUtil;

public class CourseActivityTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CourseActivity.class);
        CourseActivity courseActivity1 = new CourseActivity();
        courseActivity1.setId(1L);
        CourseActivity courseActivity2 = new CourseActivity();
        courseActivity2.setId(courseActivity1.getId());
        assertThat(courseActivity1).isEqualTo(courseActivity2);
        courseActivity2.setId(2L);
        assertThat(courseActivity1).isNotEqualTo(courseActivity2);
        courseActivity1.setId(null);
        assertThat(courseActivity1).isNotEqualTo(courseActivity2);
    }
}
