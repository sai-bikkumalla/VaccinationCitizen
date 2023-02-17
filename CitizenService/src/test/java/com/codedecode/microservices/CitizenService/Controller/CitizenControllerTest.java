package com.codedecode.microservices.CitizenService.Controller;

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

import com.codedecode.microservices.CitizenService.Entity.Citizen;
import com.codedecode.microservices.CitizenService.Service.CitizenService;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@SpringBootTest
class CitizenControllerTest {

	public static final String baseUrl = "/citizen";
	@Autowired
	private MockMvc mockMvc;

	@Mock
	private CitizenService citizenService;

	@InjectMocks
	private CitizenController citizenController;

	@Test
	void testAddCitizen() throws Exception {
		String url = baseUrl + "/add";
		Citizen citizen = new Citizen();
		citizen.setId(1);
		citizen.setName("charan");
		citizen.setVaccinationCenterId(6084);
		Mockito.when(citizenService.addACitizen(citizen)).thenReturn(citizen);
		ObjectMapper mapper = new ObjectMapper();
		mockMvc.perform(MockMvcRequestBuilders.post(url).content(mapper.writeValueAsString(citizen))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

	@Test
	void testGetById() throws Exception {
		String url = baseUrl + "/id/6084";
		List<Citizen> result = new ArrayList<Citizen>();
		Citizen citizen1 = new Citizen();
		citizen1.setId(1);
		citizen1.setName("charan");
		citizen1.setVaccinationCenterId(6084);
		Citizen citizen2 = new Citizen();
		citizen2.setId(2);
		citizen2.setName("nikhil");
		citizen2.setVaccinationCenterId(6084);
		result.add(citizen1);
		result.add(citizen2);
		Integer id = 6084;
		Mockito.when(citizenService.findAllCitizens(id)).thenReturn(result);
		mockMvc.perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

}
