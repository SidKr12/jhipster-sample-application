package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.GeoFenceService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.GeoFenceDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.GeoFence}.
 */
@RestController
@RequestMapping("/api")
public class GeoFenceResource {

    private final Logger log = LoggerFactory.getLogger(GeoFenceResource.class);

    private static final String ENTITY_NAME = "geoFence";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GeoFenceService geoFenceService;

    public GeoFenceResource(GeoFenceService geoFenceService) {
        this.geoFenceService = geoFenceService;
    }

    /**
     * {@code POST  /geo-fences} : Create a new geoFence.
     *
     * @param geoFenceDTO the geoFenceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new geoFenceDTO, or with status {@code 400 (Bad Request)} if the geoFence has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/geo-fences")
    public ResponseEntity<GeoFenceDTO> createGeoFence(@RequestBody GeoFenceDTO geoFenceDTO) throws URISyntaxException {
        log.debug("REST request to save GeoFence : {}", geoFenceDTO);
        if (geoFenceDTO.getId() != null) {
            throw new BadRequestAlertException("A new geoFence cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GeoFenceDTO result = geoFenceService.save(geoFenceDTO);
        return ResponseEntity.created(new URI("/api/geo-fences/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /geo-fences} : Updates an existing geoFence.
     *
     * @param geoFenceDTO the geoFenceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated geoFenceDTO,
     * or with status {@code 400 (Bad Request)} if the geoFenceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the geoFenceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/geo-fences")
    public ResponseEntity<GeoFenceDTO> updateGeoFence(@RequestBody GeoFenceDTO geoFenceDTO) throws URISyntaxException {
        log.debug("REST request to update GeoFence : {}", geoFenceDTO);
        if (geoFenceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GeoFenceDTO result = geoFenceService.save(geoFenceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, geoFenceDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /geo-fences} : get all the geoFences.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of geoFences in body.
     */
    @GetMapping("/geo-fences")
    public List<GeoFenceDTO> getAllGeoFences() {
        log.debug("REST request to get all GeoFences");
        return geoFenceService.findAll();
    }

    /**
     * {@code GET  /geo-fences/:id} : get the "id" geoFence.
     *
     * @param id the id of the geoFenceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the geoFenceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/geo-fences/{id}")
    public ResponseEntity<GeoFenceDTO> getGeoFence(@PathVariable Long id) {
        log.debug("REST request to get GeoFence : {}", id);
        Optional<GeoFenceDTO> geoFenceDTO = geoFenceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(geoFenceDTO);
    }

    /**
     * {@code DELETE  /geo-fences/:id} : delete the "id" geoFence.
     *
     * @param id the id of the geoFenceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/geo-fences/{id}")
    public ResponseEntity<Void> deleteGeoFence(@PathVariable Long id) {
        log.debug("REST request to delete GeoFence : {}", id);
        geoFenceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
