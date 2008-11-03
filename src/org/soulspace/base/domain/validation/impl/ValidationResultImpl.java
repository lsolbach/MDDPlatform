package org.soulspace.base.domain.validation.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.soulspace.base.domain.validation.Severity;
import org.soulspace.base.domain.validation.ValidationIssue;
import org.soulspace.base.domain.validation.ValidationResult;

public class ValidationResultImpl implements ValidationResult {
	private List<ValidationIssue> issueList = new ArrayList<ValidationIssue>();
	private Severity maxSeverity;
	
	public void addValidationIssue(ValidationIssue issue) {
		if(issue.getSeverity().compareTo(maxSeverity) > 0) {
			maxSeverity = issue.getSeverity();
		}
		issueList.add(issue);
	}
	
	public Severity getSeverity() {
		return maxSeverity;
	}
	
	public List<ValidationIssue> getValidationIssueList() {
		return Collections.unmodifiableList(issueList);
	}
	
	public boolean isValid() {
		return !maxSeverity.equals(Severity.ERROR);
	}	
	
}
