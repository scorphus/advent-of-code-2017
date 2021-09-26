(ns advent-of-code.day-08
  (:require [clojure.string :refer [split split-lines]]
            [advent-of-code.util :as util]))

(defn evaluate
  [op a b] (case op
             ">" (> a b)
             "<" (< a b)
             "<=" (<= a b)
             ">=" (>= a b)
             "==" (= a b)
             "!=" (not= a b)
             "inc" (+ a b)
             "dec" (- a b)))

(defn evaluate-instructions
  [interceptor registers [a op1 b _ c op2 d]]
  (let [[a-val c-val] (map #(get registers % 0) [a c])
        [b-val d-val] (map util/parse-int [b d])]
    (if (evaluate op2 c-val d-val)
      (interceptor (assoc registers a (evaluate op1 a-val b-val)))
      registers)))

(defn part-1
  "Day 08 Part 1"
  [input] (->>
           (split-lines input)
           (map #(split % #"\s+"))
           (reduce (partial evaluate-instructions identity) {})
           (vals)
           (apply max)))

(defn register-max-value
  [registers] (assoc registers "max-value" (apply max (vals registers))))

(defn part-2
  "Day 08 Part 2"
  [input] (->>
           (split-lines input)
           (map #(split % #"\s+"))
           (reduce (partial evaluate-instructions register-max-value) {})
           (#(get % "max-value"))))
