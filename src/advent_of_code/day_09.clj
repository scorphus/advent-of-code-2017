(ns advent-of-code.day-09
  (:require [clojure.string :as str]))

(defn find-score
  [score group] (apply + score (map (partial find-score (inc score)) group)))

(defn part-1
  "Day 09 Part 1"
  [input] (let [replace-pairs [[#"!." ""]
                               [#"<.*?>" ""]
                               [#"\{" "["]
                               [#"\}" "]"]]
                input (reduce (fn [s [m r]] (str/replace s m r)) input replace-pairs)]
            (find-score 1 (load-string input))))

(defn part-2
  "Day 09 Part 2"
  [input] (let [input (str/replace input #"!." "")
                matches (re-seq #"<(.*?)>" input)]
            (reduce #(+ %1 (count (second %2))) 0 matches)))
