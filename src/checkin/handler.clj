(ns checkin.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [checkin.auth :as auth]
            [checkin.appointment :as appointment]
            [checkin.email-management :as email]
            [cemerick.friend :as friend]
            [environ.core :refer [env]]
            [checkin.request-helper :as request-helper]
            [checkin.middleware :refer [log-request]]
            [checkin.profile-handler :as profile-handler]
            [ring.adapter.jetty :refer [run-jetty]])
  (:gen-class))

(defroutes app-routes
           (GET "/" [] "Hello World with Compojure - try /register?name=me&email=me@email.com")

           (POST "/appointment" request (appointment/add request))
           (GET "/appointment" request (appointment/render request))

           (GET "/email/new" request (email/new))
           (POST "/email" request (email/added request))

           (GET "/status" request
                (let [session (:session request)]
                  (-> (ring.util.response/response
                        (str "<p>The current session: " session "</p><p>Your name is " (request-helper/get-user-name request) "</p><p>" @checkin.userprofiles/profiles))
                      (assoc :session session))))
           (GET "/authlink" request
                (friend/authorize #{:user} "Authorized page."))
           (GET "/authlink2" request
                (friend/authorize #{:user} "Authorized page 2."))
           (GET "/admin" request
                (friend/authorize #{:admin} "Only admins can see this page."))
           (GET "/register" [name email :as req]
                (friend/authorize #{:user} (profile-handler/create req name email)))
           (friend/logout (ANY "/logout" request (ring.util.response/redirect "/")))

           (route/not-found "Not Found"))

(def app
  (handler/site
    (-> app-routes
        log-request
        (auth/authentication-middleware))))

(defn -main [& args]
  (run-jetty app {:port (or (-> (System/getenv "PORT")
                                (Integer/parseInt))
                            3000)}))
