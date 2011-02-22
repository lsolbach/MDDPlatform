package org.soulspace.annotation.metadata;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
public @interface Range {
	long min() default Long.MIN_VALUE;
	long max() default Long.MAX_VALUE;
}
