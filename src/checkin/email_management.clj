(ns checkin.email-management
  (:require [checkin.request-helper :as request-helper]
    [checkin.userprofiles :as userprofiles]
    [checkin.profile :as profile]
    ))

(defn email-valid? [email-address]
  (re-matches #"\b[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}\b" email-address))

(defn validation-error [email]
  {:status 422
   :body   (concat "The submitted email is invalid: " email)})

(defn created [email]
  {:status 201
   :body   (concat "Successfully added email: " email)})

(defn new-email []
    (slurp "resources/new-email.html"))

(defn persist-and-created [new-profile email]
  (userprofiles/upsert-profiles new-profile)
  (created email))


(defn add [request]
  (let [email (:email (:params request))
        user-key (request-helper/get-user-id request)
        profile (userprofiles/get-profile user-key)
        new-profile (profile/add-contact-to-profile profile email #{})]
    (if (email-valid? email)
      (persist-and-created  new-profile email)
      (validation-error email))
 ))



