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
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.Diagnostic;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.Patient;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.Substance;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.services.DiagnosticService;

@RestController
@RequestMapping(value="app/secured/")
public class DiagnosticCRUDController {

	@Autowired
	private DiagnosticService diagnosticService;
	
	@PreAuthorize("hasAuthority('2')")
	@RequestMapping(value="getDiagnostics/{page}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<Page<Diagnostic>> getDiagnostics(@PathVariable int page){
		
		Long diagnosticsCount = diagnosticService.countAllDiagnostics();
		
		if(diagnosticsCount<=0) {
			return new ResponseWrapper<Page<Diagnostic>>(null, false, "Ne postoji ni jedna dijagnoza u sistemu.");
		}else if(page<=0) {
			return new ResponseWrapper<Page<Diagnostic>>(null, false, "Nevalidno unet broj stranica.");
		}
		
		int poslednja = (int)Math.ceil(diagnosticsCount/10)+1;
		Page<Diagnostic> diagnostics = null;
		
		if(page>poslednja) {
			new ResponseWrapper<Page<Diagnostic>>(null, true, "Presli ste postojeci broj stranica.");
		}else {
			diagnostics = diagnosticService.getAllDiagnostics(new PageRequest(page-1, 10));
		}
			
		return new ResponseWrapper<Page<Diagnostic>>(diagnostics, true, "Uspesno vracene dijagnoze.");
	}
	
	@PreAuthorize("hasAuthority('2')")
	@RequestMapping(value="updateDiagnostic", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<Diagnostic> updateDiagnostic(@RequestBody Diagnostic diagnostic){
		
		diagnostic = diagnosticService.insertDiagnostic(diagnostic);
		
		if(diagnostic==null) {
			return new ResponseWrapper<Diagnostic>(null,false,"Neuspesno modifikovana dijagnoza");
		}
				
		return new ResponseWrapper<Diagnostic>(diagnostic,true,"Uspesno modifikovana dijagnoza");		
	}
	
	@PreAuthorize("hasAuthority('2')")
	@RequestMapping(value="deleteDiagnostic", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<Diagnostic> deleteDiagnostic(@RequestParam(value="id") long id){
		
		try {
			diagnosticService.deleteDiagnostic(id);
		}catch(Exception e) {
			return new ResponseWrapper<Diagnostic>(null,false,"Neuspesno izbrisana dijagnoza");
		}
		
		return new ResponseWrapper<Diagnostic>(null,true,"Uspesno izbrisana dijagnoza");		
	}
	
	
	
}
