(ns checkin.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [checkin.auth :as auth]
            [checkin.routes.appointment :as appointment]
            [cemerick.friend :as friend]
            [friend-oauth2.util :refer [format-config-uri]]))

(defroutes restricted-routes
           (GET "/private-page" [] "You should only see this if logged in"))

(defroutes app-routes
           (GET "/" [] "Hello World with Compojure")

           (POST "/appointment" request (appointment/add request))
           (GET "/appointment" request (appointment/get request))

           (friend/wrap-authorize restricted-routes #{::user})
           (friend/logout (ANY "/logout" request (ring.util.response/redirect "/")))

           (route/not-found "Not Found"))

(def app
  (-> app-routes
      (wrap-defaults site-defaults)
      (friend/authenticate auth/friend-config)))
