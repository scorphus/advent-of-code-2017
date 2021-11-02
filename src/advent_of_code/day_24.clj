(ns advent-of-code.day-24)

(defn parse-port [ports port]
  (let [[p1 p2] (map #(Integer/parseInt %) (re-seq #"\d+" port))]
    (update-in
     (update-in
      ports [p1] (fnil conj #{}) p2)
     [p2] (fnil conj #{}) p1)))

(defn parse-ports [input] (reduce parse-port {} (re-seq #"[^\n]+" input)))

(defn strongest-bridge
  ([ports strat] (strongest-bridge ports strat 0 #{}))
  ([ports strat p1 seen]
   (loop [next-ports (get ports p1)
          strength 0
          len 0]
     (if (empty? next-ports)
       [strength len]
       (let [[p2 & next-ports] next-ports
             pair (set [p1 p2])]
         (if (contains? seen pair)
           (recur next-ports strength len)
           (let [[strength´ len´] (strongest-bridge ports strat p2 (conj seen pair))
                 strength´ (+ p1 p2 strength´)
                 len´ (inc len´)
                 [strength´ len´] (strat [strength len] [strength´ len´])]
             (recur next-ports strength´ len´))))))))

(defn by-strength [a b] (case (compare a b) -1 b a))

(defn part-1
  "Day 24 Part 1"
  [input]
  (first (strongest-bridge (parse-ports input) by-strength)))

(defn by-len [[a b] [c d]] (case (compare [b a] [d c]) -1 [c d] [a b]))

(defn part-2
  "Day 24 Part 2"
  [input]
  (first (strongest-bridge (parse-ports input) by-len)))
