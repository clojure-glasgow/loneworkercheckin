(ns checkin.profile)

;The dta modle for our app is as follows 
; profile_key_from_login { :emails [email1, email2...] }
;                          :appointments [ appointment1  { :start value, :end value, :arrived value, :departed value},
;                                          appointment2 {...}
;                                         ]
;              }


(defn create-profile [userkey, name, email]
  (hash-map :userkey userkey :name name :email email :contacts '() :appointments '()))


(defn get-contacts-from-profile [profile] 
  (:contacts profile))

(defn get-appointments-from-profile [profile] 
  (:appointments profile))

(defn add-contact-to-profile-old [profile contact-email permission-set]
  ( merge profile {:contacts (conj (get-contacts-from-profile profile) (hash-map contact-email permission-set))}))

(defn add-contact-to-profile [profile contact-email permission-set]
  (->> (hash-map contact-email permission-set)
       (conj (get-contacts-from-profile profile))
       (hash-map :contacts)
       (merge profile)))

(defn add-appointment-to-profile [profile appointment] (""))