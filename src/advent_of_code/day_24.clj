(ns advent-of-code.day-24)

(defn parse-port [ports port]
  (let [[p1 p2] (map #(Integer/parseInt %) (re-seq #"\d+" port))]
    (update-in
     (update-in
      ports [p1] (fnil conj #{}) p2)
     [p2] (fnil conj #{}) p1)))

(defn parse-ports [input] (reduce parse-port {} (re-seq #"[^\n]+" input)))

(defn strongest-bridge
  ([ports] (strongest-bridge ports 0 #{}))
  ([ports p1 seen]
   (loop [next-ports (get ports p1)
          strength 0]
     (if (empty? next-ports)
       strength
       (let [[p2 & next-ports] next-ports
             pair (set [p1 p2])]
         (if (contains? seen pair)
           (recur next-ports strength)
           (let [strength´ (+ p1 p2 (strongest-bridge ports p2 (conj seen pair)))]
             (recur next-ports (max strength´ strength)))))))))

(defn part-1
  "Day 24 Part 1"
  [input]
  (strongest-bridge (parse-ports input)))

(defn part-2
  "Day 24 Part 2"
  [input]
  input)
