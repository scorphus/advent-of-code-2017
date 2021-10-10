(ns advent-of-code.day-20)

(defn parse-particles
  [input] (->>
           (re-seq #"[^\n]+" input)
           (map #(re-seq #"[\d-]+" %))
           (map (partial map #(Integer/parseInt %)))
           (map-indexed #(vector %1 (partition 3 %2)))))

(defn pos [t p v a] (+ p (* v t) (* a t (inc t) 1/2)))

(defn pos-xyz
  [t [p v a]]
  (loop [[pᵢ & p] p
         [vᵢ & v] v
         [aᵢ & a] a
         res []]
    (if (nil? pᵢ) res (recur p v a (conj res (pos t pᵢ vᵢ aᵢ))))))

(defn distance-after
  [t [_ [p v a]]] (reduce + (map #(Math/abs %) (pos-xyz t [p v a]))))

(defn part-1
  "Day 20 Part 1"
  [input]
  (->>
   (parse-particles input)
   (apply min-key #(distance-after 1e6 %))
   (first)))

(defn collide
  [particles]
  (into []
        (comp
         (remove (fn [[_k v]] (> (count v) 1)))
         (map (comp first val)))
        (group-by #(pos-xyz (first %) (second %)) particles)))

(defn tick [[t pva]] [(inc t) pva])

(defn part-2
  "Day 20 Part 2"
  [input]
  (->>
   (parse-particles input)
   (map #(vector 0 (second %)))
   (iterate #(collide (map tick %)))
   (drop 50)
   (first)
   (count)))
