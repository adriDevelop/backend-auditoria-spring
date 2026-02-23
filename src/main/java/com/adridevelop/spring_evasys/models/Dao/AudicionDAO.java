package com.adridevelop.spring_evasys.models.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adridevelop.spring_evasys.models.entities.Audicion;

@Repository
public interface AudicionDAO extends JpaRepository<Audicion, Long> {

	List<Audicion> findByActivo(Integer id);
}
