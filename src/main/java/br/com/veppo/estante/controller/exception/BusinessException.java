package br.com.veppo.estante.controller.exception;

public class BusinessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    public BusinessException() {}

    public BusinessException(String message) {
       super(message);
    }
}