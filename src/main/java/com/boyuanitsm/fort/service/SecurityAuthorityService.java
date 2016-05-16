package com.boyuanitsm.fort.service;

import com.boyuanitsm.fort.domain.SecurityAuthority;
import com.boyuanitsm.fort.repository.SecurityAuthorityRepository;
import com.boyuanitsm.fort.repository.search.SecurityAuthoritySearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing SecurityAuthority.
 */
@Service
@Transactional
public class SecurityAuthorityService {

    private final Logger log = LoggerFactory.getLogger(SecurityAuthorityService.class);
    
    @Inject
    private SecurityAuthorityRepository securityAuthorityRepository;
    
    @Inject
    private SecurityAuthoritySearchRepository securityAuthoritySearchRepository;
    
    /**
     * Save a securityAuthority.
     * 
     * @param securityAuthority the entity to save
     * @return the persisted entity
     */
    public SecurityAuthority save(SecurityAuthority securityAuthority) {
        log.debug("Request to save SecurityAuthority : {}", securityAuthority);
        SecurityAuthority result = securityAuthorityRepository.save(securityAuthority);
        securityAuthoritySearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the securityAuthorities.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<SecurityAuthority> findAll(Pageable pageable) {
        log.debug("Request to get all SecurityAuthorities");
        Page<SecurityAuthority> result = securityAuthorityRepository.findAll(pageable); 
        return result;
    }

    /**
     *  Get one securityAuthority by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public SecurityAuthority findOne(Long id) {
        log.debug("Request to get SecurityAuthority : {}", id);
        SecurityAuthority securityAuthority = securityAuthorityRepository.findOneWithEagerRelationships(id);
        return securityAuthority;
    }

    /**
     *  Delete the  securityAuthority by id.
     *  
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete SecurityAuthority : {}", id);
        securityAuthorityRepository.delete(id);
        securityAuthoritySearchRepository.delete(id);
    }

    /**
     * Search for the securityAuthority corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<SecurityAuthority> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of SecurityAuthorities for query {}", query);
        return securityAuthoritySearchRepository.search(queryStringQuery(query), pageable);
    }
}