package com.codedecode.microservices.VaccinationCenter.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codedecode.microservices.VaccinationCenter.Entity.VaccinationCenter;
import com.codedecode.microservices.VaccinationCenter.Model.RequiredResponse;
import com.codedecode.microservices.VaccinationCenter.Repository.VaccinationCenterRepository;
import com.codedecode.microservices.VaccinationCenter.Util.CenterService;

@Service
public class VaccinationCenterService {
	@Autowired
	private VaccinationCenterRepository vaccinationCenterRepository;

	@Autowired
	private CenterService centerService;

	//This method is to add a vaccination center and its details.
	public VaccinationCenter addAVaccinationCenter(VaccinationCenter newVaccinationCenter) {
		return vaccinationCenterRepository.save(newVaccinationCenter);
	}

	//This method is to get list of all citizens who are allocated to a particular vaccination center and the vaccination center details.
	public RequiredResponse getAllDataById(Integer id) {
		RequiredResponse requiredResponse = new RequiredResponse();
		VaccinationCenter vaccinationCenter = vaccinationCenterRepository.getByCenterId(id);
		requiredResponse.setVaccinationCenter(vaccinationCenter);
		requiredResponse.setCitizens(centerService.getById(id));
		return requiredResponse;
	}
}
