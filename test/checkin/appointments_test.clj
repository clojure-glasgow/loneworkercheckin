(ns checkin.appointments-test
  (:require [midje.sweet :refer :all]
            [checkin.handler :refer :all]
            [checkin.appointment :as app]
            [clj-time.core :as tcore]
            [clj-time.format :as tformat]))

(facts "testing the display of appointment details"
       (fact "true equals true"
             true => true)

       (fact "What time is it mr wolf?"
             (tcore/in-years
               (tcore/interval
                 (tformat/parse "2015-09-01")
                 (tformat/parse "2016-08-31")
                 )) => 0)

       (future-fact "ensuring the title is displayed" 
             (:content (app/transform)) => "Working lonely in dodgy area")
       
)
