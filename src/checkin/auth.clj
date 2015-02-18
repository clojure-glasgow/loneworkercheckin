(ns checkin.auth
  (:use [compojure.core])
  (:require [friend-oauth2.workflow :as oauth2]
            [friend-oauth2.util :refer [format-config-uri]]
            [cheshire.core :as json]
            [clj-jwt.core :as jwt]
            [environ.core :refer [env]]))

(defn get-environment-variable [name-of-env-var]
  (let [env-var (env name-of-env-var)]
    (if env-var
      env-var
      (throw (Exception. (str "No environment variable found named " name-of-env-var ". Ensure you have a profiles.clj created if running locally"))))))

(def ^:private client-config
  {:client-id     (get-environment-variable :friend-oauth2-client-id)
   :client-secret (get-environment-variable :friend-oauth2-client-secret)
   :callback {:domain "http://localhost:3000" :path "/auth/callback"}})


(def ^:private uri-config
  {:authentication-uri {:url   "https://loneworker.auth0.com/authorize"
                        :query {:client_id    (:client-id client-config)
                                :redirect_uri (format-config-uri client-config)
                                :scope        "openid name email"
                                :sso          true}}

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
        user-name (get-in jwt [:claims :name])]             ; TODO: verify JWT

    {:identity  user-id
     :user-name user-name
     :roles     #{:user}}))

(def friend-config
  {:workflows [(oauth2/workflow
                 {:client-config        client-config
                  :uri-config           uri-config
                  :config-auth          config-auth
                  :access-token-parsefn parse-id-token
                  :credential-fn        credential-fn
                  })
               ]})

