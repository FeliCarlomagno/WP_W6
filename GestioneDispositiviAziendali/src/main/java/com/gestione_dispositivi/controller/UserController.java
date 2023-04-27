package com.gestione_dispositivi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestione_dispositivi.model.User;
import com.gestione_dispositivi.service.UserService;

@RestController
@RequestMapping ("/api/user")
public class UserController {
	
	@Autowired UserService userService;
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getById(@PathVariable (name = "id") Long id){
		return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
	}
	
	
	@GetMapping
	public ResponseEntity<?> getAllUser(Pageable pageable){
		return new ResponseEntity<>(userService.findAll(pageable),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> createuser(@RequestBody User u){
		return new ResponseEntity<>(userService.saveUser(u), HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<?> updateUser(@RequestBody User u){
		return new ResponseEntity<>(userService.saveUser(u), HttpStatus.OK);
	}
	
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		return new ResponseEntity<>(userService.removeUser(id), HttpStatus.OK);
	}
}
