package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class GeoFenceDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GeoFenceDTO.class);
        GeoFenceDTO geoFenceDTO1 = new GeoFenceDTO();
        geoFenceDTO1.setId(1L);
        GeoFenceDTO geoFenceDTO2 = new GeoFenceDTO();
        assertThat(geoFenceDTO1).isNotEqualTo(geoFenceDTO2);
        geoFenceDTO2.setId(geoFenceDTO1.getId());
        assertThat(geoFenceDTO1).isEqualTo(geoFenceDTO2);
        geoFenceDTO2.setId(2L);
        assertThat(geoFenceDTO1).isNotEqualTo(geoFenceDTO2);
        geoFenceDTO1.setId(null);
        assertThat(geoFenceDTO1).isNotEqualTo(geoFenceDTO2);
    }
}
