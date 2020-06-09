package com.schoolproject.InvestorPanel.Entity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends CrudRepository<Property, Long> {
	
	Property findByName(String name);
	Property findOneById(Long id);
	Iterable<Property> findByOwner(Long id);

}
