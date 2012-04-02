package org.soulspace.architecture;

public aspect FrameworkDefinitions {

	// Java

	public static pointcut inSql() :
		within(java.sql..*)
		||
		within(javax.sql..*)
		;
	
	public static pointcut callingSql() :
		call(* java.sql..*.*(..))
		||
		call(* javax.sql..*.*(..))
		;
	
	public static pointcut inServletApi() :
		within(javax.servlet..*);
	
	public static pointcut callingServletApi() :
		call(* javax.servlet..*.*(..));
	
	
	// Common frameworks
	public static pointcut inSpringFramework() :
		within(org.springframework..*);
	
	public static pointcut callingSpringFramework() :
		call(* org.springframework..*.*(..));
	
	public static pointcut inHibernate() :
		within(org.hibernate..*);
	
	public static pointcut callingHibernate() :
		call(* org.hibernate..*.*(..));
		
	public static pointcut inLog4J() :
		within(org.apache.log4j..*);
	
	public static pointcut callingLog4J() :
		call(* org.apache.log4j..*.*(..));
	
	// TODO check commons logging package name
	public static pointcut inCommonsLogging() :
		within(org.apache.commons.logging..*);
	
	public static pointcut callingCommonsLogging() :
		call(* org.apache.commons.logging..*.*(..));
	
	// TODO add slf4j
	
	
	// Test frameworks
	public static pointcut inJUnit() :
		within(junit.framework..*);
		
}
