package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.GeoFenceDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.GeoFence}.
 */
public interface GeoFenceService {

    /**
     * Save a geoFence.
     *
     * @param geoFenceDTO the entity to save.
     * @return the persisted entity.
     */
    GeoFenceDTO save(GeoFenceDTO geoFenceDTO);

    /**
     * Get all the geoFences.
     *
     * @return the list of entities.
     */
    List<GeoFenceDTO> findAll();


    /**
     * Get the "id" geoFence.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<GeoFenceDTO> findOne(Long id);

    /**
     * Delete the "id" geoFence.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
