[
 :module "ArchitectureFramework"
 :project "org.soulspace.architecture"
 :project-lead "Ludger Solbach"
 :type :framework
 :version "0.2.0"
 :description "An architecture framework with abstractions and aspects"
 :vendor "soulspace.org"
 :plugins ["global" "dependencies" "aspectj" "junit" "package"]
 :dependencies [[["org.soulspace.architecture" "AspectLibrary" "0.2.0"] :aspect]
                [["org.soulspace.architecture" "AnnotationLibrary" "0.4.0"]]
                [["junit" "junit" "3.8.1"] :dev]]
 ]
