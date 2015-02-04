(ns checkin.core)

(defn home [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "Hello World - !!!"})
