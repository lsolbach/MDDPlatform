package org.soulspace.base.domain.object;

public abstract aspect AbstractChangeDetectionAspect {

	pointcut domainObjectSetter(DomainObject obj, Object arg) :
		execution(* DomainObject+.set*(Object, ..))
		&& target(obj)
		&& args(arg)
		;

	pointcut domainObjectCollectionChange(DomainObject obj, Object arg) :
		(
			execution(* DomainObject+.add*(Object, ..))
			||
			execution(* DomainObject+.remove*(Object, ..))
		)
		&& target(obj)
		&& args(arg)
		;

	public abstract pointcut domainObjectChange(DomainObject obj, Object arg);
	
}
