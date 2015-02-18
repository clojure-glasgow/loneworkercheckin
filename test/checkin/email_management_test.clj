(ns checkin.email-management-test
  (:require [midje.sweet :refer :all]
            [checkin.email-management :refer :all]))



(future-fact "When I add an email address it should be valid and return 201"
      (add-email "ballbag@example.com") => [201]
      (add-email "ballbag#example.com") => [500]
      )
