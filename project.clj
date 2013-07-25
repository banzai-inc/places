(defproject places "0.1.0"
  :description "Clojure interface to Google's Places API"
  :url "https://github.com/banzai-inc/places"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [clj-http "0.7.6"]
                 [ring/ring-codec "1.0.0"]
                 [org.clojure/data.json "0.2.2"]]
  :profiles {:dev {:dependencies [[geocoder-clj "0.2.1"]]}})
