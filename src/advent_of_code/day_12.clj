(ns advent-of-code.day-12)

(defn parse-village
  "Parses input into a map representation of the village"
  [input] (->>
           (re-seq #"[^\n]+" input)
           (map (partial re-seq #"\d+"))
           (map (fn [[prog & comm]] [prog comm]))
           (into {})))

(defn visit-all
  "Visits all programs reachable from a starting program"
  [village start]
  (loop [seen #{start}
         next-progs (into '() [start])]
    (if (empty? next-progs)
      seen
      (let [[prog & next-progs] next-progs]
        (recur (conj seen prog)
               (reduce
                #(if (contains? seen %2) %1 (conj %1 %2))
                next-progs
                (get village prog)))))))

(defn part-1
  "Day 12 Part 1"
  [input] (count (visit-all (parse-village input) "0")))

(defn part-2
  "Day 12 Part 2"
  [input]
  input)
