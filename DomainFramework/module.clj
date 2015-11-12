[
 :module "DomainFramework"
 :project "org.soulspace.architecture"
 :project-lead "Ludger Solbach"
 :type :framework
 :version "0.6.1"
 :description "A domain framework with abstractions and aspects for domain driven design"
 :plugins ["global"
           ["org.soulspace.baumeister/AspectJPlugin"]
           ["org.soulspace.baumeister/JUnitPlugin"]
           ["org.soulspace.baumeister/PackagePlugin"]]
 :dependencies [["org.soulspace.architecture/AspectLibrary, 0.2.0" :aspect]
                ["org.soulspace.architecture/AnnotationLibrary, 0.4.1"]
                ["org.soulspace.common/JavaUtilLibrary, 0.3.1"]
                ["log4j/log4j, 1.2.15" :dev]
                ["commons-logging/commons-logging, 1.1.1" :dev]
                ["com.thoughtworks.xstream/xstream, 1.3.1" :dev]
                ["junit/junit, 3.8.1" :dev]]
 ]
