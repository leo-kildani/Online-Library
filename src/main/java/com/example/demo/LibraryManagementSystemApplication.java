package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import com.example.demo.entity.CurrentUser;
import org.springframework.context.ApplicationListener;

@SpringBootApplication
public class LibraryManagementSystemApplication implements ApplicationListener<ContextRefreshedEvent> {

	public static void main(String[] args) {

		SpringApplication.run(LibraryManagementSystemApplication.class, args);
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// Create and set a fake user when the application starts
		CurrentUser.createFakeUser();
	}

}
