(ns checkin.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [checkin.auth :as auth]
            [checkin.routes.appointment :as appointment]
            [cemerick.friend :as friend]
            [friend-oauth2.util :refer [format-config-uri]]
            [checkin.session-helper :as session-helper]))

(defroutes app-routes
           (GET "/" [] "Hello World with Compojure")

           (POST "/appointment" request (appointment/add request))
           (GET "/appointment" request (appointment/get request))

           (GET "/status" request
                (let [session (:session request)]
                  (-> (ring.util.response/response
                        (str "<p>The current session: " session "</p><p>Your name is " (session-helper/get-user-name request) "</p>"))
                      (assoc :session session))))
           (GET "/authlink" request
                (friend/authorize #{:user} "Authorized page."))
           (GET "/authlink2" request
                (friend/authorize #{:user} "Authorized page 2."))
           (GET "/admin" request
                (friend/authorize #{:admin} "Only admins can see this page."))
           (friend/logout (ANY "/logout" request (ring.util.response/redirect "/")))

           (route/not-found "Not Found"))
(def app
  (handler/site
    (friend/authenticate
      app-routes
      auth/friend-config)))
