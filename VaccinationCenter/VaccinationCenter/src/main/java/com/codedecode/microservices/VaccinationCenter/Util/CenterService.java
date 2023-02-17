package com.codedecode.microservices.VaccinationCenter.Util;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.codedecode.microservices.VaccinationCenter.Model.Citizen;

@FeignClient(value = "feignDemo", url = "http://localhost:8080/citizen")
public interface CenterService {
	
	@GetMapping("/id/{id}")
	List<Citizen> getById(@PathVariable Integer id);
}
