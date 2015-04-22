(ns checkin.email-management-test
  (:require [midje.sweet :refer :all]
            [checkin.handler :refer :all]
            [cemerick.friend :as friend]
            [checkin.email-management :refer :all]
            [checkin.userprofiles :as userprofiles]))

(defn setup-default-profile []
  (userprofiles/upsert-profiles {:userkey      "default-user-key"
                                   :name         "default"
                                   :email        "default@email.com"
                                   :contacts     {}
                                   :appointments '()}))

(facts "about email routes"
       (future-fact "should return 200 on add email page"
             (setup-default-profile)
             (:status (app {:uri            "/email/new"
                            :request-method :get})) => 200
             (provided (friend/identity anything) => {:current {:user-name "default-user-key"}}))

       (facts "about email routes"
              (future-fact "should return 201 on add email"
                    (setup-default-profile)
                    (:status (add {:params {:email "andy@example.com"}})) => 201
                    (provided (friend/identity anything) => {:current "default-user-key"}))
              )

       (facts "about email routes"
              (future-fact "should return 422 on add email with invalid email"
                    (setup-default-profile)
                    (:status (add {:params {:email "broken"}})) => 422
                    (provided (friend/identity anything) => {:current "default-user-key"})))
       )