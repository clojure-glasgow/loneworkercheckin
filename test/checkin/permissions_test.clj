(ns checkin.permissions-test
   (:require [midje.sweet :refer :all]
             [checkin.permissions :refer :all]))

(def some-user {:friends {"email1@example.com" #{:include-appointments}
                          "email2@example.com" #{}
                          "email3@example.com" #{:include-appointments}
                          "email4@example.com" #{:notify}}})

(facts "about permissions"
      (fact "list of people getting notified doesn't include people with empty permissions"
            (addresses-to-notify some-user) => {"email1@example.com" #{:include-appointments}
                                                "email3@example.com" #{:include-appointments}
                                                "email4@example.com" #{:notify}}))
