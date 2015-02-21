(ns checkin.middleware
  (:require [clojure.tools.logging :as log]))

(defn log-request
  [handler]
  (fn [request]
    (log/info (str "request-uri " (:uri request)))
    (log/info (str "request-params " (:params request)))
    (log/info (str "user-agent " (-> request :headers (get "user-agent"))))
    (handler request)))
