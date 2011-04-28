package org.soulspace.base.domain.object;

public abstract aspect AbstractLifeCycleAspect {

	pointcut rootCreated(DomainObject root);
	pointcut rootRead(DomainObject root);
	pointcut rootUpdated(DomainObject root);
	pointcut rootDeleted(DomainObject root);

	pointcut childCreated(DomainObject root, DomainObject child);
	pointcut childUpdated(DomainObject root, DomainObject child);
	pointcut childDeleted(DomainObject root, DomainObject child);

}
