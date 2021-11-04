(ns advent-of-code.day-25)

(defn parse-states
  [states-seq]
  (loop [[state c-a v-a m-a s-a c-b v-b m-b s-b & states-seq] states-seq
         states {}]
    (if (nil? state) states
        (let [c-a (if (= c-a "1") true? false?)
              v-a (if (= v-a "1") conj disj)
              m-a (case m-a "right" inc dec)
              c-b (if (= c-b "1") true? false?)
              v-b (if (= v-b "1") conj disj)
              m-b (case m-b "right" inc dec)]
          (recur states-seq (assoc states state [c-a v-a m-a s-a c-b v-b m-b s-b]))))))

(defn parse-blueprint
  [input]
  (let [[start steps & states-seq]
        (re-seq #"\b[A-F]\b|\d+|right|left" input)]
    [start (Integer/parseInt steps) (parse-states states-seq)]))

(defn step
  [[state states pos tape]]
  (let [[c-a v-a m-a s-a _c-b v-b m-b s-b] (get states state)]
    (if (c-a (contains? tape pos))
      [s-a states (m-a pos) (v-a tape pos)]
      [s-b states (m-b pos) (v-b tape pos)])))

(defn part-1
  "Day 25 Part 1"
  [input]
  (let [[start steps states] (parse-blueprint input)]
    [start steps states]
    (->
     (iterate step [start states 0 #{}])
     (nth steps)
     (last)
     (count))))

(defn part-2
  "Day 25 Part 2"
  [input]
  input)
