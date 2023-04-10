(ns broker.core
  (:require [org.httpkit.server :as server]
            [org.httpkit.client :as http]
            [compojure.core :refer :all]
            [compojure.route :as route] 
            [ring.adapter.jetty :as ring]
            [ring.middleware.defaults :refer :all]
            [ring.middleware.json :as mj] 
            [clojure.pprint :as pp]
            [clojure.string :as str] 
            [clojure.tools.logging :as log]
            [broker.handlers :refer :all]
            [broker.logic.database :as b]
            [broker.http :as api-client])
  (:gen-class))

(def opts {:keywords? true :bigdecimals? true})

(defroutes app-routes 
  (POST "/brokers" [] (mj/wrap-json-body create-broker-handler opts)) 
  (POST "/brokers/:broker_id/quotes" [] (mj/wrap-json-body create-quote-handler opts))
  (POST "/brokers/:broker_id/policies" [] (mj/wrap-json-body create-policy-handler opts))
  (GET "/brokers/:broker_id/policies" [] (mj/wrap-json-params get-policy-handler))
  (route/not-found "page not found!"))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [port 3000]
    (server/run-server  (wrap-defaults #'app-routes api-defaults) {:port port})
    (println (str "Running service on port " port))))