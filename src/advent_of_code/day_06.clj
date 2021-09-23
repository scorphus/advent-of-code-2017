(ns advent-of-code.day-06
  (:require [advent-of-code.util :as util]
            [clojure.string :refer [split]]))

(defn part-1
  "Day 06 Part 1"
  [input] (let [memory (->> (split input #"\s+")
                            (map util/parse-int)
                            (into []))
                [lgr_i lgr_v] (util/max-indexed memory)
                length (count memory)]
            (loop [seen #{memory}
                   memory (assoc memory lgr_i 0)
                   bank (rem (inc lgr_i) length)
                   blocks lgr_v]
              (if (zero? blocks)
                (if (contains? seen memory)
                  (count seen)
                  (let [[lgr_i lgr_v] (util/max-indexed memory)]
                    (recur
                     (conj seen memory)
                     (assoc memory lgr_i 0)
                     (rem (inc lgr_i) length)
                     lgr_v)))
                (let [value (inc (get memory bank))
                      memory (assoc memory bank value)
                      bank (rem (inc bank) length)]
                  (recur seen memory bank (dec blocks)))))))

(defn part-2
  "Day 06 Part 2"
  [input]
  input)
