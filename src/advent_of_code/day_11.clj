(ns advent-of-code.day-11
  (:require [clojure.string :refer [split]]))

(def adjacency {"n"   [1  1  0]
                "s"  [-1 -1  0]
                "ne"  [1  0  1]
                "sw" [-1  0 -1]
                "nw"  [0  1 -1]
                "se"  [0 -1  1]})

(defn move
  [[x y z] baby-step] (let [[dx dy dz] (get adjacency baby-step)]
                        [(+ x dx) (+ y dy) (+ z dz)]))

(defn part-1
  "Day 11 Part 1"
  [input] (let [path (split input #",")
                pos (reduce move [0 0 0] path)]
            (/ (apply + (map #(Math/abs %) pos)) 2)))

(defn part-2
  "Day 11 Part 2"
  [input]
  input)
