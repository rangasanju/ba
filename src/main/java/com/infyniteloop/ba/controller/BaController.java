package com.infyniteloop.ba.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infyniteloop.ba.api.BaApi;
import com.infyniteloop.ba.exceptions.MissingHeaderException;
import com.infyniteloop.ba.exceptions.ResourceNotFoundException;
import com.infyniteloop.ba.model.BreathAnalyzer;
import com.infyniteloop.ba.model.Error;
import com.infyniteloop.ba.service.BaService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping(value = "/v1")
public class BaController {

	@Autowired
	BaService service;

	Logger log = LoggerFactory.getLogger(BaApi.class);

	public Optional<ObjectMapper> getObjectMapper() {
		return Optional.empty();
	}

	public Optional<HttpServletRequest> getRequest() {
		return Optional.empty();
	}

	public Optional<String> getAcceptHeader() {

		return getRequest().map(r -> r.getHeader("accept"));
	}

	@ApiOperation(value = "Gets all the BA records", nickname = "getBARecords", notes = "", response = BreathAnalyzer.class, responseContainer = "List", tags = {
			"breathAnalyzer", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "A paged array of pets", response = BreathAnalyzer.class, responseContainer = "List"),
			@ApiResponse(code = 200, message = "unexpected error", response = Error.class) })
	@RequestMapping(value = "/ba", method = RequestMethod.GET)
	public ResponseEntity<List<BreathAnalyzer>> getBARecords(HttpServletRequest request) throws MissingHeaderException {

		String accept = request.getHeader("accept");

		if (accept.contains("application/json")) {

			List<BreathAnalyzer> records = service.getBaRecords();
			return new ResponseEntity<>(records, HttpStatus.OK);
		} else {
			throw new MissingHeaderException("A required header is mising");
		}

	}

	@ApiOperation(value = "Gets the BA record", nickname = "getBAValue", notes = "", response = BreathAnalyzer.class, tags = {
			"breathAnalyzer", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "A paged array of pets", response = BreathAnalyzer.class),
			@ApiResponse(code = 200, message = "unexpected error", response = Error.class) })
	@RequestMapping(value = "/ba/{id}", method = RequestMethod.GET)
	public ResponseEntity<BreathAnalyzer> getBARecordById(
			@ApiParam(value = "id whose ba needs to be fetched", required = true) @PathVariable("id") Long id,
			HttpServletRequest request) throws MissingHeaderException,ResourceNotFoundException {

		String accept = request.getHeader("accept");

		if (accept.contains("application/json")) {
			Optional<BreathAnalyzer> optionalBa = service.getBa(id);
			return new ResponseEntity<>(optionalBa.get(), HttpStatus.OK);
		} else {
			throw new MissingHeaderException("A required header is mising");
		}

//		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

	@ApiOperation(value = "Gets all BA record of a lobby", nickname = "getLobbyBARecords", notes = "", response = BreathAnalyzer.class, responseContainer = "List", tags = {
			"breathAnalyzer", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "A paged array of BA records", response = BreathAnalyzer.class, responseContainer = "List"),
			@ApiResponse(code = 200, message = "unexpected error", response = Error.class) })
	@RequestMapping(value = "/ba/lobby/{lobby}", method = RequestMethod.GET)
	public ResponseEntity<List<BreathAnalyzer>> getLobbyBARecords(
			@ApiParam(value = "lobby whose ba needs to be fetched", required = true) @PathVariable("lobby") String lobby,HttpServletRequest request) throws MissingHeaderException, ResourceNotFoundException {
		String accept = request.getHeader("accept");

		if (accept.contains("application/json")) {

			List<BreathAnalyzer> records = service.findByLocation(lobby);
			if(records.isEmpty())
				throw new ResourceNotFoundException("Resource not found");
			else
				return new ResponseEntity<>(records, HttpStatus.OK);			
			
		} else {
			throw new MissingHeaderException("A required header is mising");
		}
	}

	@ApiOperation(value = "Store BA values", nickname = "storeBAValue", notes = "This can only be done by the logged in user.", tags = {
			"breathAnalyzer", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation") })
	@RequestMapping(value = "/ba/{id}", produces = { "application/json" }, method = RequestMethod.POST)
	public ResponseEntity<Void> storeBAValue(
			@ApiParam(value = "id whose ba needs to be stored", required = true) @PathVariable("id") Long id,
			@ApiParam(value = "Created user object", required = true) @Valid @RequestBody BreathAnalyzer ba) {
		if (getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {

			service.saveBA(ba);
			return new ResponseEntity<>(HttpStatus.OK);

		} else {
			log.warn(
					"ObjectMapper or HttpServletRequest not configured in default BaApi interface so no example is generated");
		}
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

	@ApiOperation(value = "Updated BA Record", nickname = "updateBA", notes = "This can be used to update only the email  ( or any other fields if required ).", tags = {
			"breathAnalyzer", })
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid user id supplied"),
			@ApiResponse(code = 404, message = "User not found") })
	@RequestMapping(value = "/ba/{id}", produces = { "application/json" }, method = RequestMethod.PUT)
	public ResponseEntity<Void> updateBA(
			@ApiParam(value = "id whose ba needs to be updated", required = true) @PathVariable("id") Long id,
			@ApiParam(value = "Updated user object", required = true) @Valid @RequestBody BreathAnalyzer body) {
		if (getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
		} else {
			log.warn(
					"ObjectMapper or HttpServletRequest not configured in default BaApi interface so no example is generated");
		}
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

	@ApiOperation(value = "Gets all BA record of a crew", nickname = "getCrewBARecords", notes = "", response = BreathAnalyzer.class, responseContainer = "List", tags = {
			"breathAnalyzer", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "A paged array of BA records", response = BreathAnalyzer.class, responseContainer = "List"),
			@ApiResponse(code = 200, message = "unexpected error", response = Error.class) })
	@RequestMapping(value = "/ba/crew/{crewid}", method = RequestMethod.GET)
	public ResponseEntity<List<BreathAnalyzer>> getCrewBARecords(
			@ApiParam(value = "CrewId whose ba needs to be fetched", required = true) @PathVariable("crewid") String crewid) {
		if (getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
			if (getAcceptHeader().get().contains("application/json")) {

				List<BreathAnalyzer> records = service.findByCrewId(crewid);
				return new ResponseEntity<>(records, HttpStatus.OK);

			}
		} else {
			log.warn(
					"ObjectMapper or HttpServletRequest not configured in default BaApi interface so no example is generated");
		}
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

}
