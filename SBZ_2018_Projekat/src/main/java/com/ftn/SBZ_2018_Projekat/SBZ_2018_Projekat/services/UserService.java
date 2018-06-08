package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.services;

import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.User;

public interface UserService {

	public User findByUsername(String username);
	public User findByUsernameAndPassword(String username, char[] password);
	
}
