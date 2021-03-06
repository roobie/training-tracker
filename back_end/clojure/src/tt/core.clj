(ns tt.core
  (:require [liberator.core :refer [resource defresource]]
            [liberator.representation :refer [ring-response]]

            [compojure.core :refer [defroutes ANY]]

            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.cors :refer [wrap-cors]]
            [ring.middleware.format :refer [wrap-restful-format]]

            [clojure.java.io :as io]
            [clojure.data.json :as json])
  (:import java.net.URL))

(defn get-body
  [context]
  (json/read
   (io/reader
    (get-in context [:request :body]))))

;; convert the body to a reader. Useful for testing in the repl
;; where setting the body to a string is much simpler.
(defn body-as-string [ctx]
  (if-let [body (get-in ctx [:request :body])]
    (condp instance? body
      java.lang.String body
      (slurp (io/reader body)))))

;; For PUT and POST parse the body as json and store in the context
;; under the given key.
(defn parse-json [context key]
  (when (#{:put :post} (get-in context [:request :request-method]))
    (try
      (if-let [body (body-as-string context)]
        (let [data (json/read-str body)]
          [false {key data}])
        {:message "No body"})
      (catch Exception e
        (.printStackTrace e)
        {:message (format "IOException: %s" (.getMessage e))}))))

;; For PUT and POST check if the content type is json.
(defn check-content-type [ctx content-types]
  (if (#{:put :post} (get-in ctx [:request :request-method]))
    (or
     (some #{(get-in ctx [:request :headers "content-type"])}
           content-types)
     [false {:message "Unsupported Content-Type"}])
    true))

;; we hold a entries in this ref
(defonce entries (ref {}))

;; a helper to create a absolute url for the entry with the given id
(defn build-entry-url [request id]
  (URL. (format "%s://%s:%s%s/%s"
                (name (:scheme request))
                (:server-name request)
                (:server-port request)
                (:uri request)
                (str id))))

(def resource-defaults
  :handle-options (ring-response
                   {:headers {"Access-Control-Allow-Origin" "*"
                              "Access-Control-Allow-Headers" "Content-Type"}}))

;; create and list entries
(defresource list-resource resource-defaults
  :available-media-types ["application/json"]
  :allowed-methods [:get :post :options]
  :known-content-type? #(check-content-type % ["application/json"])
  :malformed? #(parse-json % ::data)
  :post! #(let [id (str (inc (rand-int 100000)))]
            (dosync (alter entries assoc id (::data %)))
            {::id id})
  ;:post-redirect? false
  ;:location #(build-entry-url (get % :request) (get % ::id))
  :handle-created (ring-response
                   {:headers {"Access-Control-Allow-Origin" "*"
                              "Access-Control-Allow-Headers" "Content-Type"}})
  :handle-ok (ring-response
              {:headers {"Access-Control-Allow-Origin" "*"
                         "Access-Control-Allow-Headers" "Content-Type"}}
              :body #(map (fn [id] (str (build-entry-url (get % :request) id)))
                          ;(keys @entries)
                          entries)))

(defresource entry-resource [id]
  :allowed-methods [:get :put :delete :options]
  :known-content-type? #(check-content-type % ["application/json"])
  :exists? (fn [_]
             (let [e (get @entries id)]
               (if-not (nil? e)
                 {::entry e})))
  :existed? (fn [_] (nil? (get @entries id ::sentinel)))
  :available-media-types ["application/json"]
  :handle-ok ::entry
  :delete! (fn [_] (dosync (alter entries assoc id nil)))
  :malformed? #(parse-json % ::data)
  :can-put-to-missing? false
  :put! #(dosync (alter entries assoc id (::data %)))
  :new? (fn [_] (nil? (get @entries id ::sentinel))))

(defroutes app
  (ANY "/" [] (resource :available-media-types ["*"]
                        :handle-ok "Welcome to TrainingTracker API."))
  (ANY "/ping" [] (resource :available-media-types ["*"]
                            :allowed-methods [:post :put :get :delete]
                            :handle-ok "pong"))

  ;; detail
  ;; (ANY "/collection/:id" [id] (entry-resource id))
  ;; list
  ;; (ANY "/collection" [] list-resource)

  (ANY "/v1/movements/:id" [id] (entry-resource id))
  (ANY "/v1/movements" [] list-resource)
  )



(defn wrap-cors-simple
  "Allow requests from all origins"
  [handler]
  (fn [request]
    (let [response (handler request)]
      (update-in response
                 [:headers "Access-Control-Allow-Origin"]
                 (fn [_] "*")))))

(def handler
  (-> app wrap-params wrap-cors-simple)
  ;(-> app (wrap-restful-format) )
  )

;(wrap-cors handler :access-control-allow-origin #"http://localhost:9000")

(defn main
  []
  "thx")
