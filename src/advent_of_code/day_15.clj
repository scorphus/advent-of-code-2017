(ns advent-of-code.day-15)

(def a-factor 16807)
(def b-factor 48271)
(def divider 2147483647)
(def mask 65535)

(defn part-1
  "Day 15 Part 1"
  [input] (->>
           (re-seq #"\d+" input)
           (map #(Integer/parseInt %))
           (#(reduce
              (fn [[[a b] matches] _]
                (let [a' (mod (* a a-factor) divider)
                      b' (mod (* b b-factor) divider)
                      a'' (bit-and a mask)
                      b'' (bit-and b mask)]
                  [[a' b'] (if (= a'' b'') (inc matches) matches)]))
              [% 0]
              (range 4e7)))
           (second)))

(defn part-2
  "Day 15 Part 2"
  [input]
  input)
