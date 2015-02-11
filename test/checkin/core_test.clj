(ns checkin.core-test
  (:require [midje.sweet :refer :all]
            [checkin.handler :refer :all]))

(facts "about http response codes"
       (fact "should return 200 on home page"
             (:status (app {:uri "/"
                   :request-method :get})) => 200))
