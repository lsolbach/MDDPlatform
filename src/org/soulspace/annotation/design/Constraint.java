package org.soulspace.annotation.design;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD})
public @interface Constraint {
	long minLength() default 0;
	long maxLength() default Long.MAX_VALUE;
	long minValue() default Long.MIN_VALUE;
	long maxValue() default Long.MAX_VALUE;
	String pattern() default "";
	boolean notNull() default false;
}
