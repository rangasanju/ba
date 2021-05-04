package com.infyniteloop.ba.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infyniteloop.ba.exceptions.ResourceNotFoundException;
import com.infyniteloop.ba.model.BreathAnalyzer;
import com.infyniteloop.ba.model.Zone;
import com.infyniteloop.ba.repository.ZoneRepository;

@Service
public class ZoneService {
	
	@Autowired
	ZoneRepository repo;
	
	
	public List<Zone> findAll() throws ResourceNotFoundException
	{
		
		Iterable<Zone> iterable = repo.findAll();
				
		List<Zone> result = StreamSupport.stream(iterable.spliterator(), false)
        .collect(Collectors.toList());
		return result;
	}

}
