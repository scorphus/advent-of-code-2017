(ns advent-of-code.day-02
  (:require [advent-of-code.util :as util]
            [clojure.string :refer [split split-lines]]))

(defn part-1
  "Day 02 Part 1"
  [input] (->>
           (split-lines input)
           (map #(split % #"\s+"))
           (map #(map util/parse-int %))
           (map #(- (apply max %) (apply min %)))
           (reduce +)))

(defn whole-division
  [nums] (->>
          (for [a nums
                b nums
                :when (and (not= a b) (zero? (mod a b)))]
            (quot a b))
          (first)))

(defn part-2
  "Day 02 Part 2"
  [input] (->>
           (split-lines input)
           (map #(split % #"\s+"))
           (map #(map util/parse-int %))
           (map whole-division)
           (reduce +)))
