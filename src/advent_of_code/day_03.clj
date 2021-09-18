(ns advent-of-code.day-03
  (:require [advent-of-code.util :as util]))

(defn part-1
  "Day 03 Part 1"
  [input] (let [loc (util/parse-int input)
                radius (int (/ (Math/ceil (Math/sqrt loc)) 2))
                closest (Math/pow (- (* radius 2) 1) 2)]
            (->>
             (range 1 11 2)
             (map #(+ closest (* radius %)))
             (map #(Math/abs (- loc %)))
             (apply min)
             (+ radius)
             (int))))

(defn part-2
  "Day 03 Part 2"
  [input]
  input)
