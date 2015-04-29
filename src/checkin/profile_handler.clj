(ns checkin.profile-handler 
  (:use [compojure.core]
        [clojure.walk])
  (:require [net.cgrand.enlive-html :as enlive]
            [checkin.profile :as profile]
            [checkin.userprofiles :as profiles]
            [checkin.request-helper :as request-helper]))

(defn transform [profile]
  (enlive/at (enlive/html-resource "userprofile.html")
             [:li#profile-name]  (enlive/content (:name  profile))
             [:li#profile-email] (enlive/content (:email profile)) ))

;This function would be much better if upsert-profiles returned a profile
(defn create [req name email]
  (let [profile
    (-> (request-helper/get-user-id req)
        (profile/create-profile name email))]
    (profiles/upsert-profiles profile)
    (-> (transform profile)
        (enlive/emit*))))