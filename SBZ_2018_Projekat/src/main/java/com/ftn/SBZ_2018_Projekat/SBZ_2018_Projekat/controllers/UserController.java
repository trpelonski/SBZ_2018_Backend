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
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.model.User;
import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.services.UserService;

@RestController
@RequestMapping(value = "app/secured/")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PreAuthorize("hasAuthority('2')")
	@RequestMapping(value="getUsers/{page}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<Page<User>> getUsers(@PathVariable int page, @RequestParam(value="username",required=true) String username){
		
		Long usersCount = userService.countUsers(username);
		
		if(usersCount<=0) {
			return new ResponseWrapper<Page<User>>(null, false, "Ne postoji ni jedan korisnik u sistemu.");
		}else if(page<=0) {
			return new ResponseWrapper<Page<User>>(null, false, "Nevalidno unet broj stranica.");
		}
		
		int poslednja = (int)Math.ceil(usersCount/10)+1;
		Page<User> users = null;
		
		if(page>poslednja) {
			new ResponseWrapper<Page<User>>(null, true, "Presli ste postojeci broj stranica.");
		}else {
			users = userService.getUsers(username,new PageRequest(page-1, 10));
		}
			
		return new ResponseWrapper<Page<User>>(users, true, "Uspesno vraceni korisnici.");
	}
	
	@PreAuthorize("hasAuthority('2')")
	@RequestMapping(value="insertUser", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<User> insertUser(@RequestBody User user){
		
		if(userService.findByUsername(user.getUsername())!=null) {
			return new ResponseWrapper<User>(null,false,"Korisnicko ime vec postoji!");
		}
		
		
		
		if(user.getRole().getId()==1) {
			char[] password = {'l','e','k','a','r','1','2','3','4','5'};
			user.setPassword(password);
			user.setRole(userService.getUserRole(user.getRole().getId()));
		}else {
			char[] password = {'a','d','m','i','n','1','2','3','4','5'};
			user.setPassword(password);
			user.setRole(userService.getUserRole(user.getRole().getId()));
		}
		
		if(userService.findByUsername(user.getUsername())!=null) {
			return new ResponseWrapper<User>(null,false,"Korisnicko ime vec postoji!");
		}	
		user = userService.insertUser(user);
		
		if(user==null) {
			return new ResponseWrapper<User>(null,false,"Neuspesno unet korisnik");
		}
				
		return new ResponseWrapper<User>(user,true,"Uspesno unet korisnik");		
	}
	
	@PreAuthorize("hasAuthority('2')")
	@RequestMapping(value="updateUser", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<User> updateUser(@RequestBody User user){
		
		user = userService.updateUser(user);
				
		if(user==null) {
			return new ResponseWrapper<User>(null,false,"Neuspesno modifikovan korisnik");
		}
				
		return new ResponseWrapper<User>(user,true,"Uspesno modifikovan korisnik");		
	}
	
	@PreAuthorize("hasAuthority('2')")
	@RequestMapping(value="deleteUser", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<User> deleteUser(@RequestParam(value="id") long id){
		
		User user = userService.findById(id);
		for(Diagnostic diagnostic : user.getDiagnostics()) {
			diagnostic.setDoctor(null);
		}
		
		try {
			userService.deleteUser(id);
		}catch(Exception e) {
			return new ResponseWrapper<User>(null,false,"Neuspesno izbrisan korisnik");
		}			
		return new ResponseWrapper<User>(null,true,"Uspesno izbrisan korisnik");		
	}
	
}
