(ns advent-of-code.day-16
  (:require [clojure.string :refer [join split]]))

(defn draft-programs [n]
  (mapv char (range (int \a) (+ (int \a) n))))

(defn spin
  [programs dance-move]
  (let [[x'] (re-seq #"\d+" dance-move)
        x (- (count programs) (Integer/parseInt x'))]
    (into [] (take (count programs) (drop x (cycle programs))))))

(defn exchange
  ([programs dance-move]
   (let [[a b] (map #(Integer/parseInt %) (re-seq #"\d+" dance-move))]
     (exchange programs a b)))
  ([programs a b]
   (assoc (assoc programs b (get programs a)) a (get programs b))))

(defn partner [programs dance-move]
  (let [[_ [a] [b]] (map vec (first (re-seq #"(.)/(.)" dance-move)))]
    (exchange programs (.indexOf programs a) (.indexOf programs b))))

(defn part-1
  "Day 16 Part 1"
  ([input] (part-1 input 16))
  ([input total]
   (let [dance-moves (split input #",")]
     (join
      (reduce
       (fn [programs dance-move]
         (case (first dance-move)
           \s (spin programs dance-move)
           \x (exchange programs dance-move)
           (partner programs dance-move)))
       (draft-programs total)
       dance-moves)))))

(defn part-2
  "Day 16 Part 2"
  [input]
  input)
