package org.soulspace.base.domain.validation.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.soulspace.annotation.metadata.Mandatory;
import org.soulspace.annotation.metadata.Pattern;
import org.soulspace.annotation.metadata.Range;
import org.soulspace.annotation.metadata.Rules;
import org.soulspace.annotation.metadata.Size;
import org.soulspace.base.domain.aggregate.AggregateRoot;
import org.soulspace.base.domain.validation.FieldValidator;
import org.soulspace.base.domain.validation.Severity;
import org.soulspace.base.domain.validation.Validatable;
import org.soulspace.base.domain.validation.ValidationResult;

public class ValidatorImpl {

	// TODO don't use static fields/initalizers
	public static List<FieldValidator> fieldValidatorList
		= new ArrayList<FieldValidator>();

	static {
		fieldValidatorList.add(new MandatoryValidatorImpl());
		fieldValidatorList.add(new RangeValidatorImpl());
		fieldValidatorList.add(new SizeValidatorImpl());
		fieldValidatorList.add(new PatternValidatorImpl());
	}
	
	public static ValidationResult validate(Validatable v) {
		System.out.println("validating...");
		ValidationResult vResult = new ValidationResultImpl();
		return validate(v, vResult);
	}

	public static ValidationResult validate(Validatable v, ValidationResult vResult) {
		// validate fields
		vResult = validateFields(v, vResult);

		// validate domain object
		if(v.getClass().isAnnotationPresent(Rules.class)) {
			Rules rules = v.getClass().getAnnotation(Rules.class);
			rules.validator();
		}
		
		// validate aggegate
		if(v instanceof AggregateRoot) {
			;
		}
		
		return vResult;
	}

	@SuppressWarnings("unchecked")
	public static ValidationResult validateFields(Validatable v, ValidationResult result) {
		Map<String, Field> fieldMap = new HashMap<String, Field>();
		fieldMap = fillFieldMap(v.getClass(), fieldMap);
		for(String name : fieldMap.keySet()) {
			Field field = fieldMap.get(name);
			Object value = null;
			// TODO check the reach of setAccessible
			boolean isAccessible = field.isAccessible();
			field.setAccessible(true);
			try {
				value = field.get(v);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			for(FieldValidator fieldValidator : fieldValidatorList) {
				result = fieldValidator.validateField(result, field, value);
			}
//			if(field.isAnnotationPresent(Mandatory.class)) {
//				if(!MandatoryValidatorImpl.isValid(value)) {
//					result.addValidationIssue(new ValidationIssueImpl(field.getName(), Severity.ERROR, "Mandatory field not set."));
//				}
//			}
//			if(value != null) {
//				if(field.isAnnotationPresent(Size.class)) {
//					Size size = field.getAnnotation(Size.class);
//					if(!SizeValidatorImpl.isValid(field.getName(), type, value, size.min(), size.max())) {
//						result.addValidationIssue(new ValidationIssueImpl(field.getName(), Severity.ERROR, "Size of field is invalid."));					
//					}
//				}
//				if(field.isAnnotationPresent(Range.class)) {
//					Range range = field.getAnnotation(Range.class);
//					if(!RangeValidatorImpl.isValid(value, range.min(), range.max())) {
//						result.addValidationIssue(new ValidationIssueImpl(field.getName(), Severity.ERROR, "Mandatory field not set."));					
//					}
//				}
//				if(field.isAnnotationPresent(Pattern.class)) {
//					Pattern pattern = field.getAnnotation(Pattern.class);
//					if(!PatternValidatorImpl.isValid(value, pattern.pattern())) {
//						result.addValidationIssue(new ValidationIssueImpl(field.getName(), Severity.ERROR, "Mandatory field not set."));					
//					}
//				}
//			}
			field.setAccessible(isAccessible);
		}
		return result;
	}
	
	static Map<String, Field> fillFieldMap(Class<?> c, Map<String, Field> fieldMap) {
		if(c.getSuperclass() != null) {
			fieldMap = fillFieldMap(c.getSuperclass(), fieldMap);
		}
		fieldMap = mapFieldArray(c.getDeclaredFields(), fieldMap);
		return fieldMap;
	}
	
	static Map<String, Field> mapFieldArray(Field[] fields, Map<String, Field> fieldMap) {
		for(Field field : fields) {
			fieldMap.put(field.getName(), field);
		}
		return fieldMap;
	}
}
