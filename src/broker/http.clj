(ns broker.http
  "HTTP client for accessing external data"
  (:require [org.httpkit.client :as client]
            [clojure.data.json :as json]
            [environ.core :refer [env]])
  (:gen-class))

(def base-url (env :base-url))

(def token (env :token))

(def options {:headers {"Authorization" token
                        "Content-Type" "application/json"}})

(defn process 
  "Process the given map to its path"
  [map path]
  (let [{:keys [status headers body error] :as resp}
        @(client/post (str base-url path)
                      (merge options {:body (json/write-str map)}))]
    (if error
      error
      (json/read-str body))))

(defn post-quotation 
  "Create a new quotation"
  [quote-map]
  (process quote-map "/quotations"))

(defn post-policy 
  "Create a new policy"
  [policy-map]
  (process policy-map "/policies"))

(defn get-policy 
  "Get a policy by id"
  [policy-id]
  (let [{:keys [status headers body error] :as resp}
        @(client/get (str base-url "/policies/" policy-id) options)]
    (if error
      error
      (json/read-str body))))