(ns checkin.email-management)

  (defn email-valid? [email-address]
        (re-matches #"\b[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}\b" email-address))

(defn add-email [email-address]
  (cond
    (email-valid? email-address) [201]
    :else [500]))

