package org.soulspace.base.domain.validation;

public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private ValidationResult result;
	
	public ValidationException(String message, Throwable cause, ValidationResult result) {
		super(message, cause);
		this.result = result;
	}

	public ValidationException(String message, ValidationResult result) {
		super(message);
		this.result = result;
	}

	public ValidationException(Throwable cause, ValidationResult result) {
		super(cause);
		this.result = result;
	}
	
	public ValidationResult getResult() {
		return result;
	}
	
}
