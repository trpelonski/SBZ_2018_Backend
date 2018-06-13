package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.events;

import java.util.ArrayList;
import java.util.TimerTask;

import org.kie.api.runtime.KieSession;

import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.Patient;

public class IntensiveCareTimerTask extends TimerTask{

	private ArrayList<Patient> patients;
	private KieSession intensiveCareSession;
	private long timeToStop;
	
	public IntensiveCareTimerTask(ArrayList<Patient> patients, KieSession intensiveCareSession) {
		super();
		this.patients = patients;
		this.intensiveCareSession = intensiveCareSession;
		timeToStop = System.currentTimeMillis();
	}

	@Override
	public void run() {
		
		if(System.currentTimeMillis()-timeToStop > 10*1000) {
			cancel();
			intensiveCareSession.fireAllRules();
		}else {
			HeartBeatEvent hbe = new HeartBeatEvent(patients.get(3).getId());
			intensiveCareSession.insert(hbe);
			System.out.println("UNeooooo");
		}
	}
}
