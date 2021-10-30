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
  [instructions]
  (loop [i 0
         regs {}]
    (let [[instr [x y]] (get instructions i)
          [regs jump] ((valued (get instr-map instr)) x y regs)
          regs (assoc regs instr (inc (get regs instr 0)))
          i (+ i jump)]
      (if (>= i (count instructions)) regs (recur i regs)))))

(defn part-1
  "Day 23 Part 1"
  [input]
  (let [instructions (parse-instructions input)]
    (get (execute instructions) "mul")))

(defn part-2
  "Day 23 Part 2"
  [input]
  input)
