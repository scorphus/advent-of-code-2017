(ns advent-of-code.day-02
  (:require [clojure.string :refer [split split-lines]]))

(defn parse-int [x] (Integer/parseInt x))

(defn part-1
  "Day 02 Part 1"
  [input] (->>
           (split-lines input)
           (map #(split % #"\s+"))
           (map #(map parse-int %))
           (map #(- (apply max %) (apply min %)))
           (reduce +)))

(defn part-2
  "Day 02 Part 2"
  [input]
  input)
