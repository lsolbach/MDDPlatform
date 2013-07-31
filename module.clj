[
 :module "DomainFramework"
 :project "org.soulspace.architecture"
 :project-lead "Ludger Solbach"
 :type :framework
 :version "0.6.0"
 :description "A domain framework with abstractions and aspects for domain driven design"
 :vendor "soulspace.org"
 :plugins ["global" "sdeps" "aspectj" "junit" "package"]
 :dependencies [[["org.soulspace.architecture" "AspectLibrary" "0.2.0"] :aspect]
                [["org.soulspace.architecture" "AnnotationLibrary" "0.4.0"]]
                [["org.soulspace.common" "JavaUtilLibrary" "0.3.0"]]
                [["log4j" "log4j" "1.2.15"] :dev]
                [["commons-logging" "commons-logging" "1.1.1"] :dev]
                [["com.thoughtworks.xstream" "xstream" "1.3.1"] :dev]
                [["junit" "junit" "3.8.1"] :dev]
                ]
 :log-level :info
 ]
