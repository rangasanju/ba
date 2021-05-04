package com.infyniteloop.ba.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.infyniteloop.ba.api.ZoneApi;
import com.infyniteloop.ba.exceptions.ResourceNotFoundException;
import com.infyniteloop.ba.model.Zone;
import com.infyniteloop.ba.service.ZoneService;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
public class ZoneController implements ZoneApi {
	
	@Autowired
	ZoneService service;

	@Override
	public ResponseEntity<List<Zone>> getZones() {
		
		List<Zone> records=null;
		try {
			records = service.findAll();
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		
		return new ResponseEntity<>(records, HttpStatus.OK);
		
		
	}

	

}
