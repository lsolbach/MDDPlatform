package org.soulspace.base.domain.validation;

import java.util.ArrayList;
import java.util.List;

public interface ValidationResult {
	int getResultCode();
	List<ValidationIssue> issueList = new ArrayList<ValidationIssue>();
}
