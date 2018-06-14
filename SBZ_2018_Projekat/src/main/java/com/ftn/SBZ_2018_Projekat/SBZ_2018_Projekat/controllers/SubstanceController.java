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
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.Substance;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.services.SubstanceService;


@RestController
@RequestMapping(value = "app/secured/")
public class SubstanceController {

	@Autowired
	private SubstanceService substanceService;
	
	@PreAuthorize("hasAuthority('2')")
	@RequestMapping(value="getSubstances/{page}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<Page<Substance>> getSubstances(@PathVariable int page){
		
		Long substancesCount = substanceService.countSubstances();
		
		if(substancesCount<=0) {
			return new ResponseWrapper<Page<Substance>>(null, false, "Ne postoji ni jedna substanca u sistemu.");
		}else if(page<=0) {
			return new ResponseWrapper<Page<Substance>>(null, false, "Nevalidno unet broj stranica.");
		}
		
		int poslednja = (int)Math.ceil(substancesCount/10)+1;
		Page<Substance> subastances = null;
		
		if(page>poslednja) {
			new ResponseWrapper<Page<Substance>>(null, true, "Presli ste postojeci broj stranica.");
		}else {
			subastances = substanceService.getSubstance(new PageRequest(page-1, 10));
		}
			
		return new ResponseWrapper<Page<Substance>>(subastances, true, "Uspesno vracene substance.");
	}
	
	@PreAuthorize("hasAuthority('2')")
	@RequestMapping(value="insertSubstance", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<Substance> insertSubstance(@RequestBody Substance substance){
		
		substance = substanceService.insertSubstance(substance);
		
		if(substance==null) {
			return new ResponseWrapper<Substance>(null,false,"Neuspesno uneta substanca");
		}
				
		return new ResponseWrapper<Substance>(substance,true,"Uspesno uneta substanca");		
	}
	
	@PreAuthorize("hasAuthority('2')")
	@RequestMapping(value="updateSubstance", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<Substance> updateSubstance(@RequestBody Substance substance){
		
		substance = substanceService.updateSubstance(substance);
		
		if(substance==null) {
			return new ResponseWrapper<Substance>(null,false,"Neuspesno modifikovana substanca");
		}
				
		return new ResponseWrapper<Substance>(substance,true,"Uspesno modifikovana substanca");		
	}
	
	@PreAuthorize("hasAuthority('2')")
	@RequestMapping(value="deleteSubstance", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<Substance> deleteSubstance(@RequestParam(value="id") long id){
		
		try {
			substanceService.deleteSubstance(id);
		}catch(Exception e) {
			return new ResponseWrapper<Substance>(null,false,"Neuspesno izbrisana substanca");
		}			
		return new ResponseWrapper<Substance>(null,true,"Uspesno izbrisana substanca");		
	}
	
}
