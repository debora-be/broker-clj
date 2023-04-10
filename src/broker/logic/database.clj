(ns broker.logic.database
  "logic for database interactions"
  (:require [broker.db.db :refer :all]
            [clojure.java.jdbc :as jdbc]
            [cheshire.core :as cheshire]
            [clojure.string :as str]
            [broker.logic.validation :as v])
  (:gen-class)
  (:import java.util.UUID)
  (:import java.sql.Timestamp))

(defn create-broker
  "save a new broker to the database"
  [broker-map]
  (let [is-valid (v/validate-broker broker-map)
        broker {:broker_id (java.util.UUID/randomUUID)
                :first_name (broker-map :first_name)
                :last_name (broker-map :last_name)
                :created_at (java.sql.Timestamp. (System/currentTimeMillis))}]
    (when is-valid
      (->> broker
           (jdbc/insert! db "brokers")
           (cheshire/generate-string)))))

(defn create-quote
  "save a new quote to the database"
  [external-call broker-id]
  (let [quote {:broker_id broker-id
               :expire_at (external-call "expire_at")
               :price (external-call "price")
               :quotation_id (external-call "id")
               :created_at (java.sql.Timestamp. (System/currentTimeMillis))}]
    (->> quote
         (jdbc/insert! db "quotes")
         (cheshire/generate-string))))

(defn create-policy
  "save a new policy to the database"
  [external-call broker-id]
  (let [policy {:broker_id broker-id
                :name (external-call "name")
                :sex (external-call "sex")
                :date_of_birth (external-call "date_of_birth")
                :policy_id (external-call "id")
                :created_at (java.sql.Timestamp. (System/currentTimeMillis))}]
    (->> policy
         (jdbc/insert! db "policies")
         (cheshire/generate-string))))
