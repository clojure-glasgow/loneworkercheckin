(ns checkin.middleware-test
  (:require [midje.sweet :refer :all]
            [checkin.middleware :refer :all]
            [clojure.tools.logging :as log]))

(facts "logging"
       (fact "requests at info"
             (let [response {}
                   handler (log-request (constantly response))]
               (handler {:uri "/"}) => response
               (provided (log/log* anything
                                   :info
                                   nil
                                   anything) => anything))))
