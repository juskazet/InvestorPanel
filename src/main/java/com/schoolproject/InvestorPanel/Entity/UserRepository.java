package com.schoolproject.InvestorPanel.Entity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	public User findByEmailAddress(String emailAddress);
	public User findOneById(Long id);
	public boolean existsByEmailAddress(String emailAddress);
	
}
