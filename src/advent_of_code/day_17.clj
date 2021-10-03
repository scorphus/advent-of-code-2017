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
  (let [steps (Integer/parseInt (first (re-seq #"\d+" input)))]
    (first
     (reduce
      (fn [[ans pos] val]
        (let [pos (mod (+ pos steps) val)]
          [(if (zero? pos) val ans) (inc pos)]))
      [1 1]
      (range 1 5e7)))))
