package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.controllers;

import java.util.ArrayList;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.dto.ReportDTO;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.dto.ResponseWrapper;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.Patient;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.security.TokenUtils;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.services.PatientService;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.userDetails.LoggedUser;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.userDetails.LoggedUsers;

@RestController
@RequestMapping(value = "app/secured/")
public class ReportController {

	@Autowired
	private PatientService patientService;
	
	@Autowired
	private LoggedUsers loggedUsers;
	
	@Autowired
	private TokenUtils tokenUtils;
	
	@Value("${utils.token.header}")
    private String tokenHeader;
	
	@PreAuthorize("hasAuthority('1')")
	@RequestMapping(value="getPotentialChronicDiseasePatients", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<ArrayList<Patient>> getPotentialChronicDiseasePatients(ServletRequest request){
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
	    String token = httpRequest.getHeader(this.tokenHeader);
	    String username = tokenUtils.getUsernameFromToken(token);
	    
	    LoggedUser loggedUser = loggedUsers.getLoggedUsers().get(username);
	    ArrayList<Patient> allPatients = (ArrayList<Patient>) patientService.getAllPatients();
	    
	    ReportDTO reportDTO = new ReportDTO(allPatients);

	    KieSession kieSession = loggedUser.getKieSessions().get("reportSession");
		
	    kieSession.insert(reportDTO);
	    
	    kieSession.getAgenda().getAgendaGroup("report1").setFocus();
	    
	    kieSession.fireAllRules();
	        
        for (FactHandle factHandle : kieSession.getFactHandles()) {
            kieSession.delete(factHandle);
        }
	        
		return new ResponseWrapper<ArrayList<Patient>>(reportDTO.getSelectedPatients(),true,"Uspesno dovuceni pacijenti.");	
	}
	
	@PreAuthorize("hasAuthority('1')")
	@RequestMapping(value="getPotentialAddicts", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<ArrayList<Patient>> getPotentialAddicts(ServletRequest request){
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
	    String token = httpRequest.getHeader(this.tokenHeader);
	    String username = tokenUtils.getUsernameFromToken(token);
	    
	    LoggedUser loggedUser = loggedUsers.getLoggedUsers().get(username);
	    ArrayList<Patient> allPatients = (ArrayList<Patient>) patientService.getAllPatients();
	    
	    ReportDTO reportDTO = new ReportDTO(allPatients);

	    KieSession kieSession = loggedUser.getKieSessions().get("reportSession");
		
	    kieSession.insert(reportDTO);
	    
	    kieSession.getAgenda().getAgendaGroup("report2").setFocus();
	    
	    kieSession.fireAllRules();
	        
        for (FactHandle factHandle : kieSession.getFactHandles()) {
            kieSession.delete(factHandle);
        }
	        
		return new ResponseWrapper<ArrayList<Patient>>(reportDTO.getSelectedPatients(),true,"Uspesno dovuceni pacijenti.");	
	}
	
	@PreAuthorize("hasAuthority('1')")
	@RequestMapping(value="getPotentialWeakImmunityPatients", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<ArrayList<Patient>> getPotentialWeakImmunityPatients(ServletRequest request){
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
	    String token = httpRequest.getHeader(this.tokenHeader);
	    String username = tokenUtils.getUsernameFromToken(token);
	    
	    LoggedUser loggedUser = loggedUsers.getLoggedUsers().get(username);
	    ArrayList<Patient> allPatients = (ArrayList<Patient>) patientService.getAllPatients();
	    
	    ReportDTO reportDTO = new ReportDTO(allPatients);

	    KieSession kieSession = loggedUser.getKieSessions().get("reportSession");
		
	    kieSession.insert(reportDTO);
	    
	    kieSession.getAgenda().getAgendaGroup("report3").setFocus();
	    
	    kieSession.fireAllRules();
	        
        for (FactHandle factHandle : kieSession.getFactHandles()) {
            kieSession.delete(factHandle);
        }
	        
		return new ResponseWrapper<ArrayList<Patient>>(reportDTO.getSelectedPatients(),true,"Uspesno dovuceni pacijenti.");	
	}
}
