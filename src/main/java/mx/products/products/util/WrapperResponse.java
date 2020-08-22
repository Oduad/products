package mx.products.products.util;

public class WrapperResponse<T> {

	private String message;
	private T body;
	private boolean ok;
	
	public WrapperResponse(String message, T body) {
		super();
		this.message = message;
		this.body = body;
		//this.ok = ok;
	}
	
	public WrapperResponse() {
		
	}
	
	public String getMessage() {
		return message;
	}
	public T getBody() {
		return body;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setBody(T body) {
		this.body = body;
	}
	
	/*public boolean getOk() {
		return ok;
	}
	public void setOk(Boolean ok) {
		this.ok = ok;
	}*/
	
}
