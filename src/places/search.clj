(ns places.search
  (:require [clj-http.client :as client]
            [ring.util.codec :as codec]
            [cheshire.core :refer [parse-string]]
            [clojure.string :refer [join]]))

(def ^{:private true} places-url "https://maps.googleapis.com/maps/api/place")

(def ^{:private true} output "json")

(def ^{:private true} nearby-url (str places-url "/nearbysearch/" output))

(defn- parse-response
  [resp]
  (parse-string resp true))

(defn nearby-search
  "Search places within a specified area.
  Location argument should reference a map containing two keys: lat, and lng.
  Ex: {:lng -33.8670522 :lat 151.1957362}
  Options include
  - radius: required unless rankby is \"distance\" (radius is measured in meters)
  - types: pass a vector (e.g. [\"finance\", \"atm\"])
  See https://developers.google.com/places/documentation/search"
  [api-key location & {:keys [radius rankby keyword name types]
                       :or {keyword ""
                            rankby "prominence"
                            radius 10
                            name ""
                            types []}}]
  (->> (client/get (str nearby-url "?"
                        "key=" api-key "&"
                        "location=" (:lat location) "," (:lng location) "&"
                        (if (= "distance" rankby) "" (str "radius=" radius "&"))
                        "rankby=" rankby "&"
                        "name=" name "&"
                        (if (empty? types) "" (str "types=" (join "|" types) "&"))
                        "keyword=" (codec/url-encode keyword)))
       (:body)
       (parse-response)  
       (:results)))

(defn details
  "Return place details given an id"
  [api-key place-id]
  (->> (client/get (str places-url "/details/" output "?"
                        "key=" api-key "&"
                        "placeid=" place-id))
       (:body)
       (parse-response)
       (:result)))
