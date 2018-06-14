package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.Disease;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.services.DiseaseService;

@RestController
@RequestMapping(value="app/secured/")
public class DiseaseController {

	@Autowired
	private DiseaseService diseaseService;
	
	@PreAuthorize("hasAuthority('2')")
	@RequestMapping(value="getDiseases/{page}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<Page<Disease>> getDiseases(@PathVariable int page){
		
		Long diseasesCount = diseaseService.countDiseases();
		
		if(diseasesCount<=0) {
			return new ResponseWrapper<Page<Disease>>(null, false, "Ne postoji ni jedna bolest u sistemu.");
		}else if(page<=0) {
			return new ResponseWrapper<Page<Disease>>(null, false, "Nevalidno unet broj stranica.");
		}
		
		int poslednja = (int)Math.ceil(diseasesCount/10)+1;
		Page<Disease> diseases = null;
		
		if(page>poslednja) {
			new ResponseWrapper<Page<Disease>>(null, true, "Presli ste postojeci broj stranica.");
		}else {
			diseases = diseaseService.getDiseases(new PageRequest(page-1, 10));
		}
			
		return new ResponseWrapper<Page<Disease>>(diseases, true, "Uspesno vracene bolesti.");
	}
	
	@PreAuthorize("hasAuthority('2')")
	@RequestMapping(value="insertDisease", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<Disease> insertDisease(@RequestBody Disease disease){
		
		disease = diseaseService.insertDisease(disease);
		
		if(disease==null) {
			return new ResponseWrapper<Disease>(null,false,"Neuspesno uneta bolest");
		}
				
		return new ResponseWrapper<Disease>(disease,true,"Uspesno uneta bolest");		
	}
	
	@PreAuthorize("hasAuthority('2')")
	@RequestMapping(value="updateDisease", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<Disease> updateDisease(@RequestBody Disease disease){
		
		disease = diseaseService.updateDisease(disease);
		
		if(disease==null) {
			return new ResponseWrapper<Disease>(null,false,"Neuspesno modifikovana bolest");
		}
				
		return new ResponseWrapper<Disease>(disease,true,"Uspesno modifikovana bolest");		
	}
	
	@PreAuthorize("hasAuthority('2')")
	@RequestMapping(value="deleteDisease", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<Disease> deleteDisease(@RequestParam(value="id") long id){
		
		try {
			diseaseService.deleteDisease(id);
		}catch(Exception e) {
			return new ResponseWrapper<Disease>(null,false,"Neuspesno izbrisana bolest");
		}			
		return new ResponseWrapper<Disease>(null,true,"Uspesno izbrisana bolest");		
	}
	
}
