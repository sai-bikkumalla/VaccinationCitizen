package com.codedecode.microservices.CitizenService.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.codedecode.microservices.CitizenService.Entity.Citizen;
import com.codedecode.microservices.CitizenService.Exception.NameNotGivenException;
import com.codedecode.microservices.CitizenService.Repository.CitizenRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CitizenServiceTest {

	@Mock
	private CitizenRepository citizenRepository;

	@InjectMocks
	private CitizenService citizenService;

	@Test
	void positiveTestFindAllCitizens() {
		List<Citizen> expectedCitizensList = new ArrayList<>();

		Citizen citizen1 = new Citizen();
		citizen1.setId(1);
		citizen1.setName("charan");
		citizen1.setVaccinationCenterId(6084);

		Citizen citizen2 = new Citizen();
		citizen2.setId(2);
		citizen2.setName("nikhil");
		citizen2.setVaccinationCenterId(6084);

		expectedCitizensList.add(citizen1);
		expectedCitizensList.add(citizen2);

		Mockito.when(citizenRepository.findByVaccinationCenterId(6084)).thenReturn(expectedCitizensList);

		List<Citizen> actualCitizensList = citizenService.findAllCitizens(6084);

		assertEquals(expectedCitizensList, actualCitizensList);
	}

	@Test
	void negativeTestFindAllCitizens() {
		List<Citizen> expectedList = new ArrayList<>();

		Mockito.when(citizenRepository.findByVaccinationCenterId(0)).thenReturn(expectedList);
		List<Citizen> actualList = citizenService.findAllCitizens(0);
		assertEquals(expectedList, actualList);
	}

	@Test
	void positiveTestAddACitizen() throws NameNotGivenException ,JsonProcessingException {

		Citizen expected = new Citizen();
		expected.setId(1);
		expected.setName("charan");
		expected.setVaccinationCenterId(6084);

		Mockito.when(citizenRepository.save(expected)).thenReturn(expected);
		Citizen actual = citizenService.addACitizen(expected);
		assertEquals(expected, actual);
	}

	@Test
	void negativeTestAddACitizen() throws NameNotGivenException, JsonProcessingException {
		Citizen expected = new Citizen();
		expected.setId(1);
		expected.setVaccinationCenterId(6084);
		assertThrows(NameNotGivenException.class, () -> {
			citizenService.addACitizen(expected);
		});
	}

}
