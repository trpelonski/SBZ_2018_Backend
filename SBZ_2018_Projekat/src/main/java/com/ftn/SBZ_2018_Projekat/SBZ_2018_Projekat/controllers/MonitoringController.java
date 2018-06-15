package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONObject;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.events.HeartBeatEvent;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.events.OxygenLevelDroppingEvent;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.events.OxygenLevelRisingEvent;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.events.PatientOxygen;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.Patient;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.security.TokenUtils;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.services.PatientService;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.userDetails.LoggedUser;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.userDetails.LoggedUsers;

@Component
public class MonitoringController extends TextWebSocketHandler {

	@Autowired
	private PatientService patientService;
	
	@Autowired
	private TokenUtils tokenUtils;
	
	@Autowired
	private LoggedUsers loggedUsers;
	
	static Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<WebSocketSession>());
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) {
		System.out.println("Opened");
		sessions.add(session);
	}
	
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
		System.out.println("Closed");
		LoggedUser loggedUser = (LoggedUser) session.getAttributes().get("loggedUser");
		
		for(Timer timer : loggedUser.getTimers()) {
			timer.cancel();
		}
		
		sessions.remove(session);
	}

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message)
			throws InterruptedException, IOException {

		String payload = message.getPayload();
		JSONObject jsonObject = null;
		try {
			jsonObject = new JSONObject(payload);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if(jsonObject!=null) {
				String username = tokenUtils.getUsernameFromToken(jsonObject.getString("token"));
				System.out.println(username);
				LoggedUser loggedUser = loggedUsers.getLoggedUsers().get(username);
				session.getAttributes().put("loggedUser", loggedUser);
				
				KieSession kieSession = loggedUser.getKieSessions().get("eventSession");
		    	kieSession.setGlobal("socketSession", session);

		    	insertIntensiveCarePatients(kieSession,loggedUser.getTimers());
			}
						
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	private void insertIntensiveCarePatients(KieSession kieSession, ArrayList<Timer> timers) {
		ArrayList<Patient> patients = (ArrayList<Patient>) patientService.getAllPatients();
		kieSession.insert(patients.get(2));
		kieSession.insert(patients.get(3));
		kieSession.insert(patients.get(4));
		
		/*new Thread(new Runnable() {
            public void run() {
            	kieSession.fireUntilHalt();
            }
        }).start();
		*/
		
		Timer rulesTimer1 = timers.get(0);
		Timer rulesTimer2 = timers.get(1);
		Timer timer1 = timers.get(2);
		Timer timer2 = timers.get(3);
		Timer timer3 = timers.get(4);
		Timer timer4 = timers.get(5);
		
		rulesTimer1.scheduleAtFixedRate(new TimerTask() {
			long timeToStop = System.currentTimeMillis();
			@Override
			public void run() {
				if(System.currentTimeMillis()-timeToStop > 1800*1000) { //30 min
					cancel();
				}else {
					kieSession.getAgenda().getAgendaGroup("heartRules").setFocus();
					kieSession.fireAllRules();
				}		
			}
    		
    	},0,10000);
		
		rulesTimer2.scheduleAtFixedRate(new TimerTask() {
			long timeToStop = System.currentTimeMillis();
			@Override
			public void run() {
				if(System.currentTimeMillis()-timeToStop > 1800*1000) { //30 min
					cancel();
				}else {
					kieSession.getAgenda().getAgendaGroup("oxygenRules").setFocus();
					kieSession.fireAllRules();
				}		
			}
    		
    	},100,900*1000);
		
		timer1.scheduleAtFixedRate(new TimerTask() {
			long timeToStop = System.currentTimeMillis()+1000;
			@Override
			public void run() {
				if(System.currentTimeMillis()-timeToStop > 20*1000) {
					cancel();
				}else {
					HeartBeatEvent hbe = new HeartBeatEvent(patients.get(3).getId());
					kieSession.insert(hbe);
				}		
			}
    		
    	},1000,250);
		
		PatientOxygen po = new PatientOxygen(patients.get(4).getId(),71, patients.get(4).getStringId());
		
		timer2.schedule(new TimerTask() {
			@Override
			public void run() {
				OxygenLevelRisingEvent olr = new OxygenLevelRisingEvent(po,2);
				kieSession.insert(po);
				kieSession.insert(olr);	
			}
    		
    	},0);
		
		timer3.schedule(new TimerTask() {
			@Override
			public void run() {
				OxygenLevelDroppingEvent old = new OxygenLevelDroppingEvent(po,5);
				kieSession.insert(old);	
			}
    		
    	},1000);
		
		
		timer4.scheduleAtFixedRate(new TimerTask() {
			long timeToStop = System.currentTimeMillis()+20000;
			@Override
			public void run() {
				if(System.currentTimeMillis()-timeToStop > 20*1000) {
					cancel();
				}else {
					HeartBeatEvent hbe = new HeartBeatEvent(patients.get(4).getId());
					kieSession.insert(hbe);
				}
			}
    		
    	},20000,250);
		
	}
	
}
