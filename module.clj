[
 :module "ArchitectureFramework"
 :project "org.soulspace.architecture"
 :project-lead "Ludger Solbach"
 :type "framework"
 :version "0.2.0"
 :description "An architecture framework with abstractions and aspects"
 :vendor "soulspace.org"
 :plugins ["global" "sdeps" "aspectj" "junit" "package"]
 :dependencies [[["org.soulspace.architecture" "AspectLibrary" "0.2.0"] "aspect"]
                [["org.soulspace.architecture" "AnnotationLibrary" "0.3.1"]]
                [["org.aspectj" "aspectjrt" "1.6.11"]] ; transitive AspectLibrary
                [["junit" "junit" "3.8.1"] "dev"]]
 ]
