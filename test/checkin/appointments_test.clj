(ns checkin.appointments-test
  (:require [midje.sweet :refer :all]
            [checkin.handler :refer :all]
            [checkin.appointment :as app]))

(facts "testing the display of appointment details"
       (fact "true equals true"
             true => true)
       (future-fact "ensuring the title is displayed" 
             (:content (app/transform)) => "Working lonely in dodgy area")
)
