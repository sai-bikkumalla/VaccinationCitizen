package com.codedecode.microservices.VaccinationCenter.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.codedecode.microservices.VaccinationCenter.Entity.VaccinationCenter;
import com.codedecode.microservices.VaccinationCenter.Model.Citizen;
import com.codedecode.microservices.VaccinationCenter.Model.RequiredResponse;
import com.codedecode.microservices.VaccinationCenter.Repository.VaccinationCenterRepository;
import com.codedecode.microservices.VaccinationCenter.Util.CenterService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class VaccinationCenterServiceTest {

	@Mock
	private VaccinationCenterRepository vaccinationCenterRepository;

	@MockBean
	private CenterService centerService;

	@InjectMocks
	private VaccinationCenterService vaccinationCenterService;

	@Test
	void testAddVaccinationCenter() {
		VaccinationCenter expected = new VaccinationCenter();
		expected.setId(6084);
		expected.setCenterName("center1");
		expected.setCenterAddress("hyderabad");

		Mockito.when(vaccinationCenterRepository.save(expected)).thenReturn(expected);
		VaccinationCenter actual = vaccinationCenterService.addAVaccinationCenter(expected);
		assertEquals(expected, actual);
	}

	@Test
	void testGetAllDataById() {
		RequiredResponse expected = new RequiredResponse();

		VaccinationCenter vaccinationCenter = new VaccinationCenter();
		vaccinationCenter.setId(6084);
		vaccinationCenter.setCenterName("center1");
		vaccinationCenter.setCenterAddress("hyderabad");

		List<Citizen> citizens = new ArrayList<>();
		Citizen citizen1 = new Citizen();
		citizen1.setId(1);
		citizen1.setName("charan");
		citizen1.setVaccinationCenterId(6084);

		Citizen citizen2 = new Citizen();
		citizen2.setId(2);
		citizen2.setName("nikhil");
		citizen2.setVaccinationCenterId(6084);

		citizens.add(citizen1);
		citizens.add(citizen2);

		Mockito.when(vaccinationCenterRepository.getByCenterId(6084)).thenReturn(vaccinationCenter);
		Mockito.when(centerService.getById(6084)).thenReturn(citizens);
		expected.setCitizens(citizens);
		expected.setVaccinationCenter(vaccinationCenter);
		RequiredResponse actual = vaccinationCenterService.getAllDataById(6084);
		assertEquals(expected, actual);
	}

}
