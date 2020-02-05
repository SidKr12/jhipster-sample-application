package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.GeoFenceService;
import com.mycompany.myapp.domain.GeoFence;
import com.mycompany.myapp.repository.GeoFenceRepository;
import com.mycompany.myapp.service.dto.GeoFenceDTO;
import com.mycompany.myapp.service.mapper.GeoFenceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link GeoFence}.
 */
@Service
@Transactional
public class GeoFenceServiceImpl implements GeoFenceService {

    private final Logger log = LoggerFactory.getLogger(GeoFenceServiceImpl.class);

    private final GeoFenceRepository geoFenceRepository;

    private final GeoFenceMapper geoFenceMapper;

    public GeoFenceServiceImpl(GeoFenceRepository geoFenceRepository, GeoFenceMapper geoFenceMapper) {
        this.geoFenceRepository = geoFenceRepository;
        this.geoFenceMapper = geoFenceMapper;
    }

    /**
     * Save a geoFence.
     *
     * @param geoFenceDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public GeoFenceDTO save(GeoFenceDTO geoFenceDTO) {
        log.debug("Request to save GeoFence : {}", geoFenceDTO);
        GeoFence geoFence = geoFenceMapper.toEntity(geoFenceDTO);
        geoFence = geoFenceRepository.save(geoFence);
        return geoFenceMapper.toDto(geoFence);
    }

    /**
     * Get all the geoFences.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<GeoFenceDTO> findAll() {
        log.debug("Request to get all GeoFences");
        return geoFenceRepository.findAll().stream()
            .map(geoFenceMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one geoFence by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<GeoFenceDTO> findOne(Long id) {
        log.debug("Request to get GeoFence : {}", id);
        return geoFenceRepository.findById(id)
            .map(geoFenceMapper::toDto);
    }

    /**
     * Delete the geoFence by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete GeoFence : {}", id);
        geoFenceRepository.deleteById(id);
    }
}
