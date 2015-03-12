(ns checkin.profile-handler 
  (:use [compojure.core]
        [clojure.walk])
  (:require [cheshire.core :as parse]
            [ring.util.response :as response]
            [net.cgrand.enlive-html :as enlive]
            [checkin.profile :as profile]
            [checkin.userprofiles :as profiles]
            [checkin.request-helper :as request-helper]))

(defn transform [profile]
  (enlive/at (enlive/html-resource "userprofile.html")
             [:li#profile-name]  (enlive/content (:name  profile))
             [:li#profile-email] (enlive/content (:email profile)) ))

(defn create [req name email]
  (let [profile
    (-> (request-helper/get-user-id req)
        (profile/create-profile name email))]
    (profiles/upsert-profiles profile)
    (-> (transform profile)
        (enlive/emit*))))