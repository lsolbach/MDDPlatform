package org.soulspace.base.domain.validation.impl;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

import org.soulspace.annotation.metadata.Size;
import org.soulspace.base.domain.validation.FieldValidator;
import org.soulspace.base.domain.validation.Severity;
import org.soulspace.base.domain.validation.ValidationResult;

public class SizeValidatorImpl implements FieldValidator {

	public static boolean isValidString(String value, int min, int max) {
		if(value == null) {
			return true;
		}
		if(value.length() >= min && value.length() <= max) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isValidArray(Object[] values, int min, int max) {
		if(values == null) {
			return true;
		}
		if(values.length >= min && values.length <= max) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isValidCollection(Collection<? extends Object> values, int min, int max) {
		if(values == null) {
			return true;
		}
		if(values.size() >= min && values.size() <= max) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isValidMap(Map<? extends Object, ? extends Object> values, int min, int max) {
		if(values == null) {
			return true;
		}
		if(values.size() >= min && values.size() <= max) {
			return true;
		} else {
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> boolean isValid(Class<T> type, T value, int min, int max) {
		if(type.equals(String.class)) {
			System.out.println("validating size of string value");
			return isValidString((String) value, min, max);
		} else if(type.equals(Array.class)) {
			System.out.println("validating size of array");
			return isValidArray((Object[]) value, min, max);			
		} else if(type.equals(Collection.class)) {
			System.out.println("validating size of collection");
			return isValidCollection((Collection<? extends Object>) value, min, max);			
		} else if(type.equals(Map.class)) {
			System.out.println("validating size of map");
			return isValidMap((Map<? extends Object, ? extends Object>) value, min, max);
		} else {
			System.out.println(type.getName());
			return true;
		}
	}
	
	@SuppressWarnings("unchecked")
	public ValidationResult validateField(ValidationResult result, Field field, Object value) {
		if(field.isAnnotationPresent(Size.class) && value != null) {
			// validate size
			Size size = field.getAnnotation(Size.class);
			int min = size.min();
			int max = size.max();
			Class<? extends Object> type = field.getType();
			if(type.equals(String.class)) {
				// System.out.println("validating size of string value");
				String stringValue = (String) value;
				if(stringValue.length() < min) {
					result.addValidationIssue(
							new ValidationIssueImpl(
									field.getName(),
									Severity.ERROR,
									"The string must be at least " + min + " characters long!"));
				}
				if(stringValue.length() > max) {
					result.addValidationIssue(
							new ValidationIssueImpl(
									field.getName(),
									Severity.ERROR,
									"The string has a maximum lenght of " + max + " characters!"));
				}
			} else if(type.equals(Array.class)) {
				// System.out.println("validating size of array");
				Object[] array = (Object[]) value;
				if(array.length < min) {
					result.addValidationIssue(
							new ValidationIssueImpl(
									field.getName(),
									Severity.ERROR,
									"The array must contain at least " + min + " elements!"));
				}
				if(array.length > max) {
					result.addValidationIssue(
							new ValidationIssueImpl(
									field.getName(),
									Severity.ERROR,
									"The array must contain at most " + max + " elements!"));
				}
			} else if(type.equals(Collection.class)) {
				// System.out.println("validating size of collection");
				Collection<? extends Object> collValue = (Collection<? extends Object>) value;
				if(collValue.size() < min) {
					result.addValidationIssue(
							new ValidationIssueImpl(
									field.getName(),
									Severity.ERROR,
									"The collection must contain at least " + min + " elements!"));
				}
				if(collValue.size() > max) {
					result.addValidationIssue(
							new ValidationIssueImpl(
									field.getName(),
									Severity.ERROR,
									"The collection must contain at most " + max + " elements!"));
				}
			} else if(type.equals(Map.class)) {
				// System.out.println("validating size of map");
				Map<? extends Object, ? extends Object> mapValue = (Map<? extends Object, ? extends Object>) value;
				if(mapValue.size() < min) {
					result.addValidationIssue(
							new ValidationIssueImpl(
									field.getName(),
									Severity.ERROR,
									"The map must contain at least " + min + " elements!"));
				}
				if(mapValue.size() > max) {
					result.addValidationIssue(
							new ValidationIssueImpl(
									field.getName(),
									Severity.ERROR,
									"The map must contain at most " + max + " elements!"));
				}
			} else {
				// System.out.println(type.getName());
			}
		}
		return result;
	}
}