package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Sbz2018ProjekatApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sbz2018ProjekatApplication.class, args);
	}
	
	@Bean
	public KieContainer kieContainer() {
		return KieServices.Factory.get().getKieClasspathContainer();
	}
	
}
