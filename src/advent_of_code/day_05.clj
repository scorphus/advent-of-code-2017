(ns advent-of-code.day-05
  (:require [advent-of-code.util :as util]
            [clojure.string :refer [split-lines]]))

(defn part-1
  "Day 05 Part 1"
  [input] (loop [offsets (mapv util/parse-int (split-lines input))
                 idx 0
                 jumps 0]
            (if-let [curr (get offsets idx)]
              (recur (assoc offsets idx (inc curr)) (+ idx curr) (inc jumps))
              jumps)))

(defn part-2
  "Day 05 Part 2"
  [input] (loop [offsets (mapv util/parse-int (split-lines input))
                 idx 0
                 jumps 0]
            (if-let [curr (get offsets idx)]
              (let [next (if (< curr 3) (inc curr) (dec curr))]
                (recur (assoc offsets idx next) (+ idx curr) (inc jumps)))
              jumps)))
