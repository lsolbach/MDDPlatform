package org.soulspace.base.domain.validation.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.soulspace.annotation.metadata.Mandatory;
import org.soulspace.annotation.metadata.Pattern;
import org.soulspace.annotation.metadata.Range;
import org.soulspace.annotation.metadata.Rules;
import org.soulspace.annotation.metadata.Size;
import org.soulspace.base.domain.aggregate.AggregateRoot;
import org.soulspace.base.domain.validation.Severity;
import org.soulspace.base.domain.validation.Validateable;
import org.soulspace.base.domain.validation.ValidationResult;

public class ValidatorImpl {

	public static ValidationResult validate(Validateable v) {
		ValidationResult vResult = new ValidationResultImpl();
		return validate(v, vResult);
	}

	public static ValidationResult validate(Validateable v, ValidationResult vResult) {
		// validate fields
		vResult = validateFields(v, vResult);

		// validate domain object
		if(v.getClass().isAnnotationPresent(Rules.class)) {
			Annotation a = v.getClass().getAnnotation(Rules.class);
			
			;
		}
		
		// validate aggegate
		if(v instanceof AggregateRoot) {
			;
		}
		
		return vResult;
	}

	@SuppressWarnings("unchecked")
	public static ValidationResult validateFields(Validateable v, ValidationResult result) {
		Map<String, Field> fieldMap = new HashMap<String, Field>();
		fieldMap = fillFieldMap(v.getClass(), fieldMap);
		for(String name : fieldMap.keySet()) {
			Field field = fieldMap.get(name);
			Class type = field.getType();
			Object value = null;
			// TODO check the reach of setAccessible
			field.setAccessible(true);
			try {
				value = field.get(v);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			if(field.isAnnotationPresent(Mandatory.class)) {
				if(!MandatoryValidatorImpl.isValid(value)) {
					result.addValidationIssue(new ValidationIssueImpl(field.getName(), Severity.ERROR, "Mandatory field not set."));
				}
			}
			if(value != null) {
				if(field.isAnnotationPresent(Size.class)) {
					Size size = field.getAnnotation(Size.class);
					if(!SizeValidatorImpl.isValid(type, value, size.min(), size.max())) {
						result.addValidationIssue(new ValidationIssueImpl(field.getName(), Severity.ERROR, "Size of field is invalid."));					
					}
				}
				if(field.isAnnotationPresent(Range.class)) {
					Range range = field.getAnnotation(Range.class);
					if(!RangeValidatorImpl.isValid(value, range.min(), range.max())) {
						result.addValidationIssue(new ValidationIssueImpl(field.getName(), Severity.ERROR, "Mandatory field not set."));					
					}
				}
				if(field.isAnnotationPresent(Pattern.class)) {
					Pattern pattern = field.getAnnotation(Pattern.class);
					if(!PatternValidatorImpl.isValid(value, pattern.pattern())) {
						result.addValidationIssue(new ValidationIssueImpl(field.getName(), Severity.ERROR, "Mandatory field not set."));					
					}
				}
			}
			field.setAccessible(true);
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
