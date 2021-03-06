package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.dto.ResponseWrapper;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.events.HeartBeatEvent;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.events.OxygenLevelDroppingEvent;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.events.PatientOxygen;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.Patient;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.User;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.security.TokenUtils;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.services.DiseaseService;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.services.PatientService;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.services.UserService;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.userDetails.CustomUserDetailsFactory;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.userDetails.LoggedUser;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.userDetails.LoggedUsers;

@RestController
@RequestMapping(value = "app/")
public class LoginController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private DiseaseService diseaseService;
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private TokenUtils tokenUtils;
	
	@Autowired
	private LoggedUsers loggedUsers;
	
	@Autowired
	private KieContainer kieContainer;
	
	@RequestMapping(value = "login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<String> prijaviKorisnika(@RequestBody User user) {
		
		user = userService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		String token = null;
		
		if(user==null) {
			return new ResponseWrapper<String>(null,false,"Neispravno uneseno korisnicko ime ili lozinka.");
		}else {
			token = tokenUtils.generateToken(CustomUserDetailsFactory.createCustomUserDetails(user));
		}
    	
    	LoggedUser loggedUser = new LoggedUser(user.getUsername(), createSessionsMap(), createTimers());
		loggedUsers.getLoggedUsers().put(loggedUser.getUsername(), loggedUser);
		
		return new ResponseWrapper<String>(token,true,"Uspesno logovanje!");
	}
	
	@PreAuthorize("hasAuthority('1') or hasAuthority('2')")
	@RequestMapping(value = "secured/logout", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<String> odjaviKorisnika(@RequestParam(value="username", required = true) String username) {
		
		LoggedUser loggedUser = loggedUsers.getLoggedUsers().get(username);
		
		if(loggedUser==null) {
			return new ResponseWrapper<String>("",false,"Korisnik nije logovan!");
		}
		
		loggedUsers.getLoggedUsers().remove(username);
		
		return new ResponseWrapper<String>("",true,"Uspesno odjavljivanje!");
	}
	
	private HashMap<String,KieSession> createSessionsMap(){
		
		KieSession kSession1 = kieContainer.newKieSession("rulesSession");
    	kSession1.setGlobal("diseaseService", diseaseService);
    	kSession1.setGlobal("days60", System.currentTimeMillis()-TimeUnit.DAYS.toMillis(60));
    	kSession1.setGlobal("days14", System.currentTimeMillis()-TimeUnit.DAYS.toMillis(14));
    	kSession1.setGlobal("days21", System.currentTimeMillis()-TimeUnit.DAYS.toMillis(21));
    	kSession1.setGlobal("months6", System.currentTimeMillis()-TimeUnit.DAYS.toMillis(6*31));
    	
    	KieSession kSession2 = kieContainer.newKieSession("medicationsSession");
    	
    	KieSession kSession3 = kieContainer.newKieSession("reportSession");
    	kSession3.setGlobal("months6", System.currentTimeMillis()-TimeUnit.DAYS.toMillis(6*31));    	
    	kSession3.setGlobal("months12", System.currentTimeMillis()-TimeUnit.DAYS.toMillis(12*31));
    	kSession3.setGlobal("years2", System.currentTimeMillis()-TimeUnit.DAYS.toMillis(24*31));
    	
    	KieSession kSession4 = kieContainer.newKieSession("eventSession");
    	KieSession kSession5 = kieContainer.newKieSession("eventSessionPseudo");
    	
    	HashMap<String,KieSession> kieSessions = new HashMap<String,KieSession>();
    	kieSessions.put("rulesSession", kSession1);
    	kieSessions.put("medicationsSession", kSession2);
    	kieSessions.put("reportSession", kSession3);
    	kieSessions.put("eventSession", kSession4);
    	kieSessions.put("eventSessionPseudo", kSession5);
    		
		return kieSessions;
	}
	
	private ArrayList<Timer> createTimers(){
		
		Timer rulesTimer1 = new Timer();
		Timer rulesTimer2 = new Timer();
		Timer timer1 = new Timer();
		Timer timer2 = new Timer();
		Timer timer3 = new Timer();
		Timer timer4 = new Timer();
		
		ArrayList<Timer> timers = new ArrayList<Timer>();
		
		timers.add(rulesTimer1);
		timers.add(rulesTimer2);
		timers.add(timer1);
		timers.add(timer2);
		timers.add(timer3);
		timers.add(timer4);
		
		return timers;
	}
	
}

