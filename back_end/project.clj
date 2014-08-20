(defproject
  training-tracker-back-end "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :plugins [[lein-ring "0.8.11" :port 9090]]
  :ring {:handler tt.core/handler}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [liberator "0.10.0"]
                 [compojure "1.1.3"]
                 [ring/ring-core "1.2.1"]])
