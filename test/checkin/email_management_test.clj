(ns checkin.email_management_test
  (:use midje.sweet)
  (:require [clojure.test :refer :all]
            ))



(fact "When I add an email address it should be valid and return 201"
      (add-email "ballbag@example.com") => [201]
      (add-email "ballbag#example.com") => [500]
      )