(ns checkin.core-test
  (:require [clojure.test :refer :all]
            [checkin.core :refer :all]))

(deftest shouldReturn200
  (let [response (home {})]
    (testing "success"
      (is (= 200 (:status response))))))

