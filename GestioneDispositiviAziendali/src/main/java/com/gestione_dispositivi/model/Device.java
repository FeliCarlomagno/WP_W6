package com.gestione_dispositivi.model;

import org.hibernate.annotations.ManyToAny;

import com.gestione_dispositivi.enums.StatoDispositivo;
import com.gestione_dispositivi.enums.TipoDispositivo;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "devices")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Device {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private TipoDispositivo tipoDispositivo;
	
	@Enumerated(EnumType.STRING)
	private StatoDispositivo statoDispositivo;
	
	@ManyToOne
	//@JsonIgnoreProperties({ "devices" })
	private User user;
	
	
	public Device(TipoDispositivo tipoDispositivo, StatoDispositivo statoDispositivo, User user) {
		super();
		this.tipoDispositivo = tipoDispositivo;
		this.statoDispositivo = statoDispositivo;
		this.user = user;
	}
	
	
}
