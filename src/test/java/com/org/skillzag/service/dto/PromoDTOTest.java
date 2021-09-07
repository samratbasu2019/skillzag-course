package com.org.skillzag.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.org.skillzag.web.rest.TestUtil;

public class PromoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PromoDTO.class);
        PromoDTO promoDTO1 = new PromoDTO();
        promoDTO1.setId(1L);
        PromoDTO promoDTO2 = new PromoDTO();
        assertThat(promoDTO1).isNotEqualTo(promoDTO2);
        promoDTO2.setId(promoDTO1.getId());
        assertThat(promoDTO1).isEqualTo(promoDTO2);
        promoDTO2.setId(2L);
        assertThat(promoDTO1).isNotEqualTo(promoDTO2);
        promoDTO1.setId(null);
        assertThat(promoDTO1).isNotEqualTo(promoDTO2);
    }
}
