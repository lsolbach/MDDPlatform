package org.soulspace.base.domain.factory;

import org.soulspace.base.domain.validation.Validateable;
import org.soulspace.base.domain.validation.ValidationException;
import org.soulspace.base.domain.validation.ValidationResult;

public aspect FactoryAspect {

	declare parents : (@org.soulspace.annotation.domain.Factory *) implements Factory;

}
