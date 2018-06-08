package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.dto.ResponseWrapper;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.Antibiotic;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.Diagnostic;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.Disease;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.DiseaseSymptom;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.Patient;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.Symptom;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.security.TokenUtils;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.services.AntibioticService;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.services.DiagnosticService;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.services.DiseaseService;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.services.DiseaseSymptomService;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.services.PatientService;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.services.SymptomService;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.userDetails.LoggedUser;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.userDetails.LoggedUsers;

@RestController
@RequestMapping(value = "app/secured/")
public class DiagnosticController {

	@Autowired
	private PatientService patientService;
	
	@Autowired
	private SymptomService symptomService;
	
	@Autowired
	private DiagnosticService diagnosticService;
	
	@Autowired
	private DiseaseSymptomService diseaseSymptomService;
	
	@Autowired
	private DiseaseService diseaseService;
	
	@Autowired
	private AntibioticService antibioticService;
	
	@Autowired
	private TokenUtils tokenUtils;
	
	@Autowired
	private LoggedUsers loggedUsers;
		
	@Value("${utils.token.header}")
    private String tokenHeader;
	
	@PreAuthorize("hasAuthority('1')")
	@RequestMapping(value="getPatients/{page}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<Page<Patient>> getAllPatients(@PathVariable int page){
		
		Long patientsCount = patientService.countAllPatients();
		
		if(patientsCount<=0) {
			return new ResponseWrapper<Page<Patient>>(null, false, "Ne postoji ni jedan pacijent u sistemu.");
		}else if(page<=0) {
			return new ResponseWrapper<Page<Patient>>(null, false, "Nevalidno unet broj stranica.");
		}
		
		int poslednja = (int)Math.ceil(patientsCount/10)+1;
		Page<Patient> patients = null;
			
		if(page>poslednja) {
			patients = patientService.getAllPatients(new PageRequest(poslednja-1, 10));
		}else {
			patients = patientService.getAllPatients(new PageRequest(page-1, 10));
		}
			
		return new ResponseWrapper<Page<Patient>>(patients, true, "Uspesno vraceni pacijenti.");
	}
	
	@PreAuthorize("hasAuthority('1')")
	@RequestMapping(value="findPatients/{page}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<Page<Patient>> findPatients(@PathVariable int page, @RequestParam(value = "name", required = true) String name){
		
		Long patientsCount = patientService.countPatientsByFirstNameAndLastName(name);
		
		if(patientsCount<=0) {
			return new ResponseWrapper<Page<Patient>>(null, false, "Ne postoji ni jedan pacijent u sistemu.");
		}else if(page<=0) {
			return new ResponseWrapper<Page<Patient>>(null, false, "Nevalidno unet broj stranica.");
		}
		
		int poslednja = (int)Math.ceil(patientsCount/10)+1;
		Page<Patient> patients = null;
			
		if(page>poslednja) {
			patients = patientService.getAllPatientsByFirstNameAndLastName(name, new PageRequest(poslednja-1, 10));
		}else {
			patients = patientService.getAllPatientsByFirstNameAndLastName(name, new PageRequest(page-1, 10));
		}
			
		return new ResponseWrapper<Page<Patient>>(patients, true, "Uspesno pronadjeni pacijenti.");
	}
	
	@PreAuthorize("hasAuthority('1')")
	@RequestMapping(value="getSymptoms", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<ArrayList<Symptom>> getAllSymptoms(){
		
		ArrayList<Symptom> symptoms = symptomService.getAllSymptoms(true);
		
		return new ResponseWrapper<ArrayList<Symptom>>(symptoms,true,"Uspesno dovuceni simptomi");
	}
	
	@PreAuthorize("hasAuthority('1')")
	@RequestMapping(value="getDiseases", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<ArrayList<Disease>> getAllDiseases(){
		
		ArrayList<Disease> diseases = diseaseService.getAllDiseases();
		
		return new ResponseWrapper<ArrayList<Disease>>(diseases,true,"Uspesno dovucene boleti");
	}
	
	@PreAuthorize("hasAuthority('1')")
	@RequestMapping(value="getMedications", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<ArrayList<Antibiotic>> getMedications(){
		
		ArrayList<Antibiotic> medications = antibioticService.findAllAntibiotics();
		
		return new ResponseWrapper<ArrayList<Antibiotic>>(medications,true,"Uspesno dovuceni lekovi");
	}
	
	@PreAuthorize("hasAuthority('1')")
	@RequestMapping(value="getMostLikelyDisease", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<Disease> getMostLikelyDisease(@RequestBody Diagnostic diagnostic, ServletRequest request){
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
	    String token = httpRequest.getHeader(this.tokenHeader);
	    String username = tokenUtils.getUsernameFromToken(token);
	    
	    LoggedUser loggedUser = loggedUsers.getLoggedUsers().get(username);
	    
	    KieSession kieSession = loggedUser.getKieSessions().get("rulesSession");
	    
	    kieSession.insert(diagnostic);
	    
	    kieSession.getAgenda().getAgendaGroup("necessary").setFocus();
	    kieSession.fireAllRules();
	    
	    kieSession.getAgenda().getAgendaGroup("disease").setFocus();
	    kieSession.fireAllRules();
	        
        for (FactHandle factHandle : kieSession.getFactHandles()) {
            kieSession.delete(factHandle);
        }
	    
		return new ResponseWrapper<Disease>(diagnostic.getDisease() ,true,"Uspesno dovucena bolest.");	
	}
	
	@PreAuthorize("hasAuthority('1')")
	@RequestMapping(value="getDiseaseSymptoms/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<ArrayList<DiseaseSymptom>> getDiseaseSymptoms(@PathVariable long id){
		
		ArrayList<DiseaseSymptom> diseaseSymptoms = null;
		diseaseSymptoms = diseaseSymptomService.findAllByDisease(id);
		
		if(diseaseSymptoms==null) {
			return new ResponseWrapper<ArrayList<DiseaseSymptom>> (null, false, "Neuspesno vraceni simptomi.");
		}
		
		return new ResponseWrapper<ArrayList<DiseaseSymptom>> (diseaseSymptoms, true, "Uspesno vraceni simptomi.");
	}
	
	@SuppressWarnings("unchecked")
	@PreAuthorize("hasAuthority('1')")
	@RequestMapping(value="getSymptomsDiseases", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<ArrayList<Disease>> getSymptomsDiseases(@RequestBody ArrayList<Symptom> symptoms){
		
		ArrayList<DiseaseSymptom> symptomsDiseases = null;
		symptomsDiseases = diseaseSymptomService.findBySymptoms(symptoms);
		
		if(symptomsDiseases==null) {
			return new ResponseWrapper<ArrayList<Disease>>(null, false, "Neuspesno vracene bolesti");
		}
		
		HashMap<Disease, Integer> diseasesSymptomsNumber = new HashMap<Disease, Integer>();
		
		for(DiseaseSymptom diseaseSymptom : symptomsDiseases) {	
			Disease disease = diseaseSymptom.getDisease();
			if(diseasesSymptomsNumber.containsKey(disease)) {
				diseasesSymptomsNumber.put(disease, diseasesSymptomsNumber.get(disease) + 1);
			}else {
				diseasesSymptomsNumber.put(disease, new Integer(1));
			}		
		}
		
		diseasesSymptomsNumber = sortByValues(diseasesSymptomsNumber);

		ArrayList<Disease> diseases = new ArrayList<Disease>();
		
		for(Map.Entry<Disease, Integer> entry : diseasesSymptomsNumber.entrySet()) {
			diseases.add(entry.getKey());
		}	
		
		return new ResponseWrapper<ArrayList<Disease>>(diseases, true, "Uspesno vracene bolesti");
	}
		
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private HashMap sortByValues(HashMap map) { 
	       List list = new LinkedList(map.entrySet());
	       Collections.sort(list, new Comparator() {
	            public int compare(Object o1, Object o2) {
	               return ((Comparable) ((Map.Entry) (o2)).getValue())
	                  .compareTo(((Map.Entry) (o1)).getValue());
	            }
	       });
	       
	       HashMap sortedHashMap = new LinkedHashMap();
	       for (Iterator it = list.iterator(); it.hasNext();) {
	              Map.Entry entry = (Map.Entry) it.next();
	              sortedHashMap.put(entry.getKey(), entry.getValue());
	       } 
	       return sortedHashMap;
	}
	
}
