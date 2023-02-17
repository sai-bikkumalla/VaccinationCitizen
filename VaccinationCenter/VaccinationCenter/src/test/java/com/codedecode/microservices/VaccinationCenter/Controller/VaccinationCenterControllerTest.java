package com.codedecode.microservices.VaccinationCenter.Controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.codedecode.microservices.VaccinationCenter.Entity.VaccinationCenter;
import com.codedecode.microservices.VaccinationCenter.Model.Citizen;
import com.codedecode.microservices.VaccinationCenter.Model.RequiredResponse;
import com.codedecode.microservices.VaccinationCenter.Service.VaccinationCenterService;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@SpringBootTest
class VaccinationCenterControllerTest {

	public static String baseUrl = "/vaccinationcenter";

	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	private VaccinationCenterController vaccinationCenterController;

	@Mock
	private VaccinationCenterService vaccinationCenterService;

	@Test
	void testAddVaccinationCenter() throws Exception {
		String url = baseUrl + "/add";
		VaccinationCenter vaccinationCenter = new VaccinationCenter();
		vaccinationCenter.setId(6084);
		vaccinationCenter.setCenterName("center1");
		vaccinationCenter.setCenterAddress("hyd");
		Mockito.when(vaccinationCenterService.addAVaccinationCenter(vaccinationCenter)).thenReturn(vaccinationCenter);
		ObjectMapper mapper = new ObjectMapper();
		 mockMvc.perform(MockMvcRequestBuilders.post(url).content(mapper.writeValueAsString(vaccinationCenter))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
	}

	@Test
	void testGetDataByCenterId() throws Exception {
		String url = baseUrl + "/id/6084";
		RequiredResponse requiredResponse = new RequiredResponse();
		VaccinationCenter vaccinationCenter = new VaccinationCenter();
		vaccinationCenter.setId(6084);
		vaccinationCenter.setCenterName("center1");
		vaccinationCenter.setCenterAddress("hyd");
		Citizen citizen1 = new Citizen();
		citizen1.setId(1);
		citizen1.setName("charan");
		citizen1.setVaccinationCenterId(6084);
		Citizen citizen2 = new Citizen();
		citizen2.setId(2);
		citizen2.setName("nikhil");
		citizen2.setVaccinationCenterId(6084);
		Citizen citizen3 = new Citizen();
		citizen3.setId(3);
		citizen3.setName("vardhan");
		citizen3.setVaccinationCenterId(6084);
		List<Citizen> citizens = new ArrayList<>();
		citizens.add(citizen1);
		citizens.add(citizen2);
		citizens.add(citizen3);
		requiredResponse.setCitizens(citizens);
		requiredResponse.setVaccinationCenter(vaccinationCenter);
		Integer id = 6084;
		Mockito.when(vaccinationCenterService.getAllDataById(id)).thenReturn(requiredResponse);
		mockMvc.perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
}
