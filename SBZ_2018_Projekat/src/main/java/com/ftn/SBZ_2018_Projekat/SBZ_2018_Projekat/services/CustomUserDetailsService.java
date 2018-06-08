package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.User;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.repositories.UserRepository;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.userDetails.CustomUserDetailsFactory;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findOneByUsername(username);
		
		if(user==null) {
			throw new UsernameNotFoundException("Korisnik ne postoji");
		}else {
			return CustomUserDetailsFactory.createCustomUserDetails(user);
		}
			
	}

}
