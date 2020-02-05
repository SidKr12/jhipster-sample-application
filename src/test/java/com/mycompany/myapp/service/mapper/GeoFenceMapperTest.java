package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class GeoFenceMapperTest {

    private GeoFenceMapper geoFenceMapper;

    @BeforeEach
    public void setUp() {
        geoFenceMapper = new GeoFenceMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(geoFenceMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(geoFenceMapper.fromId(null)).isNull();
    }
}
