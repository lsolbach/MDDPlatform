[
 :module "JpaLibrary"
 :project "org.soulspace.architecture"
 :type :library
 :version "0.3.0"
 :description "A helper library for merging JPA persistence units with Spring."
 :license ["Eclipse Public License 1.0" "http://www.eclipse.org/legal/epl-v10.html"]
 :provider "soulspace.org"
 :plugins ["global"
           ["org.soulspace.baumeister/JavaPlugin"]
           ["org.soulspace.baumeister/PackagePlugin"]]
 :dependencies [["org.springframework/spring-orm, 3.0.5.RELEASE"]]
 ]
