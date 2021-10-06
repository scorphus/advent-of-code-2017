(ns advent-of-code.day-18
  (:require [clojure.string :refer [split upper-case]]))

(def instructions-map
  {"add" (fn [x x-val _ y-val regs] [(assoc regs x (+ x-val y-val)) 1])
   "jgz" (fn [_ x-val _ y-val regs] [regs (if (pos? x-val) y-val 1)])
   "mod" (fn [x x-val _ y-val regs] [(assoc regs x (mod x-val y-val)) 1])
   "mul" (fn [x x-val _ y-val regs] [(assoc regs x (* x-val y-val)) 1])
   "rcv" (fn [_ x-val _ _____ regs] [regs (if (zero? x-val) 1 Integer/MAX_VALUE)])
   "set" (fn [x _____ _ y-val regs] [(assoc regs x y-val) 1])
   "snd" (fn [_ x-val _ _____ regs] [(assoc regs "snd" x-val) 1])})

(defn parse-instructions
  [input]
  (let [instructions (split input #"\n")]
    (->>
     (map #(re-seq #"[^\s]+" %) instructions)
     (map (fn [[instr & args]] [(get instructions-map instr) args]))
     (mapv (fn [[func [x & y]]] [func [x (and y (first y))]])))))

(defn get-value
  [registers id]
  (cond
    (nil? id) id
    (not= id (upper-case id)) (get registers id 0)
    :else (Integer/parseInt id)))

(defn execute
  [instructions]
  (loop [i 0
         registers {}]
    (if (or (neg? i) (>= i (count instructions)))
      registers
      (let [[instr [x y]] (get instructions i)
            x-val (get-value registers x)
            y-val (get-value registers y)
            [registers jump] (instr x x-val y y-val registers)
            i (+ i jump)]
        (recur i registers)))))

(defn part-1
  "Day 18 Part 1"
  [input]
  (let [instructions (parse-instructions input)]
    (get (execute instructions) "snd")))

(defn part-2
  "Day 18 Part 2"
  [input]
  input)
