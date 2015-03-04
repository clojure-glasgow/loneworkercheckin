(ns checkin.permissions-test
   (:require [midje.sweet :refer :all]
             [checkin.permissions :refer :all]))

(def some-user {:friends {"email1@example.com" #{:view-appointments}
                          "email2@example.com" #{}
                          "email3@example.com" #{:view-appointments :notify}
                          "email4@example.com" #{:notify}}})

(facts "about permissions"
      (fact "list of people getting notified only includes those with permission"
            (addresses-to-notify some-user) => {"email3@example.com" #{:view-appointments :notify}
                                                "email4@example.com" #{:notify}}))
