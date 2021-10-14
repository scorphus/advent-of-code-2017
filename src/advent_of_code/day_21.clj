(ns advent-of-code.day-21)

(def start [["." "#" "."] ["." "." "#"] ["#" "#" "#"]])

(defn split-rules
  [rule]
  (let [[from to] (re-seq #"[^=> ]+" rule)
        from (into [] (re-seq #"[^/]+" from))
        to (into [] (re-seq #"[^/]+" to))
        from (mapv #(into [] (re-seq #"." %)) from)
        to (mapv #(into [] (re-seq #"." %)) to)]
    [from to]))

(defn move-pixel
  [[curr pattern] [pixel & row]]
  [(conj curr pixel) (conj pattern row)])

(defn rotate
  [pattern]
  (loop [rotated []
         pattern pattern]
    (if (nil? (get pattern 0))
      rotated
      (let [[curr pattern] (reduce move-pixel [[] []] pattern)]
        (recur (conj rotated (into [] (reverse curr))) pattern)))))

(defn flip [pattern] (mapv #(into [] (reverse %)) pattern))

(defn rotflip-rules
  [rules [from to]]
  (loop [rules rules
         from from
         [func & funcs] [identity rotate rotate rotate flip rotate rotate rotate]]
    (if (nil? func) rules
        (let [from (func from)]
          (recur (assoc rules from to) from funcs)))))

(defn parse-rules
  [input] (->>
           (re-seq #"[^\n]+" input)
           (mapv split-rules)
           (reduce rotflip-rules {})))

(defn produce-by-2
  [rules row-1 row-2]
  (loop [[a b & row-1] row-1
         [c d & row-2] row-2
         [row-1' row-2' row-3'] [[] [] []]]
    (if (nil? a)
      [row-1' row-2' row-3']
      (let [[x y z] (get rules [[a b] [c d]])
            row-1' (apply conj row-1' x)
            row-2' (apply conj row-2' y)
            row-3' (apply conj row-3' z)]
        (recur row-1 row-2 [row-1' row-2' row-3'])))))

(defn split-and-join-by-2
  [rules pattern]
  (loop [[row-1 row-2 & pattern] pattern
         pattern' []]
    (if (nil? row-1)
      pattern'
      (let [rows-123 (produce-by-2 rules row-1 row-2)
            pattern' (apply conj pattern' rows-123)]
        (recur pattern pattern')))))

(defn produce-by-3
  [rules row-1 row-2 row-3]
  (loop [[a b c & row-1] row-1
         [d e f & row-2] row-2
         [g h i & row-3] row-3
         [row-1' row-2' row-3' row-4'] [[] [] [] []]]
    (if (nil? a)
      [row-1' row-2' row-3' row-4']
      (let [[w x y z] (get rules [[a b c] [d e f] [g h i]])
            row-1' (apply conj row-1' w)
            row-2' (apply conj row-2' x)
            row-3' (apply conj row-3' y)
            row-4' (apply conj row-4' z)]
        (recur row-1 row-2 row-3 [row-1' row-2' row-3' row-4'])))))

(defn split-and-join-by-3
  [rules pattern]
  (loop [[row-1 row-2 row-3 & pattern] pattern
         pattern' []]
    (if (nil? row-1)
      pattern'
      (let [rows-1234 (produce-by-3 rules row-1 row-2 row-3)
            pattern' (apply conj pattern' rows-1234)]
        (recur pattern pattern')))))

(defn produce
  [rules pattern]
  (cond
    (zero? (mod (count pattern) 2)) (split-and-join-by-2 rules pattern)
    :else (split-and-join-by-3 rules pattern)))

(defn iter
  [input iterations]
  (let [rules (parse-rules input)]
    (->>
     (iterate (partial produce rules) start)
     (drop iterations)
     (take 1)
     (flatten)
     (filter #(= % "#"))
     (count))))

(defn part-1
  "Day 21 Part 1"
  [input]
  (iter input 5))

(defn part-2
  "Day 21 Part 2"
  [input]
  (iter input 18))
