package org.soulspace.base.domain.validation;

import java.util.ArrayList;
import java.util.List;

public interface ValidationResult {
	boolean isValid();
	Severity getSeverity();
	List<ValidationIssue> getValidationIssueList();
	void addValidationIssue(ValidationIssue issue);
	
}
