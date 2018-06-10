package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.controllers;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.dto.ResponseWrapper;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.User;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.security.TokenUtils;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.services.DiseaseService;
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
			
    	KieSession kSession1 = kieContainer.newKieSession("rulesSession");
    	kSession1.setGlobal("diseaseService", diseaseService);
    	kSession1.setGlobal("days60", System.currentTimeMillis()-TimeUnit.DAYS.toMillis(60));
    	kSession1.setGlobal("days14", System.currentTimeMillis()-TimeUnit.DAYS.toMillis(14));
    	kSession1.setGlobal("days21", System.currentTimeMillis()-TimeUnit.DAYS.toMillis(21));
    	kSession1.setGlobal("months6", System.currentTimeMillis()-TimeUnit.DAYS.toMillis(6*31));
    	
    	KieSession kSession2 = kieContainer.newKieSession("medicationsSession");
    	
    	HashMap<String,KieSession> kieSessions = new HashMap<String,KieSession>();
    	kieSessions.put("rulesSession", kSession1);
    	kieSessions.put("medicationsSession", kSession2);
    	
    	LoggedUser loggedUser = new LoggedUser(user.getUsername(), kieSessions);
		loggedUsers.getLoggedUsers().put(loggedUser.getUsername(), loggedUser);
		
		return new ResponseWrapper<String>(token,true,"Uspesno logovanje!");
	}
	
}
