(ns checkin.email-management-test
  (:require [midje.sweet :refer :all]
            [checkin.email-management :refer :all]))

(fact "When I add an email address it should be valid and return 201"
      (email/add-email "ballbag@example.com") => [201]

      )

(fact "When I add an email address that is invalid, it should return 500"
      (email/add-email "blahblah" ) => [500]
      (email/add-email "ballbag#example.com") => [500]
      (email/add-email "") => [500]
      )

