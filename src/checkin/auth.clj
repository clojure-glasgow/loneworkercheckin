(ns checkin.auth
  (:use [compojure.core])
  (:require [friend-oauth2.workflow :as oauth2]
            [friend-oauth2.util :refer [format-config-uri]]
            [cheshire.core :as json]
            [clj-jwt.core :as jwt]))

(def ^:private client-config
  {:client-id     "eR08QvVSethRbm4lPQLEeg0lBgQXg4Wy"
   :client-secret "9jNUjATxDFl0FrkbeULttxc1UR1P0tLzBKa1EzPkvHXpSRKI_yof_CBcU1xBRTju" ; TODO these shouldn't be checked in to source control
   :callback      {:domain "http://localhost:3000" :path "/auth/callback"}})

(def ^:private uri-config
  {:authentication-uri {:url   "https://loneworker.auth0.com/authorize"
                        :query {:client_id    (:client-id client-config)
                                :redirect_uri (format-config-uri client-config)
                                :scope        "openid name email"}}

   :access-token-uri   {:url   "https://loneworker.auth0.com/oauth/token"
                        :query {:client_id     (:client-id client-config)
                                :client_secret (:client-secret client-config)
                                :redirect_uri  (format-config-uri client-config)}}})

(def ^:private config-auth {:roles #{::user}})

(defn- parse-id-token [response]
  (let [response-body-json (:body response)
        response-body-map (json/parse-string response-body-json true)]
    (:id_token response-body-map)))

(defn- credential-fn [token]
  (let [jwt-string (:access-token token)
        jwt (jwt/str->jwt jwt-string)
        user-id (get-in jwt [:claims :sub])                 ; TODO: verify JWT
        user-name (get-in jwt [:claims :name])]              ; TODO: verify JWT

    {:identity user-id
     :user-name user-name
     :roles    #{:user}}))

(def friend-config
  {:workflows [(oauth2/workflow
                 {:client-config        client-config
                  :uri-config           uri-config
                  :config-auth          config-auth
                  :access-token-parsefn parse-id-token
                  :credential-fn        credential-fn
                  })
               ]})

;jwt-string (:id_token parsed-response-body)
;_ (println "raw JWT is " jwt-string)
;parsed-jwt (str->jwt jwt-string)
;parsed-jwt
;
;(defn process-auth-response [params]
;  (let [params-as-keys (keywordize-keys params)
;        code (:code params-as-keys)
;        jwt (exchange-auth-code-for-token code)
;        user-id (get-in jwt [:claims :sub]) ; TODO: verify JWT
;        user-name (get-in jwt [:claims :name])] ; TODO: verify JWT
;    (str "Hello " user-name ", your user Id is " user-id)))

