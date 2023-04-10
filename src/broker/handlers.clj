(ns broker.handlers
  (:require [org.httpkit.server :as server]
            [org.httpkit.server :as client]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer :all]
            [cheshire.core :as cheshire] 
            [clojure.tools.logging :as log]
            [broker.logic.database :as d]
            [broker.logic.validation :as v]
            [broker.http :as api-client])
  (:gen-class))

(defn create-broker-handler
  "handler to post a broker"
  [request]
  (let [broker-map (:body request)
        saved (try
                (d/create-broker broker-map)
                (catch Exception e
                  (do
                    (log/error e)
                    false)))]
    (log/info {:broker-map broker-map
               :saved saved})
    {:status (if saved 201 400)
     :headers {"Content-Type" "application/json"}
     :body (if saved
             saved
             (cheshire/generate-string {:error "could not save"}))}))

(defn create-quote-handler
  "handler to post a quote"
  [request]
  (let [quote-map (:body request)
        is-valid (v/validate-quote quote-map)
        broker-id (-> request
                      :params
                      :broker_id)
        external-call (api-client/post-quotation quote-map)
        saved (when is-valid
                (try
                  (d/create-quote external-call broker-id)
                  (catch Exception e
                    (do
                      (log/error e)
                      false))))]
    (log/info {:quote-map quote-map
               :broker-id broker-id
               :external-call external-call
               :saved saved})
    {:status (if saved 201 400)
     :headers {"Content-Type" "application/json"}
     :body (if saved
             saved
             (cheshire/generate-string {:error "could not save"}))}))

(defn create-policy-handler
  "handler to post a policy"
  [request]
  (let [policy-map (:body request)
        is-valid (v/validate-policy policy-map)
        broker-id (-> request
                      :params
                      :broker_id)
        external-call (api-client/post-policy policy-map)
        saved (when is-valid
                (try
                  (d/create-policy external-call broker-id)
                  (catch Exception e
                    (do
                      (log/error e)
                      false))))]
    (log/info {:policy-map policy-map
               :broker-id broker-id
               :external-call external-call
               :saved saved})
    {:status (if saved 201 400)
     :headers {"Content-Type" "application/json"}
     :body (if saved
             saved
             (cheshire/generate-string {:error "could not save"}))}))

(defn get-policy-handler
  [request]
  "handler to get a policy by id"
  (let [broker-id (-> request
                      :params
                      :broker_id)
        policy-id (-> request
                      :params
                      :policy_id)
        external-call (try
                        (api-client/get-policy policy-id)
                        (catch Exception e
                          (do
                            (log/error e)
                            false)))]
    (log/info {:broker-id broker-id
               :policy-id policy-id
               :external-call external-call})
    {:status (if external-call 200 400)
     :headers {"Content-Type" "application/json"}
     :body (if external-call
             (cheshire/generate-string external-call)
             (cheshire/generate-string {:error "could not retrieve policy"}))}))