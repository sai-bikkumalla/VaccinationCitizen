package com.codedecode.microservices.CitizenService.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.codedecode.microservices.CitizenService.Entity.Citizen;

@Repository
@EnableJpaRepositories
public interface CitizenRepository extends JpaRepository<Citizen, Integer> {

	//This method is to get list of all citizens with vaccination center Id.
	@Query(value = "SELECT * FROM citizen WHERE vaccination_centre_id=?1", nativeQuery = true)
	public List<Citizen> findByVaccinationCenterId(Integer Id);
}
