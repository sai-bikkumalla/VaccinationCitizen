package com.codedecode.microservices.VaccinationCenter.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.microservices.VaccinationCenter.Entity.VaccinationCenter;
import com.codedecode.microservices.VaccinationCenter.Model.RequiredResponse;
import com.codedecode.microservices.VaccinationCenter.Service.VaccinationCenterService;

@RestController
@RequestMapping("/vaccinationcenter")
public class VaccinationCenterController {

	@Autowired
	private VaccinationCenterService vaccinationCenterService;

	// This is a Post request to add a vaccination center and its details.
	@PostMapping("/add")
	public ResponseEntity<VaccinationCenter> addVaccinationCenter(@RequestBody VaccinationCenter newVaccinationCenter) {
		return new ResponseEntity<>(vaccinationCenterService.addAVaccinationCenter(newVaccinationCenter),
				HttpStatus.CREATED);
	}

	// This is a get request to get the list of all citizens allocated to a
	// vaccination center and the vaccination center details
	@GetMapping("/id/{id}")
	public ResponseEntity<RequiredResponse> getDataByCenterId(@PathVariable Integer id) {
		return new ResponseEntity<>(vaccinationCenterService.getAllDataById(id), HttpStatus.OK);
	}
}
