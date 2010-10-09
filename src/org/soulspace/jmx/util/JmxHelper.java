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
