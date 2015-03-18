(ns checkin.auth
  (:use [compojure.core])
  (:require [friend-oauth2.workflow :as oauth2]
            [clojure.tools.logging :as log]
            [friend-oauth2.util :refer [format-config-uri]]
            [cemerick.friend :as friend]
            [cheshire.core :as json]
            [clj-jwt.core :as jwt]
            [environ.core :refer [env]]))

(defn- get-environment-variable [name-of-env-var]
  (let [env-var (env name-of-env-var)]
    (if env-var
      env-var
      (log/warn (str "No environment variable found named " name-of-env-var ". Ensure you have a profiles.clj created if running locally")))))

(defn client-config-fn []
  {:client-id     (get-environment-variable :friend-oauth2-client-id)
   :client-secret (get-environment-variable :friend-oauth2-client-secret)
   :callback      {:domain (get-environment-variable :app-domain) :path "/auth/callback"}})

(defn- uri-config-fn [{:keys [client-id client-secret config-uri]}]
    {:authentication-uri {:url   "https://loneworker.auth0.com/authorize"
                          :query {:client_id    client-id
                                  :redirect_uri config-uri
                                  :scope        "openid name email"
                                  :sso          true}}

     :access-token-uri   {:url   "https://loneworker.auth0.com/oauth/token"
                          :query {:client_id     client-id
                                  :client_secret client-secret
                                  :redirect_uri  config-uri}}})

(def ^:private config-auth {:roles #{::user}})

(defn- parse-id-token [response]
  (let [response-body-json (:body response)
        response-body-map (json/parse-string response-body-json true)]
    (:id_token response-body-map)))

(defn- credential-fn-for-secret [friend-oauth2-client-secret]
  (fn [token]
    (let [jwt-string (:access-token token)
          jwt (jwt/str->jwt jwt-string)]
      (jwt/verify jwt friend-oauth2-client-secret)
      {:identity  (get-in jwt [:claims :sub])
       :user-name (get-in jwt [:claims :name])
       :roles     #{:user}})))

(defn friend-config [client-config-fn]
  (let [client-config (client-config-fn)
        config-uri    (format-config-uri client-config)
        client-config-with-uri (assoc client-config :config-uri config-uri)]
    {:workflows [(oauth2/workflow
                   {:client-config        client-config
                    :uri-config           (uri-config-fn client-config-with-uri)
                    :config-auth          config-auth
                    :access-token-parsefn parse-id-token
                    :credential-fn        (-> client-config :client-secret credential-fn-for-secret)})]}))

(defn authentication-middleware [handler]
    (friend/authenticate handler (friend-config client-config-fn)))

