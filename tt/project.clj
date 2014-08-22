(defproject tt "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [
                 [org.clojure/clojure "1.6.0"]
                 [ring/ring-core "1.2.1"]
                 [ring/ring-json "0.3.1"]
                 [ring-cors "0.1.4"]

                 [compojure "1.1.8"]

                 [liberator "0.10.0"]
                 ]
  :plugins [[lein-ring "0.8.11"]]
  :ring {:handler tt.handler/app :port 9090}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}})
