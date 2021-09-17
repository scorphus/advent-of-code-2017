(ns advent-of-code.day-01)

(defn part-1
  "Day 01 Part 1"
  [input] (->>
           (map
            #(if (= %1 %2) (- (int %1) 48) 0)
            input
            (drop 1 (cycle input)))
           (reduce +)))

(defn part-2
  "Day 01 Part 2"
  [input] (->>
           (map
            #(if (= %1 %2) (- (int %1) 48) 0)
            input
            (drop (/ (count input) 2) (cycle input)))
           (reduce +)))
