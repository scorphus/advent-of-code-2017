(ns advent-of-code.day-11
  (:require [clojure.string :refer [split]]))

(def adjacency {"n"   [1  1  0]
                "s"  [-1 -1  0]
                "ne"  [1  0  1]
                "sw" [-1  0 -1]
                "nw"  [0  1 -1]
                "se"  [0 -1  1]})

(defn measure
  [pos] (/ (apply + (map #(Math/abs %) pos)) 2))

(defn move
  [[_ max-dist [x y z]] baby-step]
  (let [[dx dy dz] (get adjacency baby-step)
        pos [(+ x dx) (+ y dy) (+ z dz)]
        dist (measure pos)]
    [dist (max max-dist dist) pos]))

(defn part-1
  "Day 11 Part 1"
  [input] (let [path (split input #",")]
            (first (reduce move [0 0 [0 0 0]] path))))

(defn part-2
  "Day 11 Part 2"
  [input] (let [path (split input #",")]
            (second (reduce move [0 0 [0 0 0]] path))))
