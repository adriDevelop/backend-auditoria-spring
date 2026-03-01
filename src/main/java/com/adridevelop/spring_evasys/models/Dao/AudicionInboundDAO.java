package com.adridevelop.spring_evasys.models.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adridevelop.spring_evasys.models.entities.AudicionInbound;

@Repository
public interface AudicionInboundDAO extends JpaRepository<AudicionInbound, Long>{

}
