package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.userDetails;

import org.springframework.security.core.authority.AuthorityUtils;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;


import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.User;

public class CustomUserDetailsFactory {

	private CustomUserDetailsFactory() {}
	
	public static CustomUserDetails createCustomUserDetails(User user) {
		return new CustomUserDetails(
				user.getUsername(),
				user.getId(),
				mapToGrantedAuthorities(user.getRole().getId().toString())
				);
	}
	
	private static List<GrantedAuthority> mapToGrantedAuthorities(String role) {
        return AuthorityUtils.commaSeparatedStringToAuthorityList(role);
    }
	
}
