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
		r = r.replaceAll("&amp;", "&");
		r = r.replaceAll("&gt;", ">");
		r = r.replaceAll("&lt;", "<");
		r = r.replaceAll("&quot;", "\"");
		r = r.replaceAll("&apos;", "'");
		return r;
	}
}
