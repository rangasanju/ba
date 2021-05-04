package com.infyniteloop.ba.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.infyniteloop.ba.api.LobbyApi;
import com.infyniteloop.ba.model.Division;
import com.infyniteloop.ba.model.Lobby;
import com.infyniteloop.ba.service.LobbyService;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
public class LobbyController implements LobbyApi{

	@Autowired
	LobbyService service;
	
	
	@Override
	public ResponseEntity<List<Lobby>> getLobbies(String division) {
		List<Lobby> records = service.findByDivision(division);		
		return new ResponseEntity<>(records, HttpStatus.OK);
	}

}
