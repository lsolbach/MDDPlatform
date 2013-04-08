package org.soulspace.architecture;

public aspect FrameworkDefinitions {

	//
	// Java SE
	//
	public static pointcut inAwt() :
		within(javax.awt..*);

	public static pointcut awtCall() :
		call(* java.awt..*.*(..));
	
	public static pointcut inSwing() :
		within(javax.swing..*);

	public static pointcut swingCall() :
		call(* javax.swing..*.*(..));
	
	// 
	// Java EE
	// 
	public static pointcut inServletApi() :
		within(javax.servlet..*);
	
	public static pointcut servletApiCall() :
		call(* javax.servlet..*.*(..));
	
	
	//
	// Persistence
	//

	// JDBC
	public static pointcut inSql() :
		within(java.sql..*)
		||
		within(javax.sql..*)
		;
	
	public static pointcut sqlCall() :
		call(* java.sql..*.*(..))
		||
		call(* javax.sql..*.*(..))
		;
	
	// Hibernate
	public static pointcut inHibernate() :
		within(org.hibernate..*);
	
	public static pointcut hibernateCall() :
		call(* org.hibernate..*.*(..));
		
	//
	// Logging
	//
	
	// Log4j
	public static pointcut inLog4J() :
		within(org.apache.log4j..*);
	
	public static pointcut log4JCall() :
		call(* org.apache.log4j..*.*(..));
	
	// apache commons logging
	public static pointcut inCommonsLogging() :
		within(org.apache.commons.logging..*);
	
	public static pointcut commonsLoggingCall() :
		call(* org.apache.commons.logging..*.*(..));
	
	// slf4j
	public static pointcut inSlf4J() :
		within(org.slf4j..*);
	
	public static pointcut slf4JCall() :
		call(* org.slf4j..*.*(..));
	
	// java.util.logging
	public static pointcut inJul() :
		within(java.util.logging..*);
	
	public static pointcut julCall() :
		call(* java.util.logging..*.*(..));
	
	// any logging 
	public static pointcut inLogging() :
		inCommonsLogging() || inSlf4J() || inLog4J() || inJul();
	
	public static pointcut loggingCall() :
		commonsLoggingCall() || slf4JCall() || log4JCall() || julCall();
	
	//
	// Testing
	//
	
	// JUnit
	public static pointcut inJUnit() :
		within(junit.framework..*);


	//
	// Dependency Injection
	
	// Spring Framework
	public static pointcut inSpringFramework() :
		within(org.springframework..*);
	
	public static pointcut springFrameworkCall() :
		call(* org.springframework..*.*(..));
	

}
