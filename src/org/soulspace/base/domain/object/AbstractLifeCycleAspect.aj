package org.soulspace.base.domain.object;

import org.soulspace.base.domain.repository.Repository;

public abstract aspect AbstractLifeCycleAspect {

	pointcut rootCreated(DomainObject root) :
		execution(* Repository+.add*(DomainObject+))
		&& !within(AbstractLifeCycleAspect+)
		&& args(root)
		;
	
	pointcut rootRead() :
		// FIXME handle collections
		execution(DomainObject+ Repository+.get*(..))
		&& !within(AbstractLifeCycleAspect+)
		;
	
	pointcut rootUpdated(DomainObject root) :
		execution(* Repository+.update*(DomainObject+))
		&& !within(AbstractLifeCycleAspect+)
		&& args(root)
		;
	
	pointcut rootDeleted(DomainObject root) :
		execution(* Repository+.delete*(DomainObject+))
		&& !within(AbstractLifeCycleAspect+)
		&& args(root)
		;

	pointcut childCreated(DomainObject root, DomainObject child) // TODO
		;

	pointcut childUpdated(DomainObject root, DomainObject child) // TODO
		;

	pointcut childAdded(DomainObject root, DomainObject child) // TODO
		;
	
	pointcut childRemoved(DomainObject root, DomainObject child) // TODO
		;
	
	pointcut childDeleted(DomainObject root, DomainObject child) // TODO
		;

}
