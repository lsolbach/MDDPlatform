package org.soulspace.aop.util;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.FieldSignature;
import org.aspectj.lang.reflect.MethodSignature;

public class AspectHelper {

	public static String renderJoinPoint(JoinPoint jp) {
		Signature sig = jp.getSignature();
		return sig.getDeclaringType().getSimpleName() + "." + sig.getName();
	}
	
	public static String renderJoinPointWithArgs(JoinPoint jp) {
		Signature sig = jp.getSignature();
		return sig.getDeclaringType().getSimpleName() + "." + sig.getName() + renderArgs(jp);
	}

	public static String renderArgs(JoinPoint jp) {
		Object[] args = jp.getArgs();
		if(args == null || args.length == 0) {
			return "()";
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append("(");
			sb.append(renderArg(args[0]));
			for(int i = 1; i < args.length; i++) {
				sb.append(", ");
				sb.append(renderArg(args[i]));
			}
			sb.append(")");
			return sb.toString();
		}
	}

	public static String renderArg(Object arg) {
		if(arg == null) {
			return "[null]";
		} else if(arg instanceof String) {
			return "\"" + arg + "\"";
		}
		return arg.toString();
	}
	
	public static String getFieldName(JoinPoint jp) {
		Signature sig = jp.getSignature();
		if(sig instanceof MethodSignature) {
			MethodSignature mSig = (MethodSignature) sig;
			return getFieldNameFromMethodName(mSig.getName());
		} else if(sig instanceof FieldSignature) {
			FieldSignature fSig = (FieldSignature) sig;
			return fSig.getField().getName();
		}
		return "";
	}

	public static String getFieldNameFromMethodName(String methodName) {
		String name = "";
		if(methodName.startsWith("get") || methodName.startsWith("set")) {
			name = firstLower(methodName.substring(3));
		} else if(methodName.startsWith("is")) {
			name = firstLower(methodName.substring(2));
		}		
		return name;
	}
	
	public static String getGetMethodName(Field field) {
		return "get" + firstUpper(field.getName());
	}
	
	public static String getSetMethodName(Field field) {
		return "set" + firstUpper(field.getName());
	}
	
	public static String getAddMethodName(Field field) {
		String fieldName = field.getName();
		if(field.getType().isAssignableFrom(List.class)) {
			return "add" + firstUpper(fieldName.substring(0, fieldName.length() - 4));			
		} else if(field.getType().isAssignableFrom(Map.class)
				|| field.getType().isAssignableFrom(Set.class)) {
			return "add" + firstUpper(fieldName.substring(0, fieldName.length() - 3));			
		} else if(field.getType().isAssignableFrom(Collection.class)) {
			return "add" + firstUpper(fieldName.substring(0, fieldName.length() - 10));			
		} else {
			return "add" + firstUpper(fieldName);
		}
	}
	
	public static String getRemoveMethodName(Field field) {
		String fieldName = field.getName();
		if(field.getType().isAssignableFrom(List.class)) {
			return "remove" + firstUpper(fieldName.substring(0, fieldName.length() - 4));			
		} else if(field.getType().isAssignableFrom(Map.class)
				|| field.getType().isAssignableFrom(Set.class)) {
			return "remove" + firstUpper(fieldName.substring(0, fieldName.length() - 3));			
		} else if(field.getType().isAssignableFrom(Collection.class)) {
			return "remove" + firstUpper(fieldName.substring(0, fieldName.length() - 10));			
		} else {
			return "remove" + firstUpper(fieldName);
		}
	}
	
	public static String getGetMethodName(String fieldName) {
		return "get" + firstUpper(fieldName);
	}
	
	public static String getSetMethodName(String fieldName) {
		return "set" + firstUpper(fieldName);
	}
	
	public static String getAddMethodName(String fieldName) {
		if(fieldName.endsWith("List")) {
			return "add" + firstUpper(fieldName.substring(0, fieldName.length() - 4));
		} else if(fieldName.endsWith("Map") || fieldName.endsWith("Set")) {
			return "add" + firstUpper(fieldName.substring(0, fieldName.length() - 3));
		} else if(fieldName.endsWith("Collection")) {
			return "add" + firstUpper(fieldName.substring(0, fieldName.length() - 10));
		} else {
			return "add" + firstUpper(fieldName);
		}
	}
	
	public static String getRemoveMethodName(String fieldName) {
		if(fieldName.endsWith("List")) {
			return "remove" + firstUpper(fieldName.substring(0, fieldName.length() - 4));
		} else if(fieldName.endsWith("Map") || fieldName.endsWith("Set")) {
			return "remove" + firstUpper(fieldName.substring(0, fieldName.length() - 3));
		} else if(fieldName.endsWith("Collection")) {
			return "remove" + firstUpper(fieldName.substring(0, fieldName.length() - 10));
		} else {
			return "remove" + firstUpper(fieldName);
		}
	}

	public static Object getProperty(Object obj, String name) {
		Object result = null;
		Field field;
		try {
			field = obj.getClass().getField(name);
			if(!field.isAccessible()) {
				field.setAccessible(true);
				result = field.get(obj);			
				field.setAccessible(false);
			} else {
				result = field.get(obj);
			}
		} catch (Exception e) {
			// FIXME handle
			e.printStackTrace();
		}		
		return result;
	}
	
	public static void setProperty(Object obj, String name, Object value) {
		try {
			Field field = obj.getClass().getField(name);
			if(!field.isAccessible()) {
				field.setAccessible(true);
				field.set(obj, value);
				field.setAccessible(false);
			} else {
				field.set(obj, value);
			}
		} catch (Exception e) {
			// FIXME handle
			e.printStackTrace();
		}
	}
	
	public static String firstLower(String name) {
		return name.substring(0, 1).toLowerCase() + name.substring(1);
	}

	public static String firstUpper(String name) {
		return name.substring(0, 1).toUpperCase() + name.substring(1);
	}
}
