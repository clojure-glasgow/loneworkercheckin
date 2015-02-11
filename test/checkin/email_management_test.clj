(ns checkin.email_management_test
  (:require [clojure.test :refer :all]
            [checkin.core :refer :all]))

(deftest shouldReturn201WhenAddingAnEmailAddress
  (let [response (home {})]
    (testing "success"
      (is (= 200 (:status response))))))

