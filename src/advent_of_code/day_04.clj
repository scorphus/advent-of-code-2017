(ns advent-of-code.day-04
  (:require [clojure.string :refer [split split-lines]]))

(defn part-1
  "Day 04 Part 1"
  [input] (->>
           (split-lines input)
           (map #(split % #"\s+"))
           (filter #(apply distinct? %))
           (count)))

(defn part-2
  "Day 04 Part 2"
  [input]
  input)
