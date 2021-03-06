[
 :module "JmxLibrary"
 :project "org.soulspace.architecture"
 :type :library
 :version "0.2.0"
 :description "A library of aspects for managing applications with the Java Management Extension (JMX)."
 :license ["Eclipse Public License 1.0" "http://www.eclipse.org/legal/epl-v10.html"]
 :provider "soulspace.org"
 :plugins ["global"
           ["org.soulspace.baumeister/AspectJPlugin"]
           ["org.soulspace.baumeister/JUnitPlugin"]
           ["org.soulspace.baumeister/PackagePlugin"]]
 :dependencies [["org.soulspace.architecture/AspectLibrary, 0.2.0" :aspect]
                ["org.soulspace.architecture/AnnotationLibrary, 0.4.0"]
                ["log4j/log4j, 1.2.15" :dev]
                ["junit/junit, 3.8.1" :dev]]
 ]
