package com.infyniteloop.ba.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.infyniteloop.ba.model.Division;

@Repository
public interface DivisionRepository extends CrudRepository<Division, Long> {
	
	List<Division> findByZone(String zone);

}
