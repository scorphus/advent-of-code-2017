(ns advent-of-code.day-23
  (:require [advent-of-code.day-18 :refer [get-value parse-instructions valued]]))

(def instr-map
  {"jnz" (fn [_ x´ _ y´ regs]
           [regs (if (zero? x´) 1 y´)])
   "mul" (fn [x x´ _ y´ regs]
           [(assoc regs x (* x´ y´)) 1])
   "set" (fn [x _ _ y´ regs]
           [(assoc regs x y´) 1])
   "sub" (fn [x x´ _ y´ regs]
           [(assoc regs x (- x´ y´)) 1])})

(defn execute
  ([instructions] (execute instructions {} false))
  ([instructions regs pred]
   (loop [i 0
          regs regs]
     (let [[instr [x y]] (get instructions i)
           [regs jump] ((valued (get instr-map instr)) x y regs)
           regs (assoc regs instr (inc (get regs instr 0)))
           i (+ i jump)]
       (if (and pred (pred regs)) regs
           (if (>= i (count instructions)) regs
               (recur i regs)))))))

(defn part-1
  "Day 23 Part 1"
  [input]
  (let [instructions (parse-instructions input)]
    (get (execute instructions) "mul")))

(defn divides? [k n] (zero? (mod k n)))

(defn prime? [n]
  (or (= 2 n)
      (and (< 1 n)
           (odd? n)
           (not-any? (partial divides? n)
                     (range 3 (inc (Math/sqrt n)) 2)))))

(defn part-2
  "Day 23 Part 2"
  [input]
  (let [instructions (parse-instructions input)
        pred #(> (get % "c" 0) (get % "b" 0))
        regs (execute instructions {"a" 1} pred)
        [b c] [(get regs "b") (get regs "c")]]
    (count (remove prime? (range b (inc c) 17)))))
