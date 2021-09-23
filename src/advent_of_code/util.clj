(ns advent-of-code.util)

(defn parse-int [x] (Integer/parseInt x))

(defn max-indexed
  "Returns the [i x] for which x, a number, is greatest. If there are multiple
   such xs, the first one is returned."
  [v] (reduce
       #(case (compare (second %1) (second %2)) -1 %2 %1)
       (map-indexed vector v)))
