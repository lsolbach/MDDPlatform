{:doc-generators ; generation of design documentation
 [{:element "Class" ; DOT class diagram for a single class
   :template "dot/dot"
   :includes ["lib" "model/lib" "java/lib" "dot/dot" "common/model/lib" "domain/model/lib"
             "common/doc/dot/lib" "common/doc/dot/color" "common/doc/dot/class-dot"]
   :extension "dot"
   :subdir "doc"
   :stereotypes "ALL"}
  {:element "Package" ; PlantUML class diagram for a package
   :template "plantuml/diagram"
   :includes ["lib" "model/lib" "java/lib" "plantuml/diagram"  "plantuml/class-diagram"
              "common/model/lib" "domain/model/lib"  "common/doc/plantuml/class-diagram"]
   :extension "pu"
   :useNameAsNamespace true
   :subdir "doc"
   :stereotypes "ALL"}
  {:element "Class" ; PlantUML class diagram for a single class
   :template "plantuml/diagram"
   :includes ["lib" "model/lib" "java/lib" "plantuml/diagram"  "plantuml/class-diagram"
              "common/model/lib" "domain/model/lib"  "common/doc/plantuml/class-diagram"]
   :extension "pu"
   :subdir "doc"
   :stereotypes "ALL"}
  {:element "Model" ; HTML model documentation
   :template "html/html"
   :includes ["lib" "model/lib" "html/html4" "common/doc/html/css" "common/doc/html/model"]
   :extension "html"
   :subdir "doc"
   :stereotypes "NONE"}
  {:element "Model" ; HTML profile documentation
   :template "html/html"
   :includes ["lib" "model/lib" "html/html4" "common/doc/html/css" "architecture/doc/html/profile"]
   :suffix "Profile"
   :extension "html"
   :subdir "doc"
   :stereotypes "NONE"
   }]
 
 :analysis ; generation of analysis artifacts
 [{:element "Package"
   :template "txt/txt" 
   :includes ["lib" "model/lib" "requirements/usecases"]
   :extension "txt"
   :subdir "tmp"
   :stereotypes "use case model"}]

 :standard-generators ; generation of standard java/aspectj artifacts
 [{:element "Class"
   :template "java/enum" 
   :includes ["lib" "model/lib" "java/lib" "java/enum"]
   :extension "java"
   :subdir "src"
   :stereotypes "enumeration"}

  {:element "Class"
   :template "java/interface"
   :includes ["lib" "model/lib" "java/lib" "java/interface" "java/bean-interface"
              "common/model/lib" "common/java/lib" "common/java/interface"
              "common/java/mapping-interface" "common/java/model-interface"]
   :extension "java"
   :subdir "src"
   :stereotypes "model"}
  {:element "Class"
   :template "java/class"
   :includes ["lib" "model/lib" "java/lib" "java/class" "java/bean-class" "common/model/lib"
              "common/java/lib" "common/java/class" "common/java/metadata"
              "common/java/mapping-class" "common/java/model-class"]
   :namespaceSuffix "impl"
   :prefix "Abstract"
   :extension "java"
   :subdir "src"
   :stereotypes "model"}
  
  {:element "Class"
   :template "aspectj/aspect"
   :includes ["lib" "model/lib" "java/lib" "java/class" "java/bean-class"
              "common/model/lib" "common/java/lib" "common/java/class"]
   :namespaceSuffix "impl"
   :prefix "Abstract"
   :extension "java"
   :subdir "src"
   :stereotypes "aspect"}]

 
 :domain-generators ; generation of domain layer artifacts
 [{; domain object interface
   :element "Class"
   :template "java/interface"
   :includes ["lib" "model/lib" "java/lib" "java/interface" "java/bean-interface"
              "common/model/lib" "common/java/lib" "domain/model/lib" "domain/java/lib"
              "domain/java/domain-object-interface"]
   :extension "java"
   :subdir "src"
   :stereotypes "domain object"}
  {; abstract domain object class
   :element "Class"
   :template "java/class"
   :includes ["lib" "model/lib" "java/lib" "java/class" "java/bean-class" "common/model/lib"
              "common/java/lib" "common/java/class" "common/java/metadata" "domain/model/lib"
              "domain/java/lib" "domain/java/domain-object-class"
              ]
   :namespaceSuffix "impl"
   :prefix "Abstract"
   :extension "java"
   :subdir "src"
   :stereotypes "domain object"}
  {; entity interface
   :element "Class"
   :template "java/interface"
   :includes ["lib" "model/lib" "java/lib" "java/interface" "java/bean-interface"
             "common/model/lib" "common/java/lib" "domain/model/lib" "domain/java/lib"
             "domain/java/entity-interface"]
   :extension "java"
   :subdir "src"
   :stereotypes "entity"}
  {; abstract entity base class
   :element "Class"
   :template "java/class"
   :includes ["lib" "model/lib" "java/lib" "java/class" "java/bean-class" "common/model/lib"
              "common/java/lib" "common/java/class" "common/java/metadata" "domain/model/lib"
              "domain/java/lib" "domain/java/entity-class"
              ]
   :namespaceSuffix "impl"
   :prefix "Abstract"
   :extension "java"
   :subdir "src"
   :stereotypes "entity"}
  {; entity implementation class
   :element "Class"
   :template "java/class" 
   :includes ["lib" "model/lib" "java/lib" "java/class" "java/bean-impl-class" "common/model/lib"
              "common/java/lib" "java/impl-class" "domain/java/entity-impl-class"]
   :namespaceSuffix "impl"
   :suffix "Impl"
   :extension "java"
   :subdir "tmp"
   :stereotypes ["entity"]}
  
  {; value interface
   :element "Class"
   :template "java/interface"
   :includes ["lib" "model/lib" "java/lib" "java/interface" "java/bean-interface"
             "common/model/lib" "common/java/lib" "domain/model/lib" "domain/java/lib"
             "domain/java/value-interface"]
   :extension "java"
   :subdir "src"
   :stereotypes "value"}
  {; abstract value base class
   :element "Class"
   :template "java/class"
   :includes ["lib" "model/lib" "java/lib" "java/class" "java/bean-class" "common/model/lib"
              "common/java/lib" "common/java/class" "common/java/metadata" "domain/model/lib"
              "domain/java/lib" "domain/java/value-class"]
   :namespaceSuffix "impl"
   :prefix "Abstract"
   :extension "java"
   :subdir "src"
   :stereotypes "value"}  
  {; value implementation class
   :element "Class"
   :template "java/class" 
   :includes ["lib" "model/lib" "java/lib" "java/class" "java/bean-impl-class" "common/model/lib"
              "common/java/lib" "java/impl-class" "domain/java/value-impl-class"]
   :namespaceSuffix "impl"
   :suffix "Impl"
   :extension "java"
   :subdir "tmp"
   :stereotypes "value"}

  {; service interface
   :element "Class"
   :template "java/interface"
   :includes ["lib" "model/lib" "java/lib" "java/interface" "common/model/lib"
             "common/java/lib" "common/java/interface" "domain/model/lib"
             "domain/java/lib" "domain/java/service-interface"]
   :extension "java"
   :subdir "src"
   :stereotypes "service"}
  {; abstract service
   :element "Class"
   :template "java/class"
   :includes ["lib" "model/lib" "java/lib" "java/class" "common/model/lib"
             "common/java/lib" "common/java/class" "common/java/transaction"
             "domain/model/lib" "domain/java/lib" "domain/java/service-class"]
   :namespaceSuffix "impl"
   :prefix "Abstract"
   :extension "java"
   :subdir "src"
   :stereotypes "service"}

  {; factory interface
   :element "Class"
   :template "java/interface"
   :includes ["lib" "model/lib" "java/lib" "java/interface" "common/model/lib"
             "common/java/lib" "common/java/interface" "domain/model/lib"
             "domain/java/lib" "domain/java/factory" "domain/java/factory-interface"]
   :extension "java"
   :subdir "src"
   :stereotypes "factory"}
  {; abstract factory
   :element "Class"
   :template "java/class"
   :includes ["lib" "model/lib" "java/lib" "java/class" "common/model/lib"
             "common/java/lib" "common/java/class" "domain/model/lib"
             "domain/java/lib" "domain/java/factory" "domain/java/factory-class"]
   :namespaceSuffix "impl"
   :prefix "Abstract"
   :extension "java"
   :subdir "src"
   :stereotypes "factory"}
  
  {; repository interface
   :element "Class"
   :template "java/interface"
   :includes ["lib" "model/lib" "java/lib" "java/interface" "common/model/lib"
             "common/java/lib" "common/java/interface" "domain/model/lib"
             "domain/java/lib" "domain/java/repository" "domain/java/repository-interface"]
   :extension "java"
   :subdir "src"
   :stereotypes "repository"}
  {; abstract JPA repository
   :element "Class"
   :template "java/class"
   :includes ["lib" "model/lib" "db/lib" "java/lib" "java/class" "common/model/lib"
              "common/java/lib" "common/db/lib" "common/java/class"
              "common/java/transaction" "common/java/spring-jpa"
              "domain/model/lib" "domain/java/lib" "domain/java/repository"
              "domain/java/repository-class" "domain/java/spring-jpa"]
   :namespaceSuffix "impl"
   :prefix "AbstractJpa"
   :extension "java"
   :subdir "src"
   :stereotypes "repository"}
;  {; abstract XStream repository 
;   :element "Class"
;   :template "java/class"
;   :includes ["lib" "model/lib" "java/lib" "java/class" "common/model/lib" "common/java/lib"
;              "common/java/class" "common/java/transaction" "domain/model/lib"
;              "domain/java/lib" "domain/java/xstream-storage" "domain/java/repository"
;              "domain/java/repository-class"]
;   :namespaceSuffix "impl"
;   :prefix "AbstractXStream"
;   :extension "java"
;   :subdir "src"
;   :stereotypes "repository"}
  
  ; repository queries
  {; query interface
   :element "Class"
   :template "java/interface"
   :includes ["lib" "model/lib" "java/lib" "java/interface"
              "java/bean-interface" "common/model/lib" "common/java/lib"
              "common/java/interface" "domain/model/lib"
              "common/java/query-interface"]
   :extension "java"
   :subdir "src"
   :stereotypes "query"}
  {; query class
   :element "Class"
   :template "java/class"
   :includes ["lib" "model/lib" "db/lib" "java/lib" "java/class" "java/bean-class"
              "common/model/lib" "common/java/lib" "common/db/lib" "common/java/class"
              "domain/model/lib" "common/java/query-class" "common/java/spring-jpa"]
   :namespaceSuffix "impl"
   :extension "java"
   :suffix "Impl"
   :subdir "src"
   :stereotypes "query"}
  
  {; query result interface
   :element "Class"
   :template "java/interface"
   :includes ["lib" "model/lib" "java/lib" "java/interface" "java/bean-interface"
              "common/model/lib" "common/java/lib" "common/java/interface"
              "common/java/query-result-interface"]
   :extension "java"
   :subdir "src"
   :stereotypes "query result"}
  {; query result class
   :element "Class"
   :template "java/class" 
   :includes ["lib" "model/lib" "java/lib" "java/class" "java/bean-class" "common/model/lib"
             "common/java/lib" "common/java/class" "common/java/query-result-class"]
   :namespaceSuffix "impl"
   :extension "java"
   :suffix "Impl"
   :subdir "src"
   :stereotypes "query result"}
  
  {; model provider aspect
   :element "Package"
   :template "aspectj/aspect"
   :includes ["lib" "model/lib" "java/lib" "java/class" "aspectj/lib" "common/model/lib"
             "common/java/lib" "domain/model/lib" "domain/java/lib"
             "domain/aspectj/model-provider-aspect"]
   :useNameAsNamespace true
   :baseName "ModelProviderAspect"
   :extension "aj"
   :subdir "src"
   :stereotypes "model package"}
  
  {; spring configuration
   :element "Package"
   :template "xml/xml"
   :includes ["lib" "model/lib" "xml/lib" "java/lib" "common/model/lib" "domain/model/lib"
             "common/xml/lib" "common/config/spring-config" "common/config/jpa-config"
             "common/config/h2-config" "domain/config/spring-config"
             "domain/config/jpa-config"]
   :useNameAsNamespace true
   :baseName "component-config"
   :extension "xml"
   :subdir "src"
   :stereotypes "component"}

  ; JPA/Hibernate persistence
  {; class level hibernate mapping
   :element "Class"
   :template "xml/xml"
   :includes ["lib" "model/lib" "xml/lib" "java/lib" "java/class" "db/lib" "common/model/lib"
             "common/xml/lib" "common/db/lib" "domain/model/lib" "domain/java/lib"
             "domain/persistence/hbn-xml"]
   :extension "hbn.xml"
   :subdir "src"
   :stereotypes "entity"}
  {; component level hibernate mapping
   :element "Package"
   :template "xml/xml"
   :includes ["lib" "model/lib" "xml/lib" "java/lib" "java/class" "db/lib" "common/model/lib"
             "common/xml/lib" "common/db/lib" "domain/model/lib" "domain/java/lib"
             "domain/persistence/hbn-xml"]
   :useNameAsNamespace true
   :baseName "[MODEL_NAME]"
   :extension "hbn.xml"
   :subdir "src"
   :stereotypes "model package"}
  {; component level persistence xml
   :element "Package"
   :template "xml/xml"
   :includes ["lib" "model/lib" "xml/lib" "java/lib" "common/model/lib" "common/xml/lib"
             "common/db/lib" "domain/model/lib" "domain/persistence/persistence-xml"
             "domain/persistence/hbn-persistence-xml"]
   :baseName "[MODEL_NAME]"
   :suffix "-persistence"
   :extension "xml"
   :baseNamespace "META-INF"
   :subdir "src"
   :stereotypes "model package"}
  {; drop tables ddl script
   :element "Package"
   :template "domain/persistence/create-tables"
   :includes ["lib" "model/lib" "db/lib" "common/model/lib" "common/db/lib"
              "domain/model/lib" "domain/persistence/ddl"
              "domain/persistence/dialect/h2"
              ]
   :useNameAsNamespace true
   :baseName "create-tables"
   :extension "sql"
   :subdir "data"
   :stereotypes "model package"}
  {; drop tables ddl script
   :element "Package"
   :template "domain/persistence/drop-tables"
   :includes ["lib" "model/lib" "db/lib" "common/model/lib" "common/db/lib"
              "domain/model/lib" "domain/persistence/ddl"
              "domain/persistence/dialect/h2"]
   :useNameAsNamespace true
   :baseName "drop-tables"
   :extension "sql"
   :subdir "data"
   :stereotypes "model package"}
  ; test
  {; abstract integration test class
   :element "Class"
   :template "java/class" 
   :includes ["lib" "model/lib" "java/lib" "java/class" "common/model/lib"
             "common/java/lib" "common/java/class" "common/java/transaction"
             "domain/model/lib" "test/java/junit"]
   :namespaceSuffix "impl"
   :prefix "Abstract"
   :suffix "Test"
   :extension "java"
   :subdir "integrationtest"
   :stereotypes "service"}
  {; spring configuration integration test
   :element "Package"
   :template "xml/xml"
   :includes ["lib" "model/lib" "xml/lib" "java/lib" "common/model/lib" "domain/model/lib"
             "common/xml/lib" "common/config/spring-config" "common/config/jpa-config"
             "common/config/h2-config" "test/config/spring-config"
             "test/config/jpa-config"]
   :useNameAsNamespace true
   :baseName "test-config"
   :extension "xml"
   :subdir "integrationtest"
   :stereotypes "component"}
  
  {; model validation
   :element "Package" 
   :template "common/model/validate"
   :includes ["lib" "model/lib" "common/model/lib" "common/model/critics"
              "domain/model/lib" "domain/model/critics"]
   :useNameAsNamespace true
   :baseName "Validation"
   :extension "txt"
   :stereotypes "model package"
   :subdir "doc"}
  ]
 
 
 :application-generators ; generation of application layer artifacts
 [{:element "Class"
   :template "java/interface" 
   :includes ["lib" "model/lib" "java/lib" "java/interface" "common/model/lib"
             "common/java/lib" "common/java/interface" "common/java/task"
             "common/java/service-interface"]
   :extension "java"
   :subdir "src"
   :stereotypes "service"}
  {:element "Class"
   :template "java/class" 
   :includes ["lib" "model/lib" "java/lib" "java/class" "common/model/lib" "common/java/lib"
             "common/java/class" "common/java/transaction" "common/java/task" "common/java/service-class"]
   :namespaceSuffix "impl"
   :prefix "Abstract"
   :extension "java"
   :subdir "src"
   :stereotypes "service"}
  
  ; db queries
  {:element "Class"
   :template "java/interface" 
   :includes ["lib" "model/lib" "java/lib" "java/interface" "java/bean-interface"
             "common/model/lib" "common/java/lib" "common/java/interface"
             "common/java/query-interface"]
   :extension "java"
   :subdir "src"
   :stereotypes "query"}
  {:element "Class"
   :template "java/class" 
   :includes ["lib" "model/lib" "db/lib" "java/lib" "java/class" "java/bean-class" "common/model/lib"
             "common/java/lib" "common/db/lib" "common/java/class"
             "common/java/query-class" "common/java/spring-jpa"]
   :namespaceSuffix "impl"
   :extension "java"
   :suffix "Impl"
   :subdir "src"
   :stereotypes "query"}
  
  {:element "Class"
   :template "java/interface" 
   :includes ["lib" "model/lib" "java/lib" "java/interface" "java/bean-interface"
             "common/model/lib" "common/java/lib" "common/java/interface"
             "common/java/query-result-interface"]
   :extension "java"
   :subdir "src"
   :stereotypes "query result"}
  {:element "Class"
   :template "java/class"
   :includes ["lib" "model/lib" "java/lib" "java/class" "java/bean-class" "common/model/lib"
              "common/java/lib" "common/java/class" "common/java/query-result-class"]
   :namespaceSuffix "impl"
   :extension "java"
   :suffix "Impl"
   :subdir "src"
   :stereotypes "query result"}
  
  ; spring component configuration
  {:element "Package"
   :template "xml/xml"
   :includes ["lib" "model/lib" "xml/lib" "xml/lib" "java/lib" "common/model/lib"
              "domain/model/lib" "common/xml/lib" "common/config/spring-config"
              "common/config/jpa-config" "application/config/spring-config"]
   :useNameAsNamespace true
   :baseName "component-config"
   :extension "xml"
   :subdir "src"
   :stereotypes "component"}
  ; test
  {:element "Class" ; abstract test classes
   :template "java/class" 
   :includes ["lib" "model/lib" "java/lib" "java/class" "common/model/lib"
              "common/java/lib" "common/java/class" "common/java/transaction"
              "domain/model/lib" "test/java/junit"]
   :namespaceSuffix "impl"
   :prefix "Abstract"
   :suffix "Test"
   :extension "java"
   :subdir "integrationtest"
   :stereotypes "service"}
  {:element "Package" ; test spring config
   :template "xml/xml"
   :includes ["lib" "model/lib" "xml/lib" "xml/lib" "java/lib" "common/model/lib"
              "common/xml/lib" "common/config/spring-config" "common/config/jpa-config"
              "common/config/h2-config" "domain/model/lib" "test/config/spring-config"
              "test/config/jpa-config"]
   :useNameAsNamespace true
   :baseName "test-config"
   :extension "xml"
   :subdir "integrationtest"
   :stereotypes "component"}]

 :presentation-generators ; generation of presentation layer artifacts
 [{:element "Package"
   :template "xml/xml"
   :includes ["lib" "model/lib" "xml/lib" "common/model/lib" "presentation/model/lib"
              "presentation/web/config/web-fragment"
             "presentation/web/config/mvc-web-xml" "presentation/web/config/webflow-web-xml"]
   :baseName "web-fragment"
   :extension "xml"
   :baseNamespace "META-INF"
   :subdir "src"
   :stereotypes "presentation"}
  {:element "Package" ; Spring component configuration 
   :template "xml/xml"
   :includes ["lib" "model/lib" "xml/lib" "java/lib" "common/model/lib" "common/xml/lib"
              "common/config/spring-config" "presentation/model/lib" "presentation/web/config/webflow-config"
              "presentation/config/spring-config"]
   :baseName "component-config"
   :extension "xml"
   :subdir "src"
   :stereotypes "presentation"}
  ; Spring mvc controller
  {:element "Class"
   :template "java/interface"
   :includes ["lib" "model/lib" "java/lib" "java/interface" "common/model/lib" "common/java/lib"
              "common/java/interface" "presentation/model/lib" "presentation/java/lib"
              "presentation/web/java/mvc-controller-interface"]
   :extension "java"
   :subdir "src"
   :stereotypes "mvc controller"
   }
  {:element "Class"
   :template "java/class"
   :includes ["lib" "model/lib" "java/lib" "java/class" "common/model/lib" "common/java/lib"
              "common/java/class" "presentation/model/lib" "presentation/java/lib"
              "presentation/web/java/mvc-controller-class"]
   :prefix "Abstract"
   :extension "java"
   :namespaceSuffix "impl"
   :subdir "src"
   :stereotypes "mvc controller"}
  ; JSPs
  {:element "Class"
   :template "jsp/jsp"
   :includes ["lib" "model/lib" "jsp/jsp" "html/html4" "common/model/lib"
             "presentation/model/lib" "presentation/web/jsp/swf" "presentation/web/jsp/spring-mvc" "presentation/web/jsp/crud"]
   :namespacePrefix "META-INF/resources/views"
   :baseNamespace "[MODEL_NAME]"
   :extension "jsp"
   :subdir "src"
   :stereotypes "view"}
  ; Spring webflow 2
  {:element "StateMachine"
   :template "xml/xml"
   :includes ["lib" "model/lib" "xml/lib" "java/lib" "common/model/lib"
             "presentation/web/config/webflow"]
   :extension "xml"
   :baseNamespace "META-INF/resources/flows"
   :subdir "src"
   :protectedArea="PA"
   :stereotypes "flow"}
  {:element "Package" ; Test for View/ViewState matching
   :template "text/text"
   :includes ["lib" "model/lib" "common/model/lib"
             "presentation/model/lib" "presentation/test/states"]
   :extension "txt"
   :subdir "test"
   :stereotypes "NONE"
   }]
 
 :web-frontend-generators ; generation of web frontend artifacts
 [; Spring mvc controller
  {:element "Class"
   :template "java/interface"
   :includes ["lib" "model/lib" "java/lib" "java/interface" "common/model/lib" "common/java/lib"
              "common/java/interface" "presentation/model/lib" "presentation/java/lib" "presentation/web/java/mvc-controller-interface"]
   :extension "java"
   :subdir "src"
   :stereotypes "mvc controller"
   }
  {:element "Class"
   :template "java/class"
   :includes ["lib" "model/lib" "java/lib" "java/class" "common/model/lib" "common/java/lib"
              "common/java/class" "presentation/model/lib" "presentation/java/lib" "presentation/web/java/mvc-controller-class"]
   :prefix "Abstract"
   :extension "java"
   :namespaceSuffix "impl"
   :subdir "src"
   :stereotypes "mvc controller"}
  ; JSPs
  {:element "Class"
   :template "jsp/jsp"
   :includes ["lib" "model/lib" "jsp/jsp" "html/html4" "common/model/lib"
              "presentation/model/lib" "presentation/web/jsp/swf" "presentation/web/jsp/spring-mvc" "presentation/web/jsp/crud"]
   :baseNamespace "views"
   :extension "jsp"
   :subdir "WebContent/WEB-INF"
   :stereotypes "view"}
  ; Spring webflow 2
  {:element "StateMachine"
   :template "xml/xml"
   :includes ["lib" "model/lib" "xml/lib" "java/lib" "common/model/lib"
              "presentation/web/config/webflow"]
   :extension "xml"
   :subdir "WebContent/WEB-INF"
   :protectedArea "PA"
   :stereotypes "flow"}
  ; spring configs for deployment unit
  {:element "Package" ; Spring web context configuration (TODO use Package or Model?)
   :template "xml/xml"
   :includes ["lib" "model/lib" "xml/lib" "common/model/lib" "common/config/spring-config"
              "presentation/web/config/mvc-config"] ; add web-flow, ...
   :baseName "dispatcher-servlet"
   :extension "xml"
   :baseNamespace "WEB-INF"
   :subdir "WebContent"
   :stereotypes "web frontend"
   }
  {:element "Package" ; Spring application context configuration (TODO use Package or Model?)
   :template "xml/xml"
   :includes ["lib" "model/lib" "xml/lib" "common/model/lib" "common/config/spring-config"
              "common/config/jpa-config" "common/config/h2-config"
              "frontend/config/jpa-config" "webfrontend/config/spring-config"]
   :baseName "application-context"
   :extension "xml"
   :baseNamespace "WEB-INF"
   :subdir "WebContent"
   :stereotypes "web frontend"
   }
  {:element "Package" ; component config (TODO needed?)
   :template "xml/xml"
   :includes ["lib" "model/lib" "xml/lib" "java/lib" "common/model/lib" "common/xml/lib"
              "common/config/spring-config" "presentation/web/config/spring-config"
              "presentation/web/config/mvc-config" "presentation/web/config/webflow-config"]
   :baseName "component-config"
   :extension "xml"
   :subdir "src"
   :stereotypes "component"
   }
  ; TODO property files
  {:element "Package" ; web.xml
   :template "xml/xml"
   :includes ["lib" "model/lib" "xml/lib" "common/model/lib" "presentation/web/config/web-xml"
              "presentation/web/config/mvc-web-xml" "presentation/web/config/webflow-web-xml"]
   :baseName "web"
   :extension "xml"
   :baseNamespace "WEB-INF"
   :subdir "WebContent"
   :stereotypes "web frontend"
   }]

 :web-service-generators ; generation of web service artifacts
 [{:element "Class"
   :template "java/class"
   :includes ["lib" "model/lib" "java/lib" "java/class" "common/model/lib" "common/java/lib"
             "common/java/class" "common/java/profiling" "integration/web-service/lib"
             "integration/web-service/server/java/annotation-endpoint"]
   :prefix "Abstract"
   :extension "java"
   :subdir "src"
   :stereotypes "ws endpoint"}
  ; server side types
  {:element "Class"
   :template "java/interface"
   :includes ["lib" "model/lib" "java/lib" "java/interface" "java/bean-interface"
             "common/model/lib" "common/java/lib" "integration/web-service/lib"
             "integration/web-service/server/java/type-interface"]
   :extension "java"
   :subdir "src"
   :stereotypes "ws type"}
  {:element "Class"
   :template "java/class"
   :includes ["lib" "model/lib" "java/lib" "java/class" "java/bean-class" "common/model/lib"
              "common/java/lib" "common/java/mapping-class" "integration/web-service/lib"
              "integration/web-service/server/java/type-class"]
   :suffix "Impl"
   :extension "java"
   :namespaceSuffix "impl"
   :subdir "src"
   :stereotypes "ws type"}
  ; server side marshalling
  {:element "Package"
   :template "java/interface"
   :includes ["lib" "model/lib" "java/lib" "java/interface" "common/model/lib" "common/java/lib"
             "integration/web-service/lib" "integration/web-service/java/marshaller-interface"]
   :baseName "Marshaller"
   :extension "java"
   :useNameAsNamespace true
   :subdir "src"
   :stereotypes "web service"}
  {:element "Package"
   :template "java/class"
   :includes ["lib" "model/lib" "java/lib" "java/class" "common/model/lib" "common/java/lib"
             "integration/web-service/lib" "integration/web-service/java/marshaller-class"
             "integration/web-service/java/simple-marshalling"]
   :baseName "MarshallerImpl"
   :extension "java"
   :useNameAsNamespace true
   :namespaceSuffix "impl"
   :subdir "src"
   :stereotypes "web service"}
  ; server spring configuration
  {:element "Package" ; spring component config
   :template "xml/xml"
   :includes ["lib" "model/lib" "xml/lib" "java/lib" "common/model/lib" "common/xml/lib"
             "common/config/spring-config" "integration/web-service/lib"
             "integration/web-service/server/config/spring-config"]
   :baseName "component-config"
   :extension "xml"
   :useNameAsNamespace true
   :subdir "src"
   :stereotypes "web service"}
  {:element "Package" ; spring application config
   :template "xml/xml"
   :includes ["lib" "model/lib" "xml/lib" "java/lib" "common/model/lib" "common/xml/lib"
              "common/config/spring-config" "common/config/jpa-config"
              "common/config/h2-config" "frontend/config/jpa-config"]
   :baseName "application-context"
   :extension "xml"
   :baseNamespace "WEB-INF"
   :subdir "WebContent"
   :stereotypes "web service"}
  ; server web xml
  {:element "Package"
   :template "xml/xml"
   :includes ["lib" "model/lib" "xml/lib" "common/model/lib" "presentation/web/config/web-xml"
             "integration/web-service/lib" "integration/web-service/server/config/web-xml"]
   :baseName "web"
   :extension "xml"
   :subdir "WebContent"
   :baseNamespace "WEB-INF"
   :stereotypes "web service"}
  ; TODO spring config for deployment unit (db" " ...)
  ; TODO property files
  ; client side types
  {:element "Class"
   :template "java/interface"
   :includes ["lib" "model/lib" "java/lib" "java/interface" "java/bean-interface"
             "common/model/lib" "common/java/lib" "integration/web-service/lib"
             "integration/web-service/client/java/type-interface"]
   :extension "java"
   :namespaceSuffix "client"
   :subdir "src"
   :stereotypes "ws type"}
  {:element "Class"
   :template "java/class"
   :includes ["lib" "model/lib" "java/lib" "java/class" "java/bean-class" "common/model/lib"
              "common/java/lib" "integration/web-service/lib"
              "integration/web-service/client/java/type-class"]
   :suffix "Impl"
   :extension "java"
   :namespaceSuffix "client.impl"
   :subdir "src"
   :stereotypes "ws type"}
  ; client side marshalling
  {:element "Package"
   :template "java/interface"
   :includes ["lib" "model/lib" "java/lib" "java/interface" "common/model/lib" "common/java/lib"
             "integration/web-service/lib" "integration/web-service/java/marshaller-interface"]
   :baseName "Marshaller"
   :extension "java"
   :namespaceSuffix "client"
   :useNameAsNamespace true
   :subdir "src"
   :stereotypes "web service"}
  {:element "Package"
   :template "java/class"
   :includes ["lib" "model/lib" "java/lib" "java/class" "common/model/lib" "common/java/lib"
             "integration/web-service/lib" "integration/web-service/java/marshaller-class"
             "integration/web-service/java/simple-marshalling"]
   :baseName "MarshallerImpl"
   :extension "java"
   :namespaceSuffix "client.impl"
   :useNameAsNamespace true
   :subdir "src"
   :stereotypes "web service"}
  ; client spring configuration
  {:element "Package"
   :template "xml/xml"
   :includes ["lib" "model/lib" "xml/lib" "java/lib" "common/model/lib" "common/xml/lib"
             "common/config/spring-config" "integration/web-service/lib"
             "integration/web-service/client/config/spring-config"]
   :baseName "component-config"
   :extension "xml"
   :useNameAsNamespace true
   :namespaceSuffix "client"
   :subdir "src"
   :stereotypes "web service"}
  {:element "Package"
   :template "xml/xml"
   :includes ["lib" "model/lib" "xml/lib" "xml/schema" "common/model/lib" "common/xml/lib"
             "common/xml/schema" "integration/web-service/lib"
             "integration/web-service/server/config/doc-schema"]
   :extension "xsd"
   :baseNamespace "schema"
   :subdir "WebContent/WEB-INF"
   :stereotypes "web service"}
  ]
 }