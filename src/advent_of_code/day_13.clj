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

(defn part-2
  "Day 13 Part 2"
  [input]
  input)
