package mx.products.products.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Siempre que no podamos controlar el error. Es un error inesperado que no sabemos de dónde viene.
// Los exception se definen en el throws y tienen que manejar un try-catch.
// RuntimeException lo podemos lanzar en cualquier lado.

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)	//Código de estado
public class GenerlServiceException extends RuntimeException{

	public GenerlServiceException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GenerlServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public GenerlServiceException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public GenerlServiceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public GenerlServiceException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}	
	
}
