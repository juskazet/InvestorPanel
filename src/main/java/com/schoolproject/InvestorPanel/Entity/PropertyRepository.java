package com.schoolproject.InvestorPanel.Entity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends CrudRepository<Property, Long> {
	
	public Property findByName(String name);
	public Property findOneById(Long id);
	public Iterable<Property> findByOwner(Long id);


}
