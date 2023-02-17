package com.codedecode.microservices.CitizenService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.microservices.CitizenService.Entity.Citizen;
import com.codedecode.microservices.CitizenService.Exception.NameNotGivenException;
import com.codedecode.microservices.CitizenService.Service.CitizenService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping(path = "/citizen")
public class CitizenController {

	@Autowired
	private CitizenService citizenService;

	// It gives all the detailed information of citizens with given vaccination
	// center id so that who are allocated to that vaccination center.
	@GetMapping("/id/{id}")
	public ResponseEntity<List<Citizen>> getById(@PathVariable("id") Integer id) {
		return new ResponseEntity<>(citizenService.findAllCitizens(id), HttpStatus.OK);
	}

	//This is a post request to register a citizen
	@PostMapping("/add")
	public ResponseEntity<Citizen> addCitizen(@RequestBody Citizen newCitizen)
			throws NameNotGivenException, JsonProcessingException {
		return new ResponseEntity<>(citizenService.addACitizen(newCitizen), HttpStatus.CREATED);
	}
}
