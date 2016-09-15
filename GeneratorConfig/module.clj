[
 :module "GeneratorConfig"
 :project "org.soulspace.modelling"
 :type :data
 :version "1.0.3"
 :description "Profiles, templates and configurations for model driven software development"
 :author "Ludger Solbach"
 :provider "soulspace.org"
 :plugins [["org.soulspace.baumeister/MDDGeneratorPlugin"]
           ["org.soulspace.baumeister/PackagePlugin"]] 
 :dependencies [["org.soulspace.modelling/ArgoUMLProfileLibrary, 1.0.0, ArgoUMLProfileLibrary, zip" :generator]
                ["org.soulspace.modelling/MDDTemplateLibrary, 1.0.2, MDDTemplateLibrary, zip" :generator]]
 :mddgenerator-model-dir "profiles"
 :mddgenerator-model-name "MDSDProfile"
 :mddgenerator-template-path "${lib-generator-dir}/templates:templates"
 :mddgenerator-profile-path "profiles"
 :mddgenerator-std-profiles ["argouml/default-uml14"]
 :mddgenerator-config-dir "./config"
 ]
