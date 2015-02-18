(ns checkin.routes.appointment
  (:use [compojure.core]
        [clojure.walk])
  (:require [cheshire.core :as parse]
            [ring.util.response :as response]
            [net.cgrand.enlive-html :as enlive]))

(def appointment {:title "Working lonely in dodgy area"})

(defn transform []
  (enlive/at (enlive/html-resource "appointment.html")
      [:span] (enlive/content (appointment :title))))

(defn get [request]
   (enlive/emit* (transform)))

(defn add [request]
  (println "add appointment request"))
