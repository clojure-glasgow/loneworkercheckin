(ns checkin.profile-test
(:require [midje.sweet :refer :all]
          [checkin.profile :refer :all]))

(def default-contacts {"contact1@email.com" #{}})
(def default-appointments '())

(def default-profile
  {
     :userkey      "default-user-key"
     :name         "default"
     :email        "default@email.com"
     :contacts     default-contacts
     :appointments default-appointments})



(facts "manipulating a profile"

       (fact "should creates and returns a boiler plate data map of profile"
               (create-profile "user-key" "username" "username@email.com")
               => { :userkey  "user-key"
                    :contacts {}
                    :appointments '()
                    :email "username@email.com"
                    :name "username" })

       (fact "should fetch the contacts from a profile"
             (get-contacts-from-profile default-profile)
             => default-contacts)
       
       (fact "should fetch the list of appointments from a profile"
             (get-appointments-from-profile default-profile)
             => default-appointments)
       
       (fact "should take a contact and a profile and return a new map containing the original profile data and the contact"
             (add-contact-to-profile default-profile "test@email.com" #{})
             => {
                   :userkey      "default-user-key"
                   :name         "default"
                   :email        "default@email.com"
                   :contacts     {"test@email.com" #{}, "contact1@email.com" #{}}
                   :appointments '()})

       (future-fact "should take an appointment and a profile and return a new map containing the original profile data and the appointment"
             (add-contact-to-profile default-profile "test@email.com" #{})
             => {
                   :userkey      "default-user-key"
                   :name         "default"
                   :email        "default@email.com"
                   :contacts     {"test@email.com" #{}, "contact1@email.com" #{}}
                   :appointments '()})

       )



