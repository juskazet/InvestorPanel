package com.schoolprojects.InvestorPanel.Entity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends CrudRepository<User, Long>{

//	UserRole findByRole(String role);
}