(ns checkin.auth
  (:use [compojure.core])
  (:require [friend-oauth2.workflow :as oauth2]
            [friend-oauth2.util :refer [format-config-uri]]))

(def ^:private client-config
  {:client-id     "eR08QvVSethRbm4lPQLEeg0lBgQXg4Wy"
   :client-secret "9jNUjATxDFl0FrkbeULttxc1UR1P0tLzBKa1EzPkvHXpSRKI_yof_CBcU1xBRTju" ; TODO these shouldn't be checked in to source control
   :callback      {:domain "http://localhost:3000" :path "/auth/callback"}})

(def ^:private uri-config
  {:authentication-uri {:url   "https://loneworker.auth0.com/authorize"
                        :query {:client_id     (:client-id client-config)
                                :response_type "code"
                                :sso           true
                                :redirect_uri  (format-config-uri client-config)
                                :scope         "openid name email"}}

   :access-token-uri   {:url   "https://loneworker.auth0.com/oauth/token"
                        :query {:client_id     (:client-id client-config)
                                :client_secret (:client-secret client-config)
                                :grant_type    "authorization_code"
                                :redirect_uri  (format-config-uri client-config)}}})

(defn- credential-fn
  "Upon successful authentication with the third party, Friend calls
  this function with the user's token. This function is responsible for
  translating that into a Friend identity map with at least the :identity
  and :roles keys. How you decide what roles to grant users is up to you;
  you could e.g. look them up in a database.

  You can also return nil here if you decide that the token provided
  is invalid. This could be used to implement e.g. banning users.

  This example code just automatically assigns anyone who has
  authenticated with the third party the nominal role of ::user."
  [token]
  {:identity token
   :roles    #{::user}})

(def friend-config
  {:allow-anon? true
   :workflows   [(oauth2/workflow
                   {:client-config client-config
                    :uri-config    uri-config
                    :credential-fn credential-fn})
                 ]})

