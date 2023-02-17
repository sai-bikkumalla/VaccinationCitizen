package com.codedecode.microservices.CitizenService.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codedecode.microservices.CitizenService.Entity.Citizen;
import com.codedecode.microservices.CitizenService.Exception.NameNotGivenException;
import com.codedecode.microservices.CitizenService.Repository.CitizenRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class CitizenService {

	@Autowired
	private CitizenRepository citizenRepository;

	//This method is to get the list of all citizens with given vaccination
	// center id so that who are allocated to that vaccination center.
	public List<Citizen> findAllCitizens(Integer Id) {
		return citizenRepository.findByVaccinationCenterId(Id);
	}

	//This method is to register a citizen
	public Citizen addACitizen(Citizen newCitizen) throws NameNotGivenException, JsonProcessingException {
		if (newCitizen.getName()!= null) {
			return citizenRepository.save(newCitizen);
		}else {
		throw new NameNotGivenException("Name is not found");}
	}
}
