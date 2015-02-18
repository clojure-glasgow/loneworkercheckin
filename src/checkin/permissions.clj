(ns checkin.permissions)

(defn addresses-to-notify
  [person]
  (let [friends (:friends person)
        has-empty-permissions? #(empty? (second %))]
    (into {} (remove has-empty-permissions? friends))))
