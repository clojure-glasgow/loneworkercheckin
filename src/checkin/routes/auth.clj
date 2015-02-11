(ns checkin.routes.auth
  (:use [compojure.core]
        [clojure.walk])
  (:require [clj-http.client :as client]
            [cheshire.core :as parse]
            [ring.util.response :as response]))

(def CLIENT_ID "eR08QvVSethRbm4lPQLEeg0lBgQXg4Wy")
(def CLIENT_SECRET "9jNUjATxDFl0FrkbeULttxc1UR1P0tLzBKa1EzPkvHXpSRKI_yof_CBcU1xBRTju")
(def REDIRECT_URI "http://localhost:3000/auth/callback")
(def ACCESS_TOKEN_URI "https://loneworker.auth0.com/oauth/token")
(def GRANT_TYPE "authorization_code")

(defn process-auth-response [params]
  (let [params-as-keys (keywordize-keys params)
        code (:code params-as-keys)
        post-body (str "client_id=" CLIENT_ID "&redirect_uri=" REDIRECT_URI "&client_secret=" CLIENT_SECRET "&code=" code "&grant_type=" GRANT_TYPE)
        post-response (client/post ACCESS_TOKEN_URI
                                    {:body post-body
                                     :content-type "application/x-www-form-urlencoded"
                                     })]
    (str "Access token response is " post-response)))

;POST https://loneworker.auth0.com/oauth/token
;
;Content-type: application/x-www-form-urlencoded
;
;client_id=CLIENT_ID&redirect_uri=REDIRECT_URI&client_secret=CLIENT_SECRET&code=AUTHORIZATION_CODE&grant_type=authorization_code