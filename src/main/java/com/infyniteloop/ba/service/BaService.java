package com.infyniteloop.ba.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infyniteloop.ba.model.BreathAnalyzer;
import com.infyniteloop.ba.repository.BaRepository;

@Service
public class BaService {
	
	@Autowired
	BaRepository repo;
	
	
	public Optional<BreathAnalyzer> getBa(Long id)
	{
		return repo.findById(id);
	}
	
	
	public List<BreathAnalyzer> findByCrewId(String CrewId)
	{
		
		Iterable<BreathAnalyzer> iterable = repo.findByCrewId(CrewId);
				
		List<BreathAnalyzer> result = StreamSupport.stream(iterable.spliterator(), false)
        .collect(Collectors.toList());
		return result;
	}
	
	public List<BreathAnalyzer> findByLocation(String lobby)
	{
		
		Iterable<BreathAnalyzer> iterable = repo.findByLocation(lobby);
				
		List<BreathAnalyzer> result = StreamSupport.stream(iterable.spliterator(), false)
        .collect(Collectors.toList());
		return result;
	}
	
	
	public List<BreathAnalyzer> getBaRecords()
	{
		
		Iterable<BreathAnalyzer> iterable = repo.findAll();
				
		List<BreathAnalyzer> result = StreamSupport.stream(iterable.spliterator(), false)
        .collect(Collectors.toList());
		return result;
	}
	
	public BreathAnalyzer saveBA(BreathAnalyzer ba)
	{
		return repo.save(ba);
	}
	
	
	
	
	
	
	
}
