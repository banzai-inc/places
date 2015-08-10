# places

Clojure interface to the Google Places API.

## Installation

Install using Leiningen and Clojars: https://clojars.org/places.

## Usage

NOTE: The `places` library is nice to use alongside the `geocoder-clj` library (see: https://github.com/r0man/geocoder-clj) which can help determine latitude and longitude coordinates.

#### Require library

```clojure
(require '[places.search :refer [nearby-search details]])
```

#### Nearby Search

```clojure
(nearby-search "asdf1234" ;; Acquire from Google's Developer Console
               {:lng -33.8670522 :lat 151.1957362}
               :radius 8046
               :rankby "prominence"
               :types ["food"]
               :keyword "ice cream")
```

#### Place Details

```clojure
(details "asdf1234" "my-place-id")
```

Returns a map representing Google Place's search results.

## License

Copyright © 2015 Banzai Inc.

Distributed under the Eclipse Public License, the same as Clojure.
