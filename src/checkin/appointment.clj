(ns checkin.appointment
  (:require [net.cgrand.enlive-html :as enlive]))

(def appointment {:title "Working lonely in dodgy area"})

(defn transform []
  (enlive/at (enlive/html-resource "appointment.html")
             [:span] (enlive/content (appointment :title))))

(defn render [request]
  (enlive/emit* (transform)))

(defn add [request]
  (println "add appointment request"))
