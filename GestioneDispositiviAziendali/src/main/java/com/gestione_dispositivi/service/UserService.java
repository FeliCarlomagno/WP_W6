package com.gestione_dispositivi.service;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gestione_dispositivi.model.User;
import com.gestione_dispositivi.repository.UserDaoRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
	
	@Autowired UserDaoRepository repoUser;
	
	
	@Autowired @Qualifier ("NuovoUtente") private ObjectProvider<User> fakeUserProvider;
	
	public User saveUser(User u) {
		if(repoUser.existsByEmail(u.getEmail()) || repoUser.existsByUsername(u.getUsername())) {
			throw new EntityExistsException("EMAIL OR USERNAME ALREADY EXIST IN THE DATABSE!");
		}else {
			repoUser.save(u);
		}
		return u;
	}
	
	public void createFakeUser() {
		User u = fakeUserProvider.getObject();
		saveUser(u);
	}
	
	public String removeUser(Long id) {
		if(repoUser.existsById(id)) {
			repoUser.deleteById(id);
		}else {
			throw new EntityNotFoundException("USER DO NOT EXIST ON DATABSE!");
		}
		
		return "USER REMOVED SUCCESSFULLY!";
	}
	
	
	public User updateUser(User u) {
		if(repoUser.existsById(u.getId())) {
			repoUser.save(u);
		}else {
			throw new EntityNotFoundException("USER NOT FOUND BECAUSE IT DOES NOT EXIST OR ID IS INCORRECT");
		}
		return u;
	}
	
	
	public User findById(Long id) {
		if(repoUser.existsById(id)) {
			return repoUser.findById(id).get();
		}else {
			throw new EntityNotFoundException("USER WHIT THIS ID NOT EXIST!");
		}
	}
	
	public User findByEmail(String email) {
		if(repoUser.existsByEmail(email)) {
			return repoUser.findByEmail(email).get();
		}else {
			throw new EntityNotFoundException("USER WHIT THIS EMAIL NOT EXIST!");
		}
	}
	
	public User findByUsername(String username) {
		if(repoUser.existsByUsername(username)) {
			return repoUser.findByUsername(username).get();
		}else {
			throw new EntityNotFoundException("USER WHIT THIS USERNAME NOT EXIST!");
		}
	}
	
	public Page<User> findAll(Pageable pageable){
		return (Page<User>) repoUser.findAll(pageable);
	}
}
