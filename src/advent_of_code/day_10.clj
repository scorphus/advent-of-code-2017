(ns advent-of-code.day-10
  (:require [clojure.string :refer [split]]
            [advent-of-code.util :as util]))

(defn rev-skip
  "Reverses and skips the list"
  [the-list [skip length]]
  (let [size (count the-list)
        the-list (concat
                  (take (- size length) (drop length (cycle the-list)))
                  (reverse (take length (cycle the-list))))]
    (take size (drop skip (cycle the-list)))))

(defn part-1
  "Day 10 Part 1"
  ([input] (part-1 input 256))
  ([input size]
   (let [lengths (map util/parse-int (split input #","))
         the-list (reduce rev-skip (range size) (map-indexed vector lengths))
         skips (* (count lengths) (dec (count lengths)) 1/2)
         droppage (- size (mod (apply + skips lengths) size))]
     (apply * (take 2 (drop droppage the-list))))))

(defn part-2
  "Day 10 Part 2"
  [input]
  input)
