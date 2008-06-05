package org.soulspace.base.domain.validation;

public interface ValidationIssue {
	String getSeverity();
	String getProperty();
	String getMessage();
}
