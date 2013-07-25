# places

Clojure interface to the Google Places API.

## Installation

Install using Leiningen and Clojars: https://clojars.org/places.


## Usage

NOTE: The `places` library is nice to use alongside the `geocoder-clj` library (see: https://github.com/r0man/geocoder-clj) which can help determine latitude and longitude coordinates.

```clojure
(require '[places.search :as places])

(places/nearby-search {:lng -33.8670522 :lat 151.1957362}
                      8046
                      "asdf1234" ;; Acquire from Google's Developer Console
                      :term "ice cream")
```

Returns a map representing Google Place's search results.

## License

Copyright © 2013 Banzai Inc.

Distributed under the Eclipse Public License, the same as Clojure.
