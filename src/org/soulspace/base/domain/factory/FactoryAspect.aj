package org.soulspace.base.domain.factory;

public aspect FactoryAspect {

	declare parents : (@org.soulspace.annotation.common.Factory *) implements Factory;

}
