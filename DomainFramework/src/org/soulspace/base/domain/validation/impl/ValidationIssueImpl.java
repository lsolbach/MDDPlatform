package org.soulspace.base.domain.validation.impl;

import org.soulspace.base.domain.validation.Severity;
import org.soulspace.base.domain.validation.ValidationIssue;

public class ValidationIssueImpl implements ValidationIssue {

	private final String property;
	private final Severity severity;
	private final String message;
	
	public ValidationIssueImpl(String property, Severity severity,
			String message) {
		super();
		this.property = property;
		this.severity = severity;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public String getProperty() {
		return property;
	}

	public Severity getSeverity() {
		return severity;
	}

}
