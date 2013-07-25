(ns places.search
  (:require [clj-http.client :as client]
            [ring.util.codec :as codec]
            [clojure.data.json :as json]))

(def ^{:private true} places-url "https://maps.googleapis.com/maps/api/place")

(def ^{:private true} output :json)

(def ^{:private true} nearby-url (str places-url "/nearbysearch/" (name output)))

(defn- parse-response
  [resp]
  (:results (json/read-str resp :key-fn keyword)))

(defn nearby-search
  "Search places within a specified area.
  Location argument should reference a map containing two keys: lat, and lng.
  Ex: {:lng -33.8670522 :lat 151.1957362}
  Radius is measured in meters.
  See https://developers.google.com/places/documentation/search"
  [location radius api-key & {:keys [sensor term]
                              :or {sensor false}}]
  (parse-response
    (:body
      (client/get (str nearby-url "?"
                       "key=" api-key "&"
                       "location=" (:lat location) ","
                       (:lng location) "&"
                       "radius=" radius "&"
                       "sensor=" sensor "&"
                       "keyword=" (codec/url-encode term))))))
