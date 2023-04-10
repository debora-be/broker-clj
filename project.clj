(defproject broker "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"] 
                 [org.postgresql/postgresql "42.2.2"]
                 [compojure "1.7.0"] 
                 [http-kit "2.6.0"]
                 [ring "1.9.6"]
                 [ring/ring-defaults "0.3.2"]
                 [ring/ring-json "0.5.0"]
                 [cheshire "5.11.0"]
                 [environ "1.2.0"]
                 [org.clojure/tools.logging "0.2.3"]
                 [log4j/log4j "1.2.16"]
                 [funcool/clojure.jdbc "0.9.0"] 
                 [org.clojure/java.jdbc "0.7.12"]]
  :plugins [[lein-environ "1.2.0"]]
  :main ^:skip-aot broker.core
  :uberjar-name "clojure-broker"
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
