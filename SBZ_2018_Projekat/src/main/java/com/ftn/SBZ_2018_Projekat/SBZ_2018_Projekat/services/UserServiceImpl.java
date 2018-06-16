package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.User;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.UserRole;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.repositories.UserRepository;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.repositories.UserRoleRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Override
	public User findByUsername(String username) {
		return userRepository.findOneByUsername(username);
	}

	@Override
	public User findByUsernameAndPassword(String username, char[] password) {
		return userRepository.findOneByUsernameAndPassword(username, password);
	}

	@Override
	public User insertUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.delete(id);;
	}

	@Override
	public Page<User> getUsers(String username, Pageable pageable) {
		return userRepository.findByUsernameNot(username,pageable);
	}

	@Override
	public Long countUsers(String username) {
		return userRepository.countByUsernameNot(username);
	}

	@Override
	public User findById(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public UserRole getUserRole(Long id) {
		return userRoleRepository.findOne(id);
	}

}
