package com.org.skillzag.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.org.skillzag.web.rest.TestUtil;

public class SubscriptionManagementDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SubscriptionManagementDTO.class);
        SubscriptionManagementDTO subscriptionManagementDTO1 = new SubscriptionManagementDTO();
        subscriptionManagementDTO1.setId(1L);
        SubscriptionManagementDTO subscriptionManagementDTO2 = new SubscriptionManagementDTO();
        assertThat(subscriptionManagementDTO1).isNotEqualTo(subscriptionManagementDTO2);
        subscriptionManagementDTO2.setId(subscriptionManagementDTO1.getId());
        assertThat(subscriptionManagementDTO1).isEqualTo(subscriptionManagementDTO2);
        subscriptionManagementDTO2.setId(2L);
        assertThat(subscriptionManagementDTO1).isNotEqualTo(subscriptionManagementDTO2);
        subscriptionManagementDTO1.setId(null);
        assertThat(subscriptionManagementDTO1).isNotEqualTo(subscriptionManagementDTO2);
    }
}
