package com.boyuanitsm.fort.repository;

import com.boyuanitsm.fort.domain.SecurityAuthority;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the SecurityAuthority entity.
 */
@SuppressWarnings("unused")
public interface SecurityAuthorityRepository extends JpaRepository<SecurityAuthority,Long> {

    @Query("select distinct securityAuthority from SecurityAuthority securityAuthority left join fetch securityAuthority.resources")
    List<SecurityAuthority> findAllWithEagerRelationships();

    @Query("select securityAuthority from SecurityAuthority securityAuthority left join fetch securityAuthority.resources where securityAuthority.id =:id")
    SecurityAuthority findOneWithEagerRelationships(@Param("id") Long id);

}