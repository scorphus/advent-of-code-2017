(ns advent-of-code.day-07
  (:require [clojure.string :refer [split split-lines]]))

(defn extract-on-hold
  [programs line] (if-let [on-hold (second (split line #" -> " 2))]
                    (into programs (split on-hold #", "))
                    programs))

(defn find-bottom
  [on-hold line] (let [program (first (split line #"\s+" 2))]
                   (if (contains? on-hold program) on-hold (reduced program))))

(defn part-1
  "Day 07 Part 1"
  [input] (let [lines (split-lines input)
                on-hold (reduce extract-on-hold #{} lines)]
            (reduce find-bottom on-hold lines)))

(defn part-2
  "Day 07 Part 2"
  [input]
  input)
