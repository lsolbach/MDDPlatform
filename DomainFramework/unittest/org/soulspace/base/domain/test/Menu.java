package org.soulspace.base.domain.test;

import java.util.ArrayList;
import java.util.List;

public class Menu extends MenuItem {

	List<MenuItem> itemList = new ArrayList<MenuItem>();
	
	public Menu(String id, String label) {
		super(id, label);
	}

	List<MenuItem> getMenuItemList() {
		return itemList;
	}
	
	Menu addMenuItem(MenuItem item) {
		itemList.add(item);
		return this;
	}
	
	Menu removeMenuItem(MenuItem item) {
		itemList.remove(item);
		return this;
	}
}
