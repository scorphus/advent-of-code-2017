(ns advent-of-code.day-18
  (:require [clojure.string :refer [split upper-case]]))

(defn get-value
  [regs id]
  (cond
    (nil? id) id
    (not= id (upper-case id)) (get regs id 0)
    :else (Integer/parseInt id)))

(def instr-map
  {"add" (fn [x x-val _ y-val regs]
           [(assoc regs x (+ x-val y-val))
            :continue
            1])
   "jgz" (fn [_ x-val _ y-val regs]
           [regs
            :continue
            (if (pos? x-val) y-val 1)])
   "mod" (fn [x x-val _ y-val regs]
           [(assoc regs x (mod x-val y-val))
            :continue
            1])
   "mul" (fn [x x-val _ y-val regs]
           [(assoc regs x (* x-val y-val))
            :continue
            1])
   "rcv" (fn [x _ _ _ regs]
           (if-let [rcv (peek (get regs "queue"))]
             [(assoc (update regs "queue" pop) x rcv) :continue 1]
             [regs :wait 0]))
   "set" (fn [x _ _ y-val regs]
           [(assoc regs x y-val)
            :continue
            1])
   "snd" (fn [_ x-val _ _ regs]
           [(assoc (assoc regs "snd-cnt" (inc (get regs "snd-cnt" 0))) "snd" x-val)
            :send
            1])})

(defn parse-instructions
  [input]
  (let [instructions (split input #"\n")]
    (->>
     (map #(re-seq #"[^\s]+" %) instructions)
     (mapv (fn [[instr x & y]] [instr [x (and y (first y))]])))))

(defn valued
  [f] (fn [x y regs] (f x (get-value regs x) y (get-value regs y) regs)))

(defn execute
  [instructions]
  (loop [i 0
         regs {}]
    (let [[instr [x y]] (get instructions i)
          [regs act jump] ((valued (get instr-map instr)) x y regs)
          i (+ i jump)]
      (if (= act :wait) regs (recur i regs)))))

(defn part-1
  "Day 18 Part 1"
  [input]
  (let [instructions (parse-instructions input)]
    (get (execute instructions) "snd")))

(defn execute-twice
  [instructions]
  (loop [i 0
         j 0
         regs-i {"p" 0 "queue" (clojure.lang.PersistentQueue/EMPTY)}
         regs-j {"p" 1 "queue" (clojure.lang.PersistentQueue/EMPTY)}]
    (let [[instr-i [xi yi]] (get instructions i)
          [instr-j [xj yj]] (get instructions j)
          [regs-i act-i jump-i] ((valued (get instr-map instr-i)) xi yi regs-i)
          [regs-j act-j jump-j] ((valued (get instr-map instr-j)) xj yj regs-j)
          i (+ i jump-i)
          j (+ j jump-j)]
      (if (= [act-i act-j] [:wait :wait])
        [regs-i regs-j]
        (let [regs-i (if (= act-j :send)
                       (update regs-i "queue" conj (get regs-j "snd"))
                       regs-i)
              regs-j (if (= act-i :send)
                       (update regs-j "queue" conj (get regs-i "snd"))
                       regs-j)]
          (recur i j regs-i regs-j))))))

(defn part-2
  "Day 18 Part 2"
  [input]
  (let [instructions (parse-instructions input)]
    (get (second (execute-twice instructions)) "snd-cnt")))
