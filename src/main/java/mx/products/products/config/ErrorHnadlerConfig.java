package mx.products.products.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import mx.products.products.exceptions.GenerlServiceException;
import mx.products.products.exceptions.NoDataFoundException;
import mx.products.products.exceptions.ValidateServiceException;
import mx.products.products.service.ProductService;
import mx.products.products.util.WrapperResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
public class ErrorHnadlerConfig extends ResponseEntityExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);
	
	@ExceptionHandler(Exception.class)//-->Este método se ejecuta cuando se propague a nivel de controller
	//una excepción de tipo Exception.	
	public ResponseEntity<?> all(Exception e, WebRequest request){
		
		LOGGER.error(e.getMessage(),e);
		WrapperResponse<?> responde = new WrapperResponse<>("Internal server error",null);
		//La línea así porque es un error que no sabemos de dónde viene.
		return new ResponseEntity<>(responde, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ValidateServiceException.class)
	public ResponseEntity<?> validateServiceException(ValidateServiceException e, WebRequest request){
		LOGGER.info(e.getMessage(),e);
		WrapperResponse<?> responde = new WrapperResponse<>(e.getMessage(),null);
		return new ResponseEntity<>(responde, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoDataFoundException.class)
	public ResponseEntity<?> noDataFoundException(NoDataFoundException e, WebRequest request){
		LOGGER.info(e.getMessage(),e);
		WrapperResponse<?> responde = new WrapperResponse<>(e.getMessage(),null);
		return new ResponseEntity<>(responde, HttpStatus.NO_CONTENT);
	}
	
	@ExceptionHandler(GenerlServiceException.class)
	public ResponseEntity<?> generalServiceException(GenerlServiceException e, WebRequest request){
		LOGGER.error(e.getMessage(),e);
		WrapperResponse<?> responde = new WrapperResponse<>("Internal server error",null);
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
