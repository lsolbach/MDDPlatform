package org.soulspace.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.print.attribute.HashAttributeSet;

import junit.framework.TestCase;

public class CollectionUtilsTest extends TestCase {

	Long[] longArray = {0L, 1L, 2L, 3L, 4L, 5L};
	List<Long> longList = new ArrayList<Long>(Arrays.asList(longArray));
	Object[] mixedArray = {0, "1", 2L, 3, "4", 5L};
	List<? extends Object> mixedList = new ArrayList<Object>(Arrays.asList(mixedArray));

	Filter<Long> evenFilter = new Filter<Long>() {
		public boolean accept(Long t) {
			return t % 2 == 0;
		}
	};
	
	public void testFilterListByType() {
		List<String> filterList = CollectionUtils.filterList(mixedList, String.class);
		assertEquals("[1, 4]", filterList.toString());
	}

	public void testFilterListByFilter() {
		List<Long> filterList = CollectionUtils.filterList(longList, evenFilter);
		assertEquals("[0, 2, 4]", filterList.toString());
	}

	public void testFilterListInplace() {
		List<Long> testList = new ArrayList<Long>(longList);
		CollectionUtils.filterListInplace(testList, evenFilter);
		assertEquals("[0, 2, 4]", testList.toString());
	}

	public void testAddAllUniqueList() {
		Long[] extraArray = {4L, 5L, 6L};
		List<Long> extraList = new ArrayList<Long>(Arrays.asList(extraArray));
		List<Long> resultList = CollectionUtils.addAllUnique(longList, extraList);
		assertEquals("[0, 1, 2, 3, 4, 5, 6]", resultList.toString());
	}

	public void testAddAllUniqueElement() {
		List<Long> resultList = CollectionUtils.addAllUnique(longList, 5L);
		assertEquals("[0, 1, 2, 3, 4, 5]", resultList.toString());
		resultList= CollectionUtils.addAllUnique(longList, 6L);
		assertEquals("[0, 1, 2, 3, 4, 5, 6]", resultList.toString());
	}

	public void testAsList() {
		Set<Long> longSet = new HashSet<Long>(Arrays.asList(longArray));
		List<Long> resultList = CollectionUtils.asList(longSet, Long.class);
		assertTrue(resultList instanceof List);
		assertEquals("[0, 1, 2, 3, 4, 5]", resultList.toString());
	}

	public void testAsArrayList() {
		ArrayList<Long> resultList = CollectionUtils.asArrayList(longArray);
		assertTrue(resultList instanceof ArrayList);
		assertEquals("[0, 1, 2, 3, 4, 5]", resultList.toString());
	}

	public void testAsHashSet() {
		HashSet<Long> resultSet = CollectionUtils.asHashSet(longArray);
		assertTrue(resultSet instanceof HashSet);
		System.out.println(resultSet);
	}

	public void testAsStringArray() {
		String[] expectedArray = {"0", "1", "2", "3", "4", "5"};
		String[] convertedArray = CollectionUtils.asStringArray(longList);
		assertEquals(Arrays.toString(expectedArray), Arrays.toString(convertedArray));
	}

}
