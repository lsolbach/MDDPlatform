[
 :module "ArchitectureFramework"
 :project "org.soulspace.architecture"
 :project-lead "Ludger Solbach"
 :type :framework
 :version "0.2.1"
 :description "An architecture framework with abstractions and aspects"
 :plugins ["global"
           ["org.soulspace.baumeister/DependencyPlugin"]
           ["org.soulspace.baumeister/AspectJPlugin"]
           ["org.soulspace.baumeister/JUnitPlugin"]
           ["org.soulspace.baumeister/PackagePlugin"]]
 :dependencies [["org.soulspace.architecture/AspectLibrary, 0.2.0" :aspect]
                ["org.soulspace.architecture/AnnotationLibrary, 0.4.1"]
                ["junit/junit, 3.8.1" :dev]]
 ]
