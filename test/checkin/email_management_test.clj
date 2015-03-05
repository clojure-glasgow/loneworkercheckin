(ns checkin.email-management-test
  (:require [midje.sweet :refer :all]
            [checkin.handler :refer :all]))

(facts "about email routes"
       (fact "should return 200 on add email page"
             (:status (app {:uri            "/email/new"
                            :request-method :get})) => 200))

(facts "about email routes"
       (fact "should return 201 on add email"
             (:status (app {:uri            "/email"
                            :params         {:email "andy@example.com"}
                            :request-method :post})) => 201))

(facts "about email routes"
       (fact "should return 500 on add email with invalid email"
             (:status (app {:uri            "/email"
                            :params         {:email "broken"}
                            :request-method :post})) => 422))
