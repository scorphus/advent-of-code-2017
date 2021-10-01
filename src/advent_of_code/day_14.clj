(ns advent-of-code.day-14
  (:require [clojure.pprint :refer (cl-format)]
            [clojure.string :refer [join]]
            [advent-of-code.day-10 :as day-10]))

(defn hash->grid
  [hash] (->>
          (map #(cl-format nil "~4,'0',B" (Integer/parseInt (str %) 16)) hash)
          (join)
          (vec)))

(defn part-1
  "Day 14 Part 1"
  [input] (->>
           (map vector (cycle [input]) (range 128))
           (map (partial join "-"))
           (map day-10/part-2)
           (mapv hash->grid)
           (flatten)
           (filterv #(= % \1))
           (count)))

(defn part-2
  "Day 14 Part 2"
  [input]
  input)
