(ns checkin.request-helper
    (:require [cemerick.friend :as friend]))

(defn- get-user-identity [request]
(let [session (:session request)
     user-identity (:cemerick.friend/identity session)]
     user-identity))

(defn get-user-id [request]
(let [user-id (:current (get-user-identity request))]
    user-id))

(defn get-user-name [request]
(let [user-name (get-in (get-user-identity request) [:authentications (get-user-id request) :user-name])]
     user-name))


