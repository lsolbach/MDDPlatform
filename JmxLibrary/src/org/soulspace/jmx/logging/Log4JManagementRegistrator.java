/*
 *  Copyright (c) Ludger Solbach. All rights reserved.
 *  The use and distribution terms for this software are covered by the
 *  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *  which can be found in the file license.txt at the root of this distribution.
 *  By using this software in any fashion, you are agreeing to be bound by
 *  the terms of this license.
 *  You must not remove this notice, or any other, from this software.
 */
package org.soulspace.jmx.logging;

import java.lang.management.ManagementFactory;
import java.util.Enumeration;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.jmx.HierarchyDynamicMBean;
import org.apache.log4j.spi.LoggerRepository;

public class Log4JManagementRegistrator {

	@SuppressWarnings("unchecked")
	public static void registerLog4jMBeans() {
		// lookup the MBeanServer
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

		try {
			// create and Register the top level Log4J MBean
			HierarchyDynamicMBean hdm = new HierarchyDynamicMBean();
			ObjectName mbo = new ObjectName("log4j:hierarchy=default");
			mbs.registerMBean(hdm, mbo);

			// add the root logger to the Hierarchy MBean
			Logger rootLogger = Logger.getRootLogger();
			hdm.addLoggerMBean(rootLogger.getName());
 
			// get each logger from the Log4J Repository and add it to
			// the Hierarchy MBean created above.
			LoggerRepository r = LogManager.getLoggerRepository();
			Enumeration<Logger> e = r.getCurrentLoggers();
			while (e.hasMoreElements()) {
				Logger logger = e.nextElement();
				hdm.addLoggerMBean(logger.getName());
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
