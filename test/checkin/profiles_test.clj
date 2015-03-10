(ns checkin.profiles-test 
  (:require [midje.sweet :refer :all]
            [checkin.userprofiles :refer :all]
            [checkin.profile-test :refer :all]))

(facts "profiles state"
       (fact "should add a new profile to a map of profiles"
             (upsert-profiles default-profile )
             => {"default-user-key" {
                                     :userkey      "default-user-key"
                                     :name         "default"
                                     :email        "default@email.com"
                                     :contacts     default-contacts
                                     :appointments default-appointments}}
             )

       (fact "should fetch the profile from the profiles map"
             (get-profile "default-user-key")
             => {
                 :userkey      "default-user-key"
                 :name         "default"
                 :email        "default@email.com"
                 :contacts     {"contact1@email.com" #{} }
                 :appointments '()
                 })

       )



