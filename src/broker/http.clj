(ns broker.http
  "HTTP client for accessing external data"
  (:require [org.httpkit.client :as client]
            [cheshire.core :as cheshire]
            [environ.core :refer [env]])
  (:gen-class))

(def base-url (env :base-url))

(def x (env :x))

(def options {:headers {"X-Api-Key" x
                        "Content-Type" "application/json"}})

(defn process 
  "process the given map to its path"
  [map path]
  (let [{:keys [status headers body error] :as resp}
        @(client/post (str base-url path)
                      (merge options {:body (cheshire/generate-string map)}))]
    (if error
      error
      (cheshire/decode body))))

(defn post-quotation 
  "create a new quotation"
  [quote-map]
  (process quote-map "/quotations.json"))

(defn post-policy 
  "create a new policy"
  [policy-map]
  (process policy-map "/policies.json"))

(defn get-policy 
  "get a policy by id"
  [policy-id]
  (let [{:keys [status headers body error] :as resp}
        @(client/get (str base-url "/get_policies/" policy-id ".json") options)]
    (if error
      error
      (cheshire/decode body))))

(defn auth
  []
  (let [{:keys [status headers body error] :as resp}
        @(client/post (str base-url "/auth")
                      (merge options {:headers {"X-Api-Key" x}}))]
    (if error
      error
      (cheshire/decode body))))
