package org.soulspace.base.domain.validation;

public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private ValidationResult result;
	
	public ValidationException(ValidationResult result, String message, Throwable cause) {
		super(message, cause);
		this.result = result;
	}

	public ValidationException(ValidationResult result, String message) {
		super(message);
		this.result = result;
	}

	public ValidationException(ValidationResult result, Throwable cause) {
		super(cause);
		this.result = result;
	}
	
	public ValidationResult getResult() {
		return result;
	}
	
}
