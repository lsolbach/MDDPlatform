package org.soulspace.base.domain.test;

import org.soulspace.base.infrastructure.storage.Storage;
import org.soulspace.base.infrastructure.storage.impl.XStreamStorageImpl;

import junit.framework.TestCase;

public class StorageTest extends TestCase {

	Storage storage;
	
	public StorageTest() {
		super();
		storage = new XStreamStorageImpl();
		((XStreamStorageImpl) storage).registerClass("Menu", Menu.class, Menu.class);
		((XStreamStorageImpl) storage).registerClass("MenuItem", MenuItem.class, MenuItem.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testWrite() {
		Menu menu =
			new Menu("100", "Main")
			.addMenuItem(
				new Menu("110", "File")
				.addMenuItem(new MenuItem("111", "New"))
				.addMenuItem(new MenuItem("112", "Open"))
				.addMenuItem(new MenuItem("113", "Save")))
			.addMenuItem(new Menu("120", "Edit")
				.addMenuItem(new MenuItem("121", "Cut")));
		storage.write(menu, "menu1");
	}
	
}
