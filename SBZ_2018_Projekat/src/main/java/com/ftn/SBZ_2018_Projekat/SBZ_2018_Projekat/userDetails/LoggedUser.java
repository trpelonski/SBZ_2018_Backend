package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.userDetails;

import java.util.HashMap;

import org.kie.api.runtime.KieSession;

public class LoggedUser {

	private String username;
	private HashMap<String,KieSession> kieSessions;
	
	public LoggedUser() {}

	public LoggedUser(String username, HashMap<String, KieSession> kieSessions) {
		super();
		this.username = username;
		this.kieSessions = kieSessions;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public HashMap<String, KieSession> getKieSessions() {
		return kieSessions;
	}

	public void setKieSessions(HashMap<String, KieSession> kieSessions) {
		this.kieSessions = kieSessions;
	}

	@Override
	public String toString() {
		return "LoggedUser [username=" + username + ", kieSessions=" + kieSessions + "]";
	}
}
