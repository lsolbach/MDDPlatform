package org.soulspace.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class CollectionUtils {

	/**
	 * Filter the list. The new list contains only objects of the given type.
	 * @param <T>
	 * @param list
	 * @param type
	 * @return
	 */
	public static <T> List<T> filterList(List<?> list, Class<T> type) {
		List<T> filteredList = new ArrayList<T>();
		for(Object o : list) {
			if(type.isInstance(o)) {
				filteredList.add(type.cast(o));
			}
		}
		return filteredList;
	}

	/**
	 * Filter the list. The new list contains only objects accepted by the given filter.
	 * @param <T>
	 * @param list
	 * @param filter
	 * @return
	 */
	public static <T> List<T> filterList(List<T> list, Filter<T> filter) {
		List<T> filteredList = new ArrayList<T>();
		for(T t : list) {
			if(filter.accept(t)) {
				filteredList.add(t);
			}
		}
		return filteredList;
	}
	
	/**
	 * Filter the list, so the list contains only objects accepted by the given filter
	 * @param <T>
	 * @param list
	 * @param filter
	 * @return
	 */
	public static <T> List<T> filterListInplace(List<T> list, Filter<T> filter) {
		Iterator<T> it = list.iterator();
		while (it.hasNext()) {
			if(!filter.accept(it.next())) {
				it.remove();
			}			
		}
		return list;
	}
	
	/**
	 * Convert the collection to a typed list of given type. 
	 * @param <T>
	 * @param collection
	 * @param type
	 * @return
	 */
	public static <T> List<T> asList(Collection<?> collection, Class<T> type) {
		List<T> list = new ArrayList<T>();
		for(Object o : collection) {
			if(type.isInstance(o)) {
				list.add(type.cast(o));
			}			
		}
		return list;
	}
	
	/**
	 * Convert the collection to a string array. The method toString() is called on every entry.
	 * @param coll
	 * @return string array
	 */
	public static String[] asStringArray(Collection coll) {
		int i = 0;
		String[] strings = new String[coll.size()];
		Iterator it = coll.iterator();
		while(it.hasNext()) {    
			strings[i++] = it.next().toString();
		}
		return strings;
	}
}
