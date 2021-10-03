(ns advent-of-code.day-17)

(defn part-1
  "Day 17 Part 1"
  [input]
  (let [steps (Integer/parseInt (first (re-seq #"\d+" input)))]
    (second
     (reduce
      (fn [buffer value]
        (conj (take value (drop (inc steps) (cycle buffer))) value))
      (list)
      (range 2018)))))

(defn part-2
  "Day 17 Part 2"
  [input]
  input)
