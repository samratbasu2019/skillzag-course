package com.org.skillzag.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.org.skillzag.web.rest.TestUtil;

public class SessionManagementDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SessionManagementDTO.class);
        SessionManagementDTO sessionManagementDTO1 = new SessionManagementDTO();
        sessionManagementDTO1.setId(1L);
        SessionManagementDTO sessionManagementDTO2 = new SessionManagementDTO();
        assertThat(sessionManagementDTO1).isNotEqualTo(sessionManagementDTO2);
        sessionManagementDTO2.setId(sessionManagementDTO1.getId());
        assertThat(sessionManagementDTO1).isEqualTo(sessionManagementDTO2);
        sessionManagementDTO2.setId(2L);
        assertThat(sessionManagementDTO1).isNotEqualTo(sessionManagementDTO2);
        sessionManagementDTO1.setId(null);
        assertThat(sessionManagementDTO1).isNotEqualTo(sessionManagementDTO2);
    }
}
