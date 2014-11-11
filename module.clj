[
 :module "AspectLibrary"
 :project "org.soulspace.architecture"
 :project-lead "Ludger Solbach"
 :type :library
 :version "0.2.0"
 :description "An aspect library containing some useful general purpose aspects"
 :license ["Eclipse Public License 1.0" "http://www.eclipse.org/legal/epl-v10.html"]
 :provider "soulspace.org"
 :plugins ["global"
           ["org.soulspace.baumeister/DependencyPlugin"]
           ["org.soulspace.baumeister/AspectJPlugin"]
           ["org.soulspace.baumeister/JUnitPlugin"]
           ["org.soulspace.baumeister/PackagePlugin"]]
 :dependencies [["org.soulspace.architecture/AnnotationLibrary, 0.4.0"]
                ["org.aspectj/aspectjrt, 1.6.11"]
                ["log4j/log4j, 1.2.15" :dev]
                ["commons-logging/commons-logging, 1.1.1" :dev]
                ["junit/junit, 3.8.1" :dev]]
 ]