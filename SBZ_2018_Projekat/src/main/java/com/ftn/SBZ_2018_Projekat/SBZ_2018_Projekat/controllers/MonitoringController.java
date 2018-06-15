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
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.services.PatientService;

@Component
public class MonitoringController extends TextWebSocketHandler {

	@Autowired
	private PatientService patientService;
	
	@Autowired
	private KieContainer kieContainer;
	
	static Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<WebSocketSession>());
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) {
		System.out.println("Opened");
		sessions.add(session);
		KieSession kieSession = kieContainer.newKieSession("eventSession");
    	kieSession.setGlobal("socketSession", session);
		
    	insertIntensiveCarePatients(kieSession);
	}
	
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
		System.out.println("Closed");
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
			session.sendMessage(new TextMessage("Hi " + jsonObject.get("user") + " how may we help you?"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	private void insertIntensiveCarePatients(KieSession kieSession) {
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
		
		Timer rulesTimer = new Timer();
		Timer timer1 = new Timer();
		Timer timer2 = new Timer();
		
		rulesTimer.scheduleAtFixedRate(new TimerTask() {
			long timeToStop = System.currentTimeMillis()+10000;
			@Override
			public void run() {
				if(System.currentTimeMillis()-timeToStop > 1200*1000) { //20 min
					cancel();
				}else {
					kieSession.fireAllRules();
				}		
			}
    		
    	},10000,1000);
		
		timer1.scheduleAtFixedRate(new TimerTask() {
			long timeToStop = System.currentTimeMillis();
			@Override
			public void run() {
				if(System.currentTimeMillis()-timeToStop > 20*1000) {
					cancel();
					kieSession.fireAllRules();
				}else {
					HeartBeatEvent hbe = new HeartBeatEvent(patients.get(3).getId());
					kieSession.insert(hbe);
				}		
			}
    		
    	},10000,100);
		
		timer2.schedule(new TimerTask() {
			long timeToStop = System.currentTimeMillis();
			@Override
			public void run() {
				if(System.currentTimeMillis()-timeToStop > 15*1000) {
					cancel();
					kieSession.fireAllRules();
				}else {
					PatientOxygen po = new PatientOxygen(patients.get(4).getId(),71, patients.get(4).getStringId());
					OxygenLevelRisingEvent olr = new OxygenLevelRisingEvent(po,2);
					OxygenLevelDroppingEvent old = new OxygenLevelDroppingEvent(po,5);
					kieSession.insert(olr);
					kieSession.insert(po);
					kieSession.insert(old);
				}		
			}
    		
    	},10000);
		
	}
	
}
