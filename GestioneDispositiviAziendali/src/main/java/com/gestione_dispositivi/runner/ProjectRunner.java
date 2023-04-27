package com.gestione_dispositivi.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import com.gestione_dispositivi.service.DeviceService;
import com.gestione_dispositivi.service.UserService;

public class ProjectRunner implements ApplicationRunner {
	
	@Autowired UserService userService;
	@Autowired DeviceService deviceService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		createUser();
		createDevice();
	}
	
	
	
	public void createUser() {
		for(int i = 0; i < 20 ; i++) {
			userService.createFakeUser();;
		}
	}
	
	
	public void createDevice() {
		for(int i = 0; i < 20 ; i++) {
			deviceService.createFakeDevice();
		}
	}

}
