package com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.ftn.SBZ_2018_Projekat.SBZ_2018_Projekat.controllers.MonitoringController;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	@Bean
	public MonitoringController monitoringController() {
		return new MonitoringController();
	}
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(monitoringController(), "/websocket").setAllowedOrigins("*");
	}

}
