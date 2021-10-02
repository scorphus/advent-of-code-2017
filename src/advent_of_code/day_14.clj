(ns advent-of-code.day-14
  (:require [clojure.pprint :refer (cl-format)]
            [clojure.string :refer [join]]
            [advent-of-code.day-10 :as day-10]))

(defn hash->grid
  [hash] (->>
          (map #(cl-format nil "~4,'0',B" (Integer/parseInt (str %) 16)) hash)
          (join)
          (vec)))

(defn key-string->grid
  [key-string rows] (->>
                     (map vector (cycle [key-string]) (range rows))
                     (map (partial join "-"))
                     (map day-10/part-2)
                     (mapv hash->grid)))

(defn part-1
  "Day 14 Part 1"
  [input] (->>
           (key-string->grid input 128)
           (flatten)
           (filterv #(= % \1))
           (count)))

(defn get-row-col [grid row col] (nth (nth grid row) col))

(defn set-row-col [grid row col value] (update grid row assoc col value))

(def directions [[1 0] [0 1] [-1 0] [0 -1]])

(defn set-region-free
  "Frees up all used squares adjacents to a give square"
  [grid square]
  (let [rows (count grid)
        cols (count (first grid))]
    (loop [grid grid
           next-squares (list square)]
      (if (empty? next-squares)
        grid
        (let [[[row col] & next-squares] next-squares]
          (recur (set-row-col grid row col \0)
                 (reduce
                  (fn [next-squares [δr δc]]
                    (let [r (+ row δr)
                          c (+ col δc)]
                      (if (and
                           (>= r 0) (< r rows)
                           (>= c 0) (< c cols)
                           (= (get-row-col grid r c) \1))
                        (conj next-squares [r c])
                        next-squares)))
                  next-squares
                  directions)))))))

(defn part-2
  "Day 14 Part 2"
  [input] (let [grid (key-string->grid input 128)
                rows (count grid)
                cols (count (first grid))]
            (->>
             (for [r (range rows) c (range cols)] [r c])
             (reduce
              (fn [[grid regions] [row col]]
                (if (= (get-row-col grid row col) \1)
                  [(set-region-free grid [row col]) (inc regions)]
                  [grid regions]))
              [grid 0])
             (second))))
