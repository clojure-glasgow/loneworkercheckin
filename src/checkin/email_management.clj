(ns checkin.email-management
  (:require [checkin.middleware :as helper]))

(defn email-valid? [email-address]
  (re-matches #"\b[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}\b" email-address))

(defn validation-error [email]
  {:status 422
   :body   (concat "The submitted email is invalid: " email)})

(defn created [email]
  {:status 201
   :body   (concat "Successfully added email: " email)})

(defn new []
  (slurp "resources/new-email.html"))

(defn added [request]
  (helper/log-request request)
  (let [email (:email (:params request))]
    (if (email-valid? email)
      (created email)
      (validation-error email))))

