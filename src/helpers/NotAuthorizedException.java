package helpers;

@SuppressWarnings("serial")
public class NotAuthorizedException extends Exception {

	public NotAuthorizedException() {
		super("No tiene permiso para acceder a esta página");
	}
	
}
