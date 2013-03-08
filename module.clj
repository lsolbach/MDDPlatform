[
 :name "AspectLibrary"
 :project "org.soulspace.architecture"
 :project-lead "Ludger Solbach"
 :type "library"
 :version "0.2.0"
 :description "An aspect library containing some useful general purpose aspects"
 :vendor "soulspace.org"
 :plugins ["global" "deps" "aspectj" "junit" "package"]
 :dependencies [["org.soulspace.architecture" "AnnotationLibrary" "0.4.0"]
                ["org.aspectj" "aspectjrt" "1.6.11"]
                ["log4j" "log4j" "1.2.15" "dev"]
                ["commons-logging" "commons-logging" "1.1.1" "dev"]
                ["junit" "junit" "3.8.1" "dev"]]
 ]