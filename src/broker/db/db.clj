(ns broker.db.db 
  "database connection"
  (:gen-class))

(def db
  {:subname "//db:5432/postgres"
   :host "db"
   :port "5432"
   :dbname "postgres"
   :subprotocol "postgres"
   :dbtype "postgres"
   :user "postgres"
   :password "postgres"})

