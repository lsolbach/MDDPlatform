[
 :module "JavaUtilLibrary"
 :project "org.soulspace.common"
 :project-lead "Ludger Solbach"
 :provider "soulspace.org"
 :inceptionYear 2003
 :type :library
 :version "0.3.1"
 :description "The JavaUtilLibrary is a library of utility classes with pure java convenience methods."
 :license ["Eclipse Public License 1.0" "http://www.eclipse.org/legal/epl-v10.html"]
 :plugins ["global"
           ["org.soulspace.baumeister/JavaPlugin"]
           ["org.soulspace.baumeister/JUnitPlugin"]
           ["org.soulspace.baumeister/PackagePlugin"]]
 :dependencies [["junit/junit, 3.8.1" :dev]]
 :java-javadoc-windowtitle "${module} ${version}"
 :java-javadoc-doctitle "${module} ${version}"
 :java-javadoc-header "<FONT CLASS=\"NavBarFont1\">${module} ${version}</FONT>"
 :java-javadoc-footer "<FONT CLASS=\"NavBarFont1\">&copy; 2003-2015 Ludger Solbach</FONT>"
 ]
