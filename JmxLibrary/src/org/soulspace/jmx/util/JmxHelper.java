/*
 *  Copyright (c) Ludger Solbach. All rights reserved.
 *  The use and distribution terms for this software are covered by the
 *  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *  which can be found in the file license.txt at the root of this distribution.
 *  By using this software in any fashion, you are agreeing to be bound by
 *  the terms of this license.
 *  You must not remove this notice, or any other, from this software.
 */
package org.soulspace.jmx.util;

import java.lang.management.ManagementFactory;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import javax.management.StandardMBean;

public class JmxHelper {
	private static MBeanServer mbs;

	static {
		mbs = ManagementFactory.getPlatformMBeanServer();
	}
	
	@SuppressWarnings("unchecked")
	public static void register(Class iface, Object m, String key) {
		try {
			StandardMBean smb = new StandardMBean(m, iface);
			mbs.registerMBean(smb, new ObjectName(key + "=" + m.getClass().getSimpleName()));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
