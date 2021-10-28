(ns advent-of-code.day-22)

(defn parse-grid
  [input]
  (first
   (reduce
    (fn [[grid r] row]
      [(first
        (reduce
         (fn [[grid r c] node]
           [(if (= node "#") (conj grid [r c]) grid) r (inc c)])
         [grid r 0]
         (re-seq #"." row)))
       (inc r)])
    [#{} 0]
    (re-seq #"[^\n]+" input))))

(defn turn [[Δr Δc] infected] (if infected [Δc (- Δr)] [(- Δc) Δr]))

(defn move-in-bursts
  ([[grid [r c] deltas infections]]
   (let [infected (contains? grid [r c])
         [Δr Δc :as deltas] (turn deltas infected)
         grid (if infected (disj grid [r c]) (conj grid [r c]))
         infections (if infected infections (inc infections))]
     [grid [(+ r Δr) (+ c Δc)] deltas infections])))

(defn part-1
  "Day 22 Part 1"
  [input]
  (let [grid (parse-grid input)
        mid-point (quot (inc (apply max (flatten (vec grid)))) 2)]
    (->
     (iterate move-in-bursts [grid [mid-point mid-point] [-1 0] 0])
     (nth 10000)
     (last))))

(defn part-2
  "Day 22 Part 2"
  [input]
  input)
