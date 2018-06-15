package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.userDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;

import org.kie.api.runtime.KieSession;

public class LoggedUser {

	private String username;
	private HashMap<String,KieSession> kieSessions;
	private ArrayList<Timer> timers;
	
	public LoggedUser() {}

	public LoggedUser(String username, HashMap<String, KieSession> kieSessions, ArrayList<Timer> timers) {
		super();
		this.username = username;
		this.kieSessions = kieSessions;
		this.timers = timers ;
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
	
	public ArrayList<Timer> getTimers() {
		return timers;
	}

	public void setTimers(ArrayList<Timer> timers) {
		this.timers = timers;
	}

	@Override
	public String toString() {
		return "LoggedUser [username=" + username + ", kieSessions=" + kieSessions + "]";
	}
}
