package com.org.skillzag.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.org.skillzag.web.rest.TestUtil;

public class SessionManagementTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SessionManagement.class);
        SessionManagement sessionManagement1 = new SessionManagement();
        sessionManagement1.setId(1L);
        SessionManagement sessionManagement2 = new SessionManagement();
        sessionManagement2.setId(sessionManagement1.getId());
        assertThat(sessionManagement1).isEqualTo(sessionManagement2);
        sessionManagement2.setId(2L);
        assertThat(sessionManagement1).isNotEqualTo(sessionManagement2);
        sessionManagement1.setId(null);
        assertThat(sessionManagement1).isNotEqualTo(sessionManagement2);
    }
}
