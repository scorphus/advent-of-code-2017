(ns advent-of-code.day-10
  (:require [clojure.string :refer [join split]]
            [advent-of-code.util :as util]))

(defn rev-skip
  "Reverses and skips the list"
  [the-list [skip length]]
  (let [size (count the-list)
        the-list (concat
                  (take (- size length) (drop length (cycle the-list)))
                  (reverse (take length (cycle the-list))))]
    (take size (drop skip (cycle the-list)))))

(defn knot-hash
  "Runs a given number of rounds of the Knot Hash"
  [lengths size rounds]
  (let [acc-len-size (* rounds (count lengths))
        the-list (reduce rev-skip
                         (range size)
                         (map vector (range acc-len-size) (cycle lengths)))
        forwards (* rounds (apply + lengths))
        skips (* acc-len-size (dec acc-len-size) 1/2)
        droppage (- size (mod (+ forwards skips) size))]
    (->>
     (cycle the-list)
     (drop droppage)
     (take size))))

(defn part-1
  "Day 10 Part 1"
  ([input] (part-1 input 256 1))
  ([input size] (part-1 input size 1))
  ([input size rounds]
   (let [lengths (mapv util/parse-int (split input #","))]
     (apply * (take 2 (knot-hash lengths size rounds))))))

(defn part-2
  "Day 10 Part 2"
  ([input] (part-2 input 256 64 16))
  ([input size rounds blocks]
   (let [lengths (into (mapv byte input) [17 31 73 47 23])]
     (->>
      (knot-hash lengths size rounds)
      (partition blocks)
      (map #(apply bit-xor %))
      (map #(format "%02x" %))
      (join "")))))
