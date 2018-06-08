package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.User;

public interface UserRepository extends JpaRepository<User,Long>{

	public User findOneByUsername(String username);
	public User findOneByUsernameAndPassword(String username, char[] password);
	
}
