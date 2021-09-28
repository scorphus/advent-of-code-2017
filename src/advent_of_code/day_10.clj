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

(defn part-1
  "Day 10 Part 1"
  ([input] (part-1 input 256))
  ([input size]
   (let [lengths (mapv util/parse-int (split input #","))
         the-list (reduce rev-skip (range size) (map-indexed vector lengths))
         forwards (apply + lengths)
         skips (* (count lengths) (dec (count lengths)) 1/2)
         droppage (- size (mod (+ forwards skips) size))]
     (apply * (take 2 (drop droppage the-list))))))

(defn part-2
  "Day 10 Part 2"
  ([input] (part-2 input 256 64 16))
  ([input size rounds blocks]
   (let [lengths (into (mapv byte input) [17 31 73 47 23])
         acc-len-size (* rounds (count lengths))
         the-list (reduce rev-skip
                          (range size)
                          (map vector (range acc-len-size) (cycle lengths)))
         forwards (* rounds (apply + lengths))
         skips (* acc-len-size (dec acc-len-size) 1/2)
         droppage (- size (mod (+ forwards skips) size))]
     (->>
      (cycle the-list)
      (drop droppage)
      (take size)
      (partition blocks)
      (map #(apply bit-xor %))
      (map #(format "%02x" %))
      (join "")))))
