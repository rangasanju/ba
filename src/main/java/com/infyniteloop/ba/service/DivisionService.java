package com.infyniteloop.ba.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infyniteloop.ba.model.Division;
import com.infyniteloop.ba.repository.DivisionRepository;


@Service
public class DivisionService {

	
	@Autowired
	DivisionRepository repo;
	
	
	public List<Division> findAll()
	{
		
		Iterable<Division> iterable = repo.findAll();
				
		List<Division> result = StreamSupport.stream(iterable.spliterator(), false)
        .collect(Collectors.toList());
		return result;
	}
	
	public List<Division> findByZone(String zone)
	{
		
		Iterable<Division> iterable = repo.findByZone(zone);
				
		List<Division> result = StreamSupport.stream(iterable.spliterator(), false)
        .collect(Collectors.toList());
		return result;
	}
	
	
}
