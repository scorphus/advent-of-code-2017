(ns advent-of-code.day-19
  (:require [clojure.string :refer [join]]))

(def corner "+")
(def path-hor "-")
(def path-ver "|")
(def pathways #{path-hor path-ver})

(defn parse-diagram
  [input] (mapv
           #(into [] (re-seq #"." %))
           (re-seq #"[^\n]+" input)))

(defn get-line [diag row col] (get (get diag row) col))

(defn turn [[Δrow _]] (if (zero? Δrow) [[-1 0] [1 0]] [[0 -1] [0 1]]))

(defn steer
  [diag [row col] deltas]
  (first
   (for [[Δrow Δcol] (turn deltas)
         :when (not= " " (get-line diag (+ row Δrow) (+ col Δcol)))]
     [Δrow Δcol])))

(defn walk
  [diag [row col] deltas]
  (let [curr (get-line diag row col)]
    (cond
      (= " " curr) nil
      (contains? pathways curr) [deltas nil]
      (= corner curr) [(steer diag [row col] deltas) nil]
      :else [deltas curr])))

(defn follow-diagram
  [diag]
  (loop [[row col] [0 (.indexOf (get diag 0) path-ver)]
         deltas [1 0]
         letters []
         steps 0]
    (if-let [[[Δrow Δcol] letter] (walk diag [row col] deltas)]
      (recur
       [(+ row Δrow) (+ col Δcol)]
       [Δrow Δcol]
       (if (nil? letter) letters (conj letters letter))
       (inc steps))
      [letters steps])))

(defn part-1
  "Day 19 Part 1"
  [input]
  (join (first (follow-diagram (parse-diagram input)))))

(defn part-2
  "Day 19 Part 2"
  [input]
  (second (follow-diagram (parse-diagram input))))
