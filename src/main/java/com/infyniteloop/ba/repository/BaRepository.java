package com.infyniteloop.ba.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.infyniteloop.ba.model.BreathAnalyzer;

@Repository
public interface BaRepository extends CrudRepository<BreathAnalyzer, Long> {
	
	List<BreathAnalyzer> findByCrewId(String CrewId);
	List<BreathAnalyzer> findByLocation(String lobby);

}
