package org.soulspace.base.domain.validation;

public interface ValidationIssue {
	Severity getSeverity();
	String getProperty();
	String getMessage();
}
