package org.soulspace.base.domain.validation.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.soulspace.annotation.metadata.Pattern;
import org.soulspace.annotation.metadata.Range;
import org.soulspace.annotation.metadata.Size;
import org.soulspace.base.domain.validation.Validateable;
import org.soulspace.base.domain.validation.ValidationResult;
import org.soulspace.base.domain.validation.Validator;

public class ValidatorImpl implements Validator {

	ValidationResult result = new ValidationResultImpl();	
	
	@Override
	public Class<? extends Validateable> getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ValidationResult validate(Validateable v) {
		validateFields(v);
		return result;
	}

	protected void validateFields(Validateable v) {
		Field[] fields = v.getClass().getFields();
		for(Field field : fields) {
			Class type = field.getType();
			Object value = null; //field.get(arg0);
			field.isAccessible();
			if(field.isAnnotationPresent(Size.class)) {
				Size size = field.getAnnotation(Size.class);
				SizeValidatorImpl.isValid(type, value, size.min(), size.max());
			}
			if(field.isAnnotationPresent(Range.class)) {
				Range range = field.getAnnotation(Range.class);
				// FIXME signatures/number conversions?
				//RangeValidatorImpl.isValid((value, range.min(), range.max());
			}
			if(field.isAnnotationPresent(Pattern.class)) {
				Pattern pattern = field.getAnnotation(Pattern.class);
				// FIXME signatures/number conversions?
				//PatternValidatorImpl.isValid(value, pattern.pattern());
			}
		}
	}	
}
