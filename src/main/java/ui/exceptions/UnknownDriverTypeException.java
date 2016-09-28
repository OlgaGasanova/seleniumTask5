package ui.exceptions;

public class UnknownDriverTypeException extends RuntimeException {
	
	private static final long serialVersionUID = -6087441774720861978L;

	public UnknownDriverTypeException(String message){
		super(message);
	}
}
