(def component-stereotypes 
  ["library" "framework" "integration" "domain" "application" "presentation" "web frontend" "web service"])

(def web-stereotypes
  ["web frontend" "web service"])

; TODO if the architecture packages lie in project packages,
; TODO an empty base namespace has to be set for all generators
; TODO  :baseNamespace ""
; TODO check if empty baseNamespaces are currently supported as needed

(defn empty-dir-generators []
  ; generate dummy files in empty dirs
  [{:element "Package"
    :template "empty"
    :baseName "dummy"
    :extension "txt"
    :subdir "modules"
    :namespaceSuffix "templates"
    :useNameAsNamespace true
    :stereotypes component-stereotypes}
   {:element "Package"
    :template "empty"
    :baseName "dummy"
    :extension "txt"
    :subdir "modules"
    :namespaceSuffix "src"
    :useNameAsNamespace true
    :stereotypes component-stereotypes}
   {:element "Package"
    :template "empty"
    :baseName "dummy"
    :extension "txt"
    :subdir "modules"
    :namespaceSuffix "generated"
    :useNameAsNamespace true
    :stereotypes component-stereotypes}
   {:element "Package"
    :template "empty"
    :baseName "dummy"
    :extension "txt"
    :subdir "modules"
    :namespaceSuffix "backup"
    :useNameAsNamespace true
    :stereotypes component-stereotypes}])
   
(defn argo-model-generators []
  ; generate argo model
  [{:element "Package"
    :template "common/uml/argo/todo"
    :includes ["lib" "model/lib" "xml/lib" "common/uml/argo/lib"]
    :extension "todo"
    :useNameAsNamespace true
    :subdir "models"
    :stereotypes component-stereotypes}
   {:element "Package"
    :template "common/uml/argo/profile"
    :includes ["lib" "model/lib" "xml/lib" "common/uml/argo/lib"]
    :suffix "_profile"
    :extension "profile"
    :useNameAsNamespace true
    :subdir "models"
    :stereotypes component-stereotypes}
   {:element "Package"
    :template "common/uml/argo/argo"
    :includes ["lib" "model/lib" "common/uml/argo/lib"]
    :extension "argo"
    :useNameAsNamespace true
    :subdir "models"
    :stereotypes component-stereotypes}])

(defn web-project-generators []
  [{:element "Package"
    :template "text/text"
    :includes ["lib" "model/lib" "project/web/jsdt-supertype-name"]
    :baseName ".settings/org.eclipse.wst.jsdt.ui.superType.name"
    :useNameAsNamespace true
    :subdir "modules"
    :stereotypes web-stereotypes}
   {:element "Package"
    :template "text/text"
    :includes ["lib" "model/lib" "project/web/jsdt-supertype-container"]
    :baseName ".settings/org.eclipse.wst.jsdt.ui.superType.container"
    :useNameAsNamespace true
    :subdir "modules"
    :stereotypes web-stereotypes}
   {:element "Package"
    :template "text/text"
    :includes ["lib" "model/lib" "project/web/jdt-core-prefs"]
    :baseName ".settings/org.eclipse.jdt.core.prefs"
    :useNameAsNamespace true
    :subdir "modules"
    :stereotypes web-stereotypes}
   {:element "Package"
    :template "xml/xml"
    :includes ["lib" "model/lib" "xml/lib" "project/web/jsdtscope"]
    :baseName ".settings/.jsdtscope"
    :useNameAsNamespace true
    :subdir "modules"
    :stereotypes web-stereotypes}
   {:element "Package"
    :template "xml/xml"
    :includes ["lib" "model/lib" "xml/lib" "project/web/wst-common-component"]
    :baseName ".settings/org.eclipse.wst.common.component"
    :useNameAsNamespace true
    :subdir "modules"
    :stereotypes web-stereotypes}
   {:element "Package"
    :template "xml/xml"
    :includes ["lib" "model/lib" "xml/lib" "project/web/wst-project-facet-core"]
    :baseName ".settings/org.eclipse.wst.common.project.facet.core.xml"
    :useNameAsNamespace true
    :subdir "modules"
    :stereotypes web-stereotypes}])

(defn library-generators []
 [
  ; Library
  {:element "Package"
   :template "text/text"
   :includes ["lib" "model/lib" "project/module-clj" "project/library/module-clj"]
   :baseName "module"
   :extension "clj"
   :useNameAsNamespace true
   :subdir "modules"
   :stereotypes "library"}
  {:element "Package"
   :template "xml/xml"
   :includes ["lib" "model/lib" "xml/lib" "project/eclipse-project" "project/library/eclipse-project"]
   :baseName ".project"
   :useNameAsNamespace true
   :subdir "modules"
   :stereotypes "library"}
  {:element "Package"
   :template "xml/xml"
   :includes ["lib" "model/lib" "xml/lib" "project/eclipse-classpath" "project/library/eclipse-classpath"]
   :baseName ".classpath"
   :useNameAsNamespace true
   :subdir "modules"
   :stereotypes "library"}
  {:element "Package"
   :template "text/text"
   :includes ["lib" "model/lib" "project/cvs-ignore"]
   :baseName ".cvsignore"
   :useNameAsNamespace true
   :subdir "modules"
   :stereotypes "library"}
  ; generate argo model
  {:element "Package"
   :template "common/uml/xmi12"
   :includes ["lib" "model/lib" "xml/lib" "common/uml/uml14-factory" "common/uml/xmi12"]
   :extension "xmi"
   :useNameAsNamespace true
   :subdir "models"
   :stereotypes "library"}
  ])

(defn framework-generators []
  ; Framework
  [
   {:element "Package"
    :template "text/text"
    :includes ["lib" "model/lib" "project/module-clj" "project/framework/module-clj"]
    :baseName "module"
    :extension "clj"
    :useNameAsNamespace true
    :subdir "modules"
    :stereotypes "framework"}
   {:element "Package"
    :template "xml/xml"
    :includes ["lib" "model/lib" "xml/lib" "project/eclipse-project" "project/framework/eclipse-project"]
    :baseName ".project"
    :useNameAsNamespace true
    :subdir "modules"
    :stereotypes "framework"}
   {:element "Package"
    :template "xml/xml"
    :includes ["lib" "model/lib" "xml/lib" "project/eclipse-classpath" "project/framework/eclipse-classpath"]
    :baseName ".classpath"
    :useNameAsNamespace true
    :subdir "modules"
    :stereotypes "framework"}
   {:element "Package"
    :template "text/text"
    :includes ["lib" "model/lib" "project/cvs-ignore"]
    :baseName ".cvsignore"
    :useNameAsNamespace true
    :subdir "modules"
    :stereotypes "framework"}
   ; generate argo model
   {:element "Package"
    :template "common/uml/xmi12"
    :includes ["lib" "model/lib" "xml/lib" "common/uml/uml14-factory" "common/uml/xmi12"]
    :extension "xmi"
    :useNameAsNamespace true
    :subdir "models"
    :stereotypes "framework"}
   ])

(defn application-generators []
  [
   ; Application Layer
   {:element "Package"
    :template "text/text"
    :includes ["lib" "model/lib" "project/module-clj" "project/application/module-clj"]
    :baseName "module"
    :extension "clj"
    :useNameAsNamespace true
    :subdir "modules"
    :stereotypes "application"}
   {:element "Package"
    :template "xml/xml"
    :includes ["lib" "model/lib" "xml/lib" "project/eclipse-project" "project/application/eclipse-project"]
    :baseName ".project"
    :useNameAsNamespace true
    :subdir "modules"
    :stereotypes "application"}
   {:element "Package"
    :template "xml/xml"
    :includes ["lib" "model/lib" "xml/lib" "project/eclipse-classpath" "project/application/eclipse-classpath"]
    :baseName ".classpath"
    :useNameAsNamespace true
    :subdir "modules"
    :stereotypes "application"}
   {:element "Package"
    :template "text/text"
    :includes ["lib" "model/lib" "project/cvs-ignore"]
    :baseName ".cvsignore"
    :useNameAsNamespace true
    :subdir "modules"
    :stereotypes "application"}
   ; generate argo model
   {:element "Package"
    :template "common/uml/xmi12"
    :includes ["lib" "model/lib" "xml/lib" "common/uml/uml14-factory" "common/uml/xmi12" "project/application/model-xmi"]
    :extension "xmi"
    :useNameAsNamespace true
    :subdir "models"
    :stereotypes "application"}
   ])

(defn domain-generators []
  [
   ; Domain Layer
   {:element "Package"
    :template "text/text"
    :includes ["lib" "model/lib" "project/module-clj" "project/domain/module-clj"]
    :baseName "module"
    :extension "clj"
    :useNameAsNamespace true
    :subdir "modules"
    :stereotypes "domain"}
   {:element "Package"
    :template "xml/xml"
    :includes ["lib" "model/lib" "xml/lib" "project/eclipse-project" "project/domain/eclipse-project"]
    :baseName ".project"
    :useNameAsNamespace true
    :subdir "modules"
    :stereotypes "domain"}
   {:element "Package"
    :template "xml/xml"
    :includes ["lib" "model/lib" "xml/lib" "project/eclipse-classpath" "project/domain/eclipse-classpath"]
    :baseName ".classpath"
    :useNameAsNamespace true
    :subdir "modules"
    :stereotypes "domain"}
   {:element "Package"
    :template "text/text"
    :includes ["lib" "model/lib" "project/cvs-ignore"]
    :baseName ".cvsignore"
    :useNameAsNamespace true
    :subdir "modules"
    :stereotypes "domain"}
   ; generate argo model
   {:element "Package"
    :template "common/uml/xmi12"
    :includes ["lib" "model/lib" "xml/lib" "common/uml/uml14-factory" "common/uml/xmi12" "project/domain/model-xmi"]
    :extension "xmi"
    :useNameAsNamespace true
    :subdir "models"
    :stereotypes "domain"}
   ])

(defn integration-generators []
  [
   ; Integration Layer
   {:element "Package"
    :template "text/text"
    :includes ["lib" "model/lib" "project/module-clj" "project/integration/module-clj"]
    :baseName "module"
    :extension "clj"
    :useNameAsNamespace true
    :subdir "modules"
    :stereotypes "integration"}
   {:element "Package"
    :template "xml/xml"
    :includes ["lib" "model/lib" "xml/lib" "project/eclipse-project" "project/integration/eclipse-project"]
    :baseName ".project"
    :useNameAsNamespace true
    :subdir "modules"
    :stereotypes "integration"}
   {:element "Package"
    :template "xml/xml"
    :includes ["lib" "model/lib" "xml/lib" "project/eclipse-classpath" "project/integration/eclipse-classpath"]
    :baseName ".classpath"
    :useNameAsNamespace true
    :subdir "modules"
    :stereotypes "integration"}
   {:element "Package"
    :template "text/text"
    :includes ["lib" "model/lib" "project/cvs-ignore"]
    :baseName ".cvsignore"
    :useNameAsNamespace true
    :subdir "modules"
    :stereotypes "integration"}
   ; generate argo model
   {:element "Package"
    :template "common/uml/xmi12"
    :includes ["lib" "model/lib" "xml/lib" "common/uml/uml14-factory" "common/uml/xmi12" "project/integration/model-xmi"]
    :extension "xmi"
    :useNameAsNamespace true
    :subdir "models"
    :stereotypes "integration"}
   ])

(defn presentation-generators []
  [
   ; Presentation Layer
   {:element "Package"
    :template "text/text"
    :includes ["lib" "model/lib" "project/module-clj" "project/presentation/module-clj"]
    :baseName "module"
    :extension "clj"
    :useNameAsNamespace true
    :subdir "modules"
    :stereotypes "presentation"}
   {:element "Package"
    :template "xml/xml"
    :includes ["lib" "model/lib" "xml/lib" "project/eclipse-project" "project/presentation/eclipse-project"]
    :baseName ".project"
    :useNameAsNamespace true
    :subdir "modules"
    :stereotypes "presentation"}
   {:element "Package"
    :template "xml/xml"
    :includes ["lib" "model/lib" "xml/lib" "project/eclipse-classpath" "project/presentation/eclipse-classpath"]
    :baseName ".classpath"
    :useNameAsNamespace true
    :subdir "modules"
    :stereotypes "presentation"}
   {:element "Package"
    :template "text/text"
    :includes ["lib" "model/lib" "project/cvs-ignore"]
    :baseName ".cvsignore"
    :useNameAsNamespace true
    :subdir "modules"
    :stereotypes "presentation"}
   ; generate argo model
   {:element "Package"
    :template "common/uml/xmi12"
    :includes ["lib" "model/lib" "xml/lib" "common/uml/uml14-factory" "common/uml/xmi12" "project/presentation/model-xmi"]
    :extension "xmi"
    :useNameAsNamespace true
    :subdir "models"
    :stereotypes "presentation"}
   ]
  )

(defn web-frontend-generators []
  [
   ; Web frontend
   {:element "Package"
    :template "text/text"
    :includes ["lib" "model/lib" "project/module-clj" "project/webfrontend/module-clj"]
    :baseName "module"
    :extension "clj"
    :useNameAsNamespace true
    :subdir "modules"
    :stereotypes "web frontend"}
   {:element "Package"
    :template "xml/xml"
    :includes ["lib" "model/lib" "xml/lib" "project/eclipse-project" "project/webfrontend/eclipse-project"]
    :baseName ".project"
    :useNameAsNamespace true
    :subdir "modules"
    :stereotypes "web frontend"}
   {:element "Package"
    :template "xml/xml"
    :includes ["lib" "model/lib" "xml/lib" "project/eclipse-classpath" "project/webfrontend/eclipse-classpath"]
    :baseName ".classpath"
    :useNameAsNamespace true
    :subdir "modules"
    :stereotypes "web frontend"}
   {:element "Package"
    :template "text/text"
    :includes ["lib" "model/lib" "project/cvs-ignore"]
    :baseName ".cvsignore"
    :useNameAsNamespace true
    :subdir "modules"
    :stereotypes "web frontend"}
   ; generate web project specific files
   {:element "Package"
    :template "xml/xml"
    :includes ["lib" "model/lib" "xml/lib" "project/web/web-xml" "project/webfrontend/web-xml"]
    :baseName "web"
    :extension "xml"
    :useNameAsNamespace true
    :namespaceSuffix "WebContent/WEB-INF"
    :subdir "modules"
    :stereotypes "web frontend"}
   ; generate argo model
   {:element "Package"
    :template "common/uml/xmi12"
    :includes ["lib" "model/lib" "xml/lib" "common/uml/uml14-factory" "common/uml/xmi12"]
    :extension "xmi"
    :useNameAsNamespace true
    :subdir "models"
    :stereotypes "web frontend"}
   ]
  )

(defn web-service-generators []
  [
   {:element "Package"
    :template "text/text"
    :includes ["lib" "model/lib" "project/module-clj" "project/webservice/module-clj"]
    :baseName "module"
    :extension "clj"
    :useNameAsNamespace true
    :subdir "modules"
    :stereotypes "web service"}
   {:element "Package"
    :template "xml/xml"
    :includes ["lib" "model/lib" "xml/lib" "project/eclipse-project" "project/webservice/eclipse-project"]
    :baseName ".project"
    :useNameAsNamespace true
    :subdir "modules"
    :stereotypes "web service"}
   {:element "Package"
    :template "xml/xml"
    :includes ["lib" "model/lib" "xml/lib" "project/eclipse-classpath" "project/webservice/eclipse-classpath"]
    :baseName ".classpath"
    :useNameAsNamespace true
    :subdir "modules"
    :stereotypes "web service"}
   {:element "Package"
    :template "text/text"
    :includes ["lib" "model/lib" "project/cvs-ignore"]
    :baseName ".cvsignore"
    :useNameAsNamespace true
    :subdir "modules"
    :stereotypes "web service"}
   ; generate web project specific files
   {:element "Package"
    :template "xml/xml"
    :includes ["lib" "model/lib" "xml/lib" "project/web/web-xml" "project/webservice/web-xml"]
    :baseName "web"
    :extension "xml"
    :useNameAsNamespace true
    :namespaceSuffix "WebContent/WEB-INF"
    :subdir "modules"
    :stereotypes "web service"}
   ; generate argo model
   {:element "Package"
    :template "common/uml/xmi12"
    :includes ["lib" "model/lib" "xml/lib" "common/uml/uml14-factory" "common/uml/xmi12"]
    :extension "xmi"
    :useNameAsNamespace true
    :subdir "models"
    :stereotypes "web service"}
   ])

[
 ; module type specific generators
 (library-generators)
 (framework-generators)
 (application-generators)
 (domain-generators)
 (integration-generators)
 (presentation-generators)
 (web-frontend-generators)
 (web-service-generators)

 ; generic generators
 (web-project-generators)
 (argo-model-generators)
 (empty-dir-generators)
]