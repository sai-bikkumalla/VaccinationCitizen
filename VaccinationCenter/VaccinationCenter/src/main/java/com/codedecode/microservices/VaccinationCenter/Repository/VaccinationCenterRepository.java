package com.codedecode.microservices.VaccinationCenter.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.codedecode.microservices.VaccinationCenter.Entity.VaccinationCenter;

@Repository
public interface VaccinationCenterRepository extends JpaRepository<VaccinationCenter, Integer> {
	//This is to get the vaccination center details
	@Query(value = "SELECT * FROM vaccination_center WHERE id=?1", nativeQuery = true)
	public VaccinationCenter getByCenterId(Integer id);

}
