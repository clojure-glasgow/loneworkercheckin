(ns checkin.permissions)

(defn has-permission? [permission permissions-set]
  (not (nil? (permission permissions-set))))

(defn has-notify-permission? [[email-address permissions-set]]
  (has-permission? :notify permissions-set))

(defn addresses-to-notify
  [contacts]
    (into {} (filter has-notify-permission? contacts)))
