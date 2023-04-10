(ns broker.logic.validation
  (:require [clojure.string :as str]
            [compojure.core :refer :all])
  (:import java.util.UUID)
  (:import java.util.Date)
  (:gen-class))

(defn validate-broker
  "validate if a broker has all the required data"
  [broker-map]
  (let [valid-keys #{:first_name :last_name}] 
    (every?
      (fn [[k v]]
        (and (not (empty? (str/trim v)))
             (valid-keys k) 
             (string? v) 
             (<= 2 (count v) 20))) 
      broker-map)))

(defn validate-quote
  "validate if a quote has all the required data"
  [quote-map]
  (let [valid-keys #{:age :sex}
        age (:age quote-map)
        sex (:sex quote-map)]
    (and (number? age)
         (>= age 0)
         (<= age 99)
         (string? sex)
         (some #{"f" "F" "m" "M" "n" "N"} (str/lower-case sex)))
    true))

(defn validate-policy
  "validate if a policy has all the required data"
  [policy-map]
  (let [valid-keys #{:name :sex :date_of_birth}
        name (:name policy-map)
        sex (:sex policy-map)
        date_of_birth (:date_of_birth policy-map)
        current-year (-> (java.util.Date.) .getYear (+ 1900))
        date-of-birth-year (-> (java.time.LocalDate/parse date_of_birth) .getYear)]
    (and (< (- current-year date-of-birth-year) 99)
         (string? name)
         (string? sex)
         (some #{"f" "F" "m" "M" "n" "N"} (str/lower-case sex)))
    true))



