(ns tt.core
  (:require [liberator.core :refer [resource defresource]]
            [ring.middleware.params :refer [wrap-params]]
            [compojure.core :refer [defroutes ANY]]))

(defroutes app
  (ANY "/" [] (resource)))

(defroutes app
  (ANY "/foo" [] (resource :available-media-types ["text/html"]
                           :handle-ok "<html>Hello, Internet.</html>")))

(def handler
  (-> app
      wrap-params))
