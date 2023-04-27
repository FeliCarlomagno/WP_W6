package com.gestione_dispositivi.configuration;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.gestione_dispositivi.model.User;
import com.github.javafaker.Faker;

@Configuration
public class UserConfiguration {
	
	@Bean ("UtenteCustom")
	@Scope("prototype")
	public User userCustom(String name, String lastname, String username , String email) {
		return User.builder()
				.name(name)
				.lastname(lastname)
				.username(username)
				.email(email)
				.build();
	}
	
	@Bean ("NuovoUtente")
	@Scope("prototype")
	public User fakeUser() {
		
		Faker fake = Faker.instance(new Locale ("it-IT"));
		String name = fake.name().firstName();
		String lastname = fake.name().lastName();
		String username = fake.funnyName().toString();
		String email = name.charAt(0) + "." + lastname + "@example.com";
		User u = User.builder().name(name).lastname(lastname).username(username).email(email).build();
		return u;
	}
}
