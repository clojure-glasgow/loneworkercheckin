(ns exampleapp.routes.facebook
  (:use compojure.core)
  (:require [clj-oauth2.client :as oauth2]
            [noir.response :as resp]
            [clj-http.client :as client]
            [cheshire.core :as parse]))

(def facebook-user
  (atom {:facebook-id "" :facebook-name "" :facebook-email ""}))

(def APP_ID "333189506871859")
(def APP_SECRET "1ac80cac123a3e9000b66f9f0b2cd8c9")
(def REDIRECT_URI "http://localhost:3000/auth_facebook")

(def facebook-oauth2
  {:authorization-uri "https://graph.facebook.com/oauth/authorize"
   :access-token-uri "https://graph.facebook.com/oauth/access_token"
   :redirect-uri REDIRECT_URI
   :client-id APP_ID
   :client-secret APP_SECRET
   :access-query-param :access_token
   :scope ["email"]
   :grant-type "authorization_code"})

(resp/redirect
  (:uri (oauth2/make-auth-request facebook-oauth2)))