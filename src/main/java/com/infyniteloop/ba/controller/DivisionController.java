package com.infyniteloop.ba.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.infyniteloop.ba.api.DivisionApi;
import com.infyniteloop.ba.api.ZoneApi;
import com.infyniteloop.ba.model.Division;
import com.infyniteloop.ba.service.DivisionService;



@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
public class DivisionController implements DivisionApi {
	
	@Autowired
	DivisionService service;

	@Override
	public ResponseEntity<List<Division>> getAllDivisions() {
		List<Division> records = service.findAll();		
		return new ResponseEntity<>(records, HttpStatus.OK);
		
	}

	@Override
	public ResponseEntity<List<Division>> getDivisions(String zone) {
		List<Division> records = service.findByZone(zone);		
		return new ResponseEntity<>(records, HttpStatus.OK);
	}

	




}
