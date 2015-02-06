(ns checkin.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [checkin.routes.facebook :as facebook]))

(defroutes app-routes
           (GET "/" [] "Hello World with Compojure")
           ; TODO: move login routes to specific collection
           (GET "/login" [] (facebook/login))
           (GET "/auth_facebook" {params :query-params} (facebook/process-auth-response params))
           (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
