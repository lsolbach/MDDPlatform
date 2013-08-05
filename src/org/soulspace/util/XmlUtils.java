/*
 *  Copyright (c) Ludger Solbach. All rights reserved.
 *  The use and distribution terms for this software are covered by the
 *  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *  which can be found in the file license.txt at the root of this distribution.
 *  By using this software in any fashion, you are agreeing to be bound by
 *  the terms of this license.
 *  You must not remove this notice, or any other, from this software.
 */
package org.soulspace.util;

public class XmlUtils {

	public static String encode(String s) {
		if(s == null || s.equals("")) {
			return "";
		}
		String r = s;
		r = r.replaceAll("&", "&amp;");
		r = r.replaceAll(">", "&gt;");
		r = r.replaceAll("<", "&lt;");
		r = r.replaceAll("\"", "&quot;");
		r = r.replaceAll("'", "&apos;");
		return r;
	}
	
	public static String decode(String s) {
		if(s == null || s.equals("")) {
			return "";
		}
		String r = s;
		r = r.replaceAll("&apos;", "'");
		r = r.replaceAll("&quot;", "\"");
		r = r.replaceAll("&lt;", "<");
		r = r.replaceAll("&gt;", ">");
		r = r.replaceAll("&amp;", "&");
		return r;
	}
}
