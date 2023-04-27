package com.gestione_dispositivi.enums;

import java.util.Random;

public enum TipoDispositivo {
	TABLET,SMARTPHONE,LAPTOP;
	
	private static final Random random = new Random();
	
	public static TipoDispositivo tipoRandom() {
		TipoDispositivo [] tipo = values();
		return tipo[random.nextInt(tipo.length)];
	}
}
