(ns broker.http
  "HTTP client for accessing external data"
  (:require [org.httpkit.client :as client]
            [cheshire.core :as cheshire]
            [environ.core :refer [env]])
  (:gen-class))

(def base-url (env :base-url))

(def x (env :x))

(def token (env :token))

(def options {:headers {"Authorization" token
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
  (process quote-map "/quotations"))

(defn post-policy 
  "create a new policy"
  [policy-map]
  (process policy-map "/policies"))

(defn get-policy 
  "get a policy by id"
  [policy-id]
  (let [{:keys [status headers body error] :as resp}
        @(client/get (str base-url "/policies/" policy-id) options)]
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
