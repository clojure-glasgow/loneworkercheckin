(ns checkin.userprofiles)

(def profiles (ref {}))

(defn upsert-profiles [profile]
  (dosync
    (commute profiles merge {(:userkey profile) profile})
    )
  )

(defn get-profile [profile-key] 
  (get @profiles profile-key))