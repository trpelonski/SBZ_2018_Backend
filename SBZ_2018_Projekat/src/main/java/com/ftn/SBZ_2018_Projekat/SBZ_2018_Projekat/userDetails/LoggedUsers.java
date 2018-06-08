package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.userDetails;

import java.util.HashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Scope(value = "singleton")
@Component
public class LoggedUsers {

	private HashMap<String,LoggedUser> loggedUsers = new HashMap<String,LoggedUser>();
	
	public LoggedUsers() {}

	public HashMap<String, LoggedUser> getLoggedUsers() {
		return loggedUsers;
	}

	public void setLoggedUsers(HashMap<String, LoggedUser> loggedUsers) {
		this.loggedUsers = loggedUsers;
	}
}
