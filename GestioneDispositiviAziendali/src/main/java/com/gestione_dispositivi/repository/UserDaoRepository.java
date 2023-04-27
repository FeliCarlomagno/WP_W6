package com.gestione_dispositivi.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.gestione_dispositivi.model.User;

public interface UserDaoRepository extends CrudRepository<User, Long>, PagingAndSortingRepository<User, Long> {
	boolean existsByEmail(String email);
	Optional<User> findByEmail(String email);
	boolean existsByUsername(String username);
	Optional<User> findByUsername(String username);
}
