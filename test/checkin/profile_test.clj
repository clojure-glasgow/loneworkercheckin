(ns checkin.profile-test
(:require [midje.sweet :refer :all]
          [checkin.profile :refer :all]))

(def some-user {:friends {"email1@example.com" #{:include-appointments}
                          "email2@example.com" #{}
                          "email3@example.com" #{:include-appointments}
                          "email4@example.com" #{:notify}}})

(def people {"profile1" some-user })

(facts "about changing profile state"
       (fact "should add a new profile to a map of profiles"
             (add-profile people {"newProfile" {}})
             => {"profile1" some-user "newProfile" {}}
             )
       )

