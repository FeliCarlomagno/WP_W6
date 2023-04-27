package com.gestione_dispositivi.model;

import java.util.List;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (nullable = false)
	private String name;
	
	@Column (nullable = false)
	private String lastname;
	
	@Column (nullable = false, unique = true)
	private String username;
	
	@Column (nullable = false, unique = true)
	private String email;
	
	@OneToMany(mappedBy = "user")
	private List<Device> devices;
	
	
	public User(String name, String lastname, String username, String email) {
		super();
		this.name = name;
		this.lastname = lastname;
		this.username = username;
		this.email = email;
	}
	
	
}
