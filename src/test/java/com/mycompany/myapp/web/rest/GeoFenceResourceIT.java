package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterSampleApplicationApp;
import com.mycompany.myapp.domain.GeoFence;
import com.mycompany.myapp.repository.GeoFenceRepository;
import com.mycompany.myapp.service.GeoFenceService;
import com.mycompany.myapp.service.dto.GeoFenceDTO;
import com.mycompany.myapp.service.mapper.GeoFenceMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.sameInstant;
import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link GeoFenceResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class GeoFenceResourceIT {

    private static final Long DEFAULT_FENCE_ID = 1L;
    private static final Long UPDATED_FENCE_ID = 2L;

    private static final String DEFAULT_FENCE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FENCE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FENCE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_FENCE_CODE = "BBBBBBBBBB";

    private static final Long DEFAULT_TYPE = 1L;
    private static final Long UPDATED_TYPE = 2L;

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATED_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_MODIFIED_BY = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_MODIFIED_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_MODIFIED_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private GeoFenceRepository geoFenceRepository;

    @Autowired
    private GeoFenceMapper geoFenceMapper;

    @Autowired
    private GeoFenceService geoFenceService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restGeoFenceMockMvc;

    private GeoFence geoFence;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GeoFenceResource geoFenceResource = new GeoFenceResource(geoFenceService);
        this.restGeoFenceMockMvc = MockMvcBuilders.standaloneSetup(geoFenceResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeoFence createEntity(EntityManager em) {
        GeoFence geoFence = new GeoFence()
            .fenceID(DEFAULT_FENCE_ID)
            .fenceName(DEFAULT_FENCE_NAME)
            .fenceCode(DEFAULT_FENCE_CODE)
            .type(DEFAULT_TYPE)
            .createdBy(DEFAULT_CREATED_BY)
            .createdTime(DEFAULT_CREATED_TIME)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .modifiedTime(DEFAULT_MODIFIED_TIME);
        return geoFence;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GeoFence createUpdatedEntity(EntityManager em) {
        GeoFence geoFence = new GeoFence()
            .fenceID(UPDATED_FENCE_ID)
            .fenceName(UPDATED_FENCE_NAME)
            .fenceCode(UPDATED_FENCE_CODE)
            .type(UPDATED_TYPE)
            .createdBy(UPDATED_CREATED_BY)
            .createdTime(UPDATED_CREATED_TIME)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .modifiedTime(UPDATED_MODIFIED_TIME);
        return geoFence;
    }

    @BeforeEach
    public void initTest() {
        geoFence = createEntity(em);
    }

    @Test
    @Transactional
    public void createGeoFence() throws Exception {
        int databaseSizeBeforeCreate = geoFenceRepository.findAll().size();

        // Create the GeoFence
        GeoFenceDTO geoFenceDTO = geoFenceMapper.toDto(geoFence);
        restGeoFenceMockMvc.perform(post("/api/geo-fences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(geoFenceDTO)))
            .andExpect(status().isCreated());

        // Validate the GeoFence in the database
        List<GeoFence> geoFenceList = geoFenceRepository.findAll();
        assertThat(geoFenceList).hasSize(databaseSizeBeforeCreate + 1);
        GeoFence testGeoFence = geoFenceList.get(geoFenceList.size() - 1);
        assertThat(testGeoFence.getFenceID()).isEqualTo(DEFAULT_FENCE_ID);
        assertThat(testGeoFence.getFenceName()).isEqualTo(DEFAULT_FENCE_NAME);
        assertThat(testGeoFence.getFenceCode()).isEqualTo(DEFAULT_FENCE_CODE);
        assertThat(testGeoFence.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testGeoFence.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testGeoFence.getCreatedTime()).isEqualTo(DEFAULT_CREATED_TIME);
        assertThat(testGeoFence.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testGeoFence.getModifiedTime()).isEqualTo(DEFAULT_MODIFIED_TIME);
    }

    @Test
    @Transactional
    public void createGeoFenceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = geoFenceRepository.findAll().size();

        // Create the GeoFence with an existing ID
        geoFence.setId(1L);
        GeoFenceDTO geoFenceDTO = geoFenceMapper.toDto(geoFence);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGeoFenceMockMvc.perform(post("/api/geo-fences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(geoFenceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GeoFence in the database
        List<GeoFence> geoFenceList = geoFenceRepository.findAll();
        assertThat(geoFenceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllGeoFences() throws Exception {
        // Initialize the database
        geoFenceRepository.saveAndFlush(geoFence);

        // Get all the geoFenceList
        restGeoFenceMockMvc.perform(get("/api/geo-fences?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(geoFence.getId().intValue())))
            .andExpect(jsonPath("$.[*].fenceID").value(hasItem(DEFAULT_FENCE_ID.intValue())))
            .andExpect(jsonPath("$.[*].fenceName").value(hasItem(DEFAULT_FENCE_NAME)))
            .andExpect(jsonPath("$.[*].fenceCode").value(hasItem(DEFAULT_FENCE_CODE)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.intValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdTime").value(hasItem(sameInstant(DEFAULT_CREATED_TIME))))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].modifiedTime").value(hasItem(sameInstant(DEFAULT_MODIFIED_TIME))));
    }
    
    @Test
    @Transactional
    public void getGeoFence() throws Exception {
        // Initialize the database
        geoFenceRepository.saveAndFlush(geoFence);

        // Get the geoFence
        restGeoFenceMockMvc.perform(get("/api/geo-fences/{id}", geoFence.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(geoFence.getId().intValue()))
            .andExpect(jsonPath("$.fenceID").value(DEFAULT_FENCE_ID.intValue()))
            .andExpect(jsonPath("$.fenceName").value(DEFAULT_FENCE_NAME))
            .andExpect(jsonPath("$.fenceCode").value(DEFAULT_FENCE_CODE))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.intValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdTime").value(sameInstant(DEFAULT_CREATED_TIME)))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY))
            .andExpect(jsonPath("$.modifiedTime").value(sameInstant(DEFAULT_MODIFIED_TIME)));
    }

    @Test
    @Transactional
    public void getNonExistingGeoFence() throws Exception {
        // Get the geoFence
        restGeoFenceMockMvc.perform(get("/api/geo-fences/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGeoFence() throws Exception {
        // Initialize the database
        geoFenceRepository.saveAndFlush(geoFence);

        int databaseSizeBeforeUpdate = geoFenceRepository.findAll().size();

        // Update the geoFence
        GeoFence updatedGeoFence = geoFenceRepository.findById(geoFence.getId()).get();
        // Disconnect from session so that the updates on updatedGeoFence are not directly saved in db
        em.detach(updatedGeoFence);
        updatedGeoFence
            .fenceID(UPDATED_FENCE_ID)
            .fenceName(UPDATED_FENCE_NAME)
            .fenceCode(UPDATED_FENCE_CODE)
            .type(UPDATED_TYPE)
            .createdBy(UPDATED_CREATED_BY)
            .createdTime(UPDATED_CREATED_TIME)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .modifiedTime(UPDATED_MODIFIED_TIME);
        GeoFenceDTO geoFenceDTO = geoFenceMapper.toDto(updatedGeoFence);

        restGeoFenceMockMvc.perform(put("/api/geo-fences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(geoFenceDTO)))
            .andExpect(status().isOk());

        // Validate the GeoFence in the database
        List<GeoFence> geoFenceList = geoFenceRepository.findAll();
        assertThat(geoFenceList).hasSize(databaseSizeBeforeUpdate);
        GeoFence testGeoFence = geoFenceList.get(geoFenceList.size() - 1);
        assertThat(testGeoFence.getFenceID()).isEqualTo(UPDATED_FENCE_ID);
        assertThat(testGeoFence.getFenceName()).isEqualTo(UPDATED_FENCE_NAME);
        assertThat(testGeoFence.getFenceCode()).isEqualTo(UPDATED_FENCE_CODE);
        assertThat(testGeoFence.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testGeoFence.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testGeoFence.getCreatedTime()).isEqualTo(UPDATED_CREATED_TIME);
        assertThat(testGeoFence.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testGeoFence.getModifiedTime()).isEqualTo(UPDATED_MODIFIED_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingGeoFence() throws Exception {
        int databaseSizeBeforeUpdate = geoFenceRepository.findAll().size();

        // Create the GeoFence
        GeoFenceDTO geoFenceDTO = geoFenceMapper.toDto(geoFence);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGeoFenceMockMvc.perform(put("/api/geo-fences")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(geoFenceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GeoFence in the database
        List<GeoFence> geoFenceList = geoFenceRepository.findAll();
        assertThat(geoFenceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGeoFence() throws Exception {
        // Initialize the database
        geoFenceRepository.saveAndFlush(geoFence);

        int databaseSizeBeforeDelete = geoFenceRepository.findAll().size();

        // Delete the geoFence
        restGeoFenceMockMvc.perform(delete("/api/geo-fences/{id}", geoFence.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GeoFence> geoFenceList = geoFenceRepository.findAll();
        assertThat(geoFenceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
