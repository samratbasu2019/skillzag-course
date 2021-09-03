package com.org.skillzag.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.org.skillzag.web.rest.TestUtil;

public class SubscriptionManagementTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SubscriptionManagement.class);
        SubscriptionManagement subscriptionManagement1 = new SubscriptionManagement();
        subscriptionManagement1.setId(1L);
        SubscriptionManagement subscriptionManagement2 = new SubscriptionManagement();
        subscriptionManagement2.setId(subscriptionManagement1.getId());
        assertThat(subscriptionManagement1).isEqualTo(subscriptionManagement2);
        subscriptionManagement2.setId(2L);
        assertThat(subscriptionManagement1).isNotEqualTo(subscriptionManagement2);
        subscriptionManagement1.setId(null);
        assertThat(subscriptionManagement1).isNotEqualTo(subscriptionManagement2);
    }
}
