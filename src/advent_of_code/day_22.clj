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

(defn move-in-bursts
  ([[grid [r c] deltas must-evolve infections]]
   (let [node (get grid [r c])
         [Δr Δc :as deltas] (turn deltas node)
         node (evolve must-evolve node)
         grid (assoc grid [r c] node)
         infections (if (= node :infected) (inc infections) infections)]
     [grid [(+ r Δr) (+ c Δc)] deltas must-evolve infections])))

(defn part-1
  "Day 22 Part 1"
  [input]
  (let [grid (parse-grid input)
        mid-point (quot (inc (apply max (flatten (keys grid)))) 2)]
    (->
     (iterate move-in-bursts [grid [mid-point mid-point] [-1 0] false 0])
     (nth 10000)
     (last))))

(defn part-2
  "Day 22 Part 2"
  [input]
  (let [grid (parse-grid input)
        mid-point (quot (inc (apply max (flatten (keys grid)))) 2)]
    (->
     (iterate move-in-bursts [grid [mid-point mid-point] [-1 0] true 0])
     (nth 10000000)
     (last))))
