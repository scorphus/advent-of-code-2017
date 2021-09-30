(ns advent-of-code.day-13)

(defn parse-record
  "Parses input into a map representation of the firewall record"
  [input] (->>
           (re-seq #"\d+" input)
           (map #(Integer/parseInt %))
           (partition 2)))

(defn trip-severity
  "Calculates the severity of the whole trip"
  [severity [depth range]]
  (let [full-cycle (* (dec range) 2)]
    (if (zero? (mod depth full-cycle)) (+ severity (* depth range)) severity)))

(defn part-1
  "Day 13 Part 1"
  [input] (reduce trip-severity 0 (parse-record input)))

(defn has-no-scanner
  "Checks if a layer would be free from scanner"
  [wait [depth range]] (pos? (mod (+ depth wait) (* (dec range) 2))))

(defn find-passage
  "Finds a passage through the firewall without being caught"
  [record wait]
  (if (every? (partial has-no-scanner wait) record) (reduced wait) record))

(defn part-2
  "Day 13 Part 2"
  [input] (reduce find-passage (parse-record input) (range)))
