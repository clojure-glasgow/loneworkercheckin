(defproject checkin "0.1.0-SNAPSHOT"
            :description "FIXME: write description"
            :url "http://example.com/FIXME"
            :license {:name "Eclipse Public License"
                      :url  "http://www.eclipse.org/legal/epl-v10.html"}
            :dependencies [[org.clojure/clojure "1.6.0"]
                           [org.slf4j/slf4j-api "1.7.10"]
                           [ch.qos.logback/logback-classic "1.1.2"]
                           [ring "1.3.2"]
                           [compojure "1.3.2"]
                           [friend-oauth2 "0.1.3"]
                           [cheshire "5.4.0"]
                           [clj-jwt "0.0.11"]
                           [environ "1.0.0"]
                           [enlive "1.1.5"]
                           [org.clojure/tools.logging "0.3.1"]]

            :profiles {:dev {:dependencies [[midje "1.6.3"]]
                             :env          {:app-domain "http://localhost:3000"}}
                       :proxied
                            {:jvm-opts ["-Dhttp.proxyHost=127.0.0.1" "-Dhttp.proxyPort=8888"
                                        "-Dhttps.proxyHost=127.0.0.1" "-Dhttps.proxyPort=8888"]}}
            :plugins [[lein-ring "0.9.1"]
                      [lein-midje "3.1.3"]
                      [lein-environ "1.0.0"]
                      [lein-ancient "0.6.2"]
                      [lein-kibit "0.0.8"]
                      [jonase/eastwood "0.2.1"]]
            :ring {:handler      checkin.handler/app
                   :uberwar-name "loneworkercheckin.war"}
            :aliases {"run-proxied" ["with-profile" "proxied" "ring" "server"]}
            :uberjar-name "checkin.jar"
            :main checkin.handler
            :aot :all)
