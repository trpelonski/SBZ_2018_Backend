package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.User;

public interface UserService {

	public User findByUsername(String username);
	public User findByUsernameAndPassword(String username, char[] password);
	public User insertUser(User user);
	public User updateUser(User user);
	public void deleteUser(Long id);
	public Page<User> getUsers(String username, Pageable pageable);
	public Long countUsers(String username);
	public User findById(Long id);
}
