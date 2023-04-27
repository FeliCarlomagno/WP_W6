package com.gestione_dispositivi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.gestione_dispositivi.enums.StatoDispositivo;
import com.gestione_dispositivi.enums.TipoDispositivo;
import com.gestione_dispositivi.model.Device;

import lombok.Builder;

@Configuration
@Builder
public class DeviceConfiguration {
	
	@Bean("DispositivoCustom")
	@Scope("prototype")
	public Device customDevice(TipoDispositivo tipo, StatoDispositivo stato) {
		return Device.builder()
				.tipoDispositivo(tipo)
				.statoDispositivo(stato)
				.build();
	}
	
	
	@Bean ("NuovoDispositivo")
	@Scope("prototype")
	public Device fakeDevice() {
		TipoDispositivo tipo = TipoDispositivo.tipoRandom();
		StatoDispositivo stato = StatoDispositivo.randomStatus();
		while(stato.equals(StatoDispositivo.ASSEGNATO)) {
			stato = StatoDispositivo.randomStatus();
		}
		return Device.builder()
				.tipoDispositivo(tipo)
				.statoDispositivo(stato)
				.build();
				
	}
}
