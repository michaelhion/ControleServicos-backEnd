package com.api.controleservicos.exceptions;

public class NotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public NotFoundException(Long id) {
	    super("Serviço não encontrado id: " + id);
	  }
}
