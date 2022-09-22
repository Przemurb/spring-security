package com.example.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class SpringSecurityApplication {
	private final InitAppService initAppService;

	public SpringSecurityApplication(InitAppService initAppService) {
		this.initAppService = initAppService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void applicationReady() {
		initAppService.initData();
	}

}
