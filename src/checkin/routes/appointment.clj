(ns checkin.routes.appointment
  (:use [compojure.core]
        [clojure.walk])
  (:require [cheshire.core :as parse]
            [ring.util.response :as response]))

(defn get [request]
  (println "get appointment request")
  "My Appointment Detail"
  )

(defn add [request]
  (println "add appointment request"))