(ns advent-of-code.day-03
  (:require [advent-of-code.util :as util]))

(defn part-1
  "Day 03 Part 1"
  [input] (let [loc (util/parse-int input)
                radius (int (/ (Math/ceil (Math/sqrt loc)) 2))
                closest (Math/pow (- (* radius 2) 1) 2)]
            (->>
             (range 1 11 2)
             (map #(+ closest (* radius %)))
             (map #(Math/abs (- loc %)))
             (apply min)
             (+ radius)
             (int))))

(defrecord Square [x y])
(defrecord Delta [dx dy])
(defrecord SpiralMemory [memory square delta])

(defn make-spiral-memory
  ([]
   (make-spiral-memory {(->Square 0 0) 1} 0 0 0 -1))
  ([memory x y dx dy]
   (->SpiralMemory memory (->Square x y) (->Delta dx dy))))

(defn adj-nn [x y] (->Square      x  (inc y)))
(defn adj-ne [x y] (->Square (inc x) (inc y)))
(defn adj-ee [x y] (->Square (inc x)      y))
(defn adj-se [x y] (->Square (inc x) (dec y)))
(defn adj-ss [x y] (->Square      x  (dec y)))
(defn adj-sw [x y] (->Square (dec x) (dec y)))
(defn adj-ww [x y] (->Square (dec x)      y))
(defn adj-nw [x y] (->Square (dec x) (inc y)))

(defn adjacency
  [{{x :x y :y} :square
    {dx :dx dy :dy} :delta}]
  (case [dx dy]
    [0  1] [(adj-nw x y) (adj-ww x y) (adj-sw x y) (adj-ss x y)]
    [-1 0] [(adj-sw x y) (adj-ss x y) (adj-se x y) (adj-ee x y)]
    [0 -1] [(adj-se x y) (adj-ee x y) (adj-ne x y) (adj-nn x y)]
    [1  0] [(adj-ne x y) (adj-nn x y) (adj-nw x y) (adj-ww x y)]))

(defn write
  [{memory :memory
    square :square
    :as spiral-memory}]
  (->>
   (adjacency spiral-memory)
   (map #(get memory % 0))
   (reduce +)
   (assoc memory square)
   (assoc spiral-memory :memory)))

(defn walk
  [{memory :memory
    {x :x y :y} :square
    {dx :dx dy :dy} :delta}]
  (let [should-turn (or (= x y)
                        (and (pos? y) (zero? (+ x y)))
                        (and (<= y 0) (zero? (+ x y -1))))
        [dx dy] (if should-turn [(- dy) dx] [dx dy])]
    (make-spiral-memory memory (+ x dx) (+ y dy) dx dy)))

(defn spiralize
  ([]
   (spiralize (make-spiral-memory)))
  ([spiral-memory]
   (lazy-seq (cons spiral-memory (spiralize (write (walk spiral-memory)))))))

(defn part-2
  "Day 03 Part 2"
  [input] (let [threshold (util/parse-int input)]
            (first (for [{memory :memory square :square} (spiralize)
                         :when (> (get memory square) threshold)]
                     (get memory square)))))
