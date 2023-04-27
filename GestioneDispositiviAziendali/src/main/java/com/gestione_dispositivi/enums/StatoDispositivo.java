package com.gestione_dispositivi.enums;

import java.util.Random;

public enum StatoDispositivo {
	DISPONIBILE,ASSEGNATO,MANUTENZIONE,DISMESSO;
	
	private static final Random random = new Random();
	
	public static StatoDispositivo randomStatus()  {
    	StatoDispositivo[] statuses = values();
        return statuses[random.nextInt(statuses.length)];
    }
}
