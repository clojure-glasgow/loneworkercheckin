(ns checkin.session-helper
    (:require [cemerick.friend :as friend]))

(defn get-user-id [request]
(let [session (:session request)
     user-identity (:cemerick.friend/identity session)
     user-id (:current user-identity)]
    user-id))

(defn get-user-name [request]
(let [session (:session request)
     user-identity (:cemerick.friend/identity session)
     user-id (:current user-identity)
     user-name (get-in user-identity [:authentications user-id :user-name])]
     user-name))
