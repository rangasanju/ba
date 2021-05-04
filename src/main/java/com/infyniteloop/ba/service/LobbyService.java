package com.infyniteloop.ba.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infyniteloop.ba.model.Division;
import com.infyniteloop.ba.model.Lobby;
import com.infyniteloop.ba.repository.LobbyRepository;


@Service
public class LobbyService {

	
	@Autowired
	LobbyRepository repo;
	
	
	public List<Lobby> findAll()
	{
		
		Iterable<Lobby> iterable = repo.findAll();
				
		List<Lobby> result = StreamSupport.stream(iterable.spliterator(), false)
        .collect(Collectors.toList());
		return result;
	}
	
	public List<Lobby> findByDivision(String division)
	{
		
		Iterable<Lobby> iterable = repo.findByDivision(division);
				
		List<Lobby> result = StreamSupport.stream(iterable.spliterator(), false)
        .collect(Collectors.toList());
		return result;
	}
	
	
}
