(ns advent-of-code.day-22)

(defn parse-grid
  [input]
  (first
   (reduce
    (fn [[grid r] row]
      [(first
        (reduce
         (fn [[grid r c] node]
           [(if (= node "#") (assoc grid [r c] :infected) grid) r (inc c)])
         [grid r 0]
         (re-seq #"." row)))
       (inc r)])
    [{} 0]
    (re-seq #"[^\n]+" input))))

(defn turn
  [[Δr Δc] node]
  (case node
    :weakened [Δr Δc]
    :infected [Δc (- Δr)]
    :flagged [(- Δr) (- Δc)]
    [(- Δc) Δr]))

(defn evolve
  [must-evolve node]
  (case must-evolve
    true (case node
           :weakened :infected
           :infected :flagged
           :flagged nil
           :weakened)
    (case node :infected nil :infected)))

(defn burst
  ([[grid [r c] deltas must-evolve infections]]
   (let [node (get grid [r c])
         [Δr Δc :as deltas] (turn deltas node)
         node (evolve must-evolve node)
         grid (assoc grid [r c] node)
         infections (if (= node :infected) (inc infections) infections)]
     [grid [(+ r Δr) (+ c Δc)] deltas must-evolve infections])))

(defn move-in-bursts
  [input must-evolve bursts]
  (let [grid (parse-grid input)
        mid-point (quot (inc (apply max (flatten (keys grid)))) 2)]
    (->
     (iterate burst [grid [mid-point mid-point] [-1 0] must-evolve 0])
     (nth bursts)
     (last))))

(defn part-1
  "Day 22 Part 1"
  [input]
  (move-in-bursts input false 10000))

(defn part-2
  "Day 22 Part 2"
  [input]
  (move-in-bursts input true 10000000))
