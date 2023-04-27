
//MANDA INDIETRO AL CLIENT IL TIPO DI ERRORE CHE E' STATO SOLLEVATO
package com.auth.exception;

import org.springframework.http.HttpStatus;

public class MyAPIException extends RuntimeException {
	
	private HttpStatus status;
    private String message;
    
    public MyAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public MyAPIException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }
    
    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
