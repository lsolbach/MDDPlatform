/*
 *  Copyright (c) Ludger Solbach. All rights reserved.
 *  The use and distribution terms for this software are covered by the
 *  Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 *  which can be found in the file license.txt at the root of this distribution.
 *  By using this software in any fashion, you are agreeing to be bound by
 *  the terms of this license.
 *  You must not remove this notice, or any other, from this software.
 */
package org.soulspace.base.infrastructure.spring.jpa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.orm.jpa.persistenceunit.MutablePersistenceUnitInfo;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitPostProcessor;

public class MergingPersistenceUnitPostProcessor implements
		PersistenceUnitPostProcessor {

	Map<String, Set<String>> puiClasses = new HashMap<String, Set<String>>();
	Map<String, Set<String>> puiMappingFiles = new HashMap<String, Set<String>>();

	public void postProcessPersistenceUnitInfo(MutablePersistenceUnitInfo pui) {
		System.out.println("Processing: " + pui.getPersistenceUnitRootUrl()
				+ " - " + pui.getPersistenceUnitName());
		Set<String> classes = puiClasses.get(pui.getPersistenceUnitName());

		if(classes == null) {
			classes = new HashSet<String>();
			puiClasses.put(pui.getPersistenceUnitName(), classes);
		}
		for(String className : classes) {
			if(!pui.getManagedClassNames().contains(className)) {
				pui.getManagedClassNames().add(className);
			}
		}
		classes.addAll(pui.getManagedClassNames());

		Set<String> mappingFiles = puiMappingFiles.get(pui.getPersistenceUnitName());
		if(mappingFiles == null) {
			mappingFiles = new HashSet<String>();
			puiMappingFiles.put(pui.getPersistenceUnitName(), mappingFiles);
			
		}
		for(String mappingFileName : mappingFiles) {
			if(!pui.getMappingFileNames().contains(mappingFileName)) {
				pui.getMappingFileNames().add(mappingFileName);
			}
		}
		mappingFiles.addAll(pui.getMappingFileNames());

		System.out.println("Managed classes: " + pui.getManagedClassNames());
		System.out.println("Mapping files: " + pui.getMappingFileNames());
	}
}
