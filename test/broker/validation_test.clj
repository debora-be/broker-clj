(ns broker.validation-test
  (:require [clojure.test :refer :all]
            [broker.logic.validation :refer :all]))

(deftest validate-broker-test
  (testing "should be valid"
    (is (true? (validate-broker {:first_name "Ruby"
                                 :last_name  "Tuesday"}))))

  (testing "should fail when there is no first_name"
    (is (false? (validate-broker {:first_name ""
                                  :last_name  "Tuesday"}))))
  
  (testing "should fail when first_name is too long"
    (is (false? (validate-broker {:first_name "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                                  :last_name  "Tuesday"}))))
  
  (testing "should fail when first_name is too short"
    (is (false? (validate-broker {:first_name "a"
                                  :last_name  "Tuesday"}))))
  (testing "should fail when last_name is too long"
    (is (false? (validate-broker {:first_name "Ruby"
                                  :last_name  "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"}))))

(testing "should fail when last_name is too short"
  (is (false? (validate-broker {:first_name "Ruby"
                                :last_name  "a"}))))

  (testing "should fail when there is no last_name"
    (is (false? (validate-broker {:first_name "Ruby"
                                  :last_name  ""})))))

(deftest validate-quote-test
  (testing "should be valid"
    (is (true? (validate-quote {:age 29
                                :sex  "m"}))))

  (testing "should fail when age is not valid"
    (is (false? (validate-quote {:age 115
                                 :sex  "m"}))))

  (testing "should fail when age is not under zero"
     (is (false? (validate-quote {:age -2
                                 :sex  "m"}))))
  
  (testing "should fail when sex is empty"
    (is (false? (validate-quote {:age 115
                                 :sex  ""})))))

(deftest validate-policy-test
  (testing "should be valid"
    (is (true? (validate-policy {:quotation_id "f8f9cb1c-a964-4eb4-b19a-4a4cf8fb2fbe",
                                 :name "Mighty Maria",
                                 :sex "f",
                                 :date_of_birth "1993-06-26"}))))
  
  (testing "should fail when name is too short"
    (is (false? (validate-policy {:quotation_id "f8f9cb1c-a964-4eb4-b19a-4a4cf8fb2fbe",
                                 :name "a",
                                 :sex "f",
                                 :date_of_birth "1993-06-26"}))))

  (testing "should fail when name is too long"
    (is (false? (validate-policy {:quotation_id "f8f9cb1c-a964-4eb4-b19a-4a4cf8fb2fbe",
                                  :name "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                                  :sex "f",
                                  :date_of_birth "1993-06-26"}))))
  
  (testing "should fail when name is empty"
    (is (false? (validate-policy {:quotation_id "f8f9cb1c-a964-4eb4-b19a-4a4cf8fb2fbe",
                                  :name "",
                                  :sex "f",
                                  :date_of_birth "1993-06-26"}))))
  
  (testing "should fail when sex is empty"
    (is (false? (validate-policy {:quotation_id "f8f9cb1c-a964-4eb4-b19a-4a4cf8fb2fbe",
                                  :name "Mighty Maria",
                                  :sex "",
                                  :date_of_birth "1993-06-26"}))))
  
  (testing "should fail when date_of_birth is not valid"
    (is (false? (validate-policy {:quotation_id "f8f9cb1c-a964-4eb4-b19a-4a4cf8fb2fbe",
                                  :name "Mighty Maria",
                                  :sex "f",
                                  :date_of_birth "1893-06-26"})))))
