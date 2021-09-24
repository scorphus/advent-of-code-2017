(ns advent-of-code.day-06
  (:require [advent-of-code.util :as util]
            [clojure.string :refer [split]]))

(defn reallocate
  "Balances the blocks between the memory banks"
  [memory] (let [[lgr_i lgr_v] (util/max-indexed memory)
                 length (count memory)]
             (loop [seen {memory 1}
                    memory (assoc memory lgr_i 0)
                    bank (rem (inc lgr_i) length)
                    blocks lgr_v]
               (if (zero? blocks)
                 (if (contains? seen memory)
                   [seen memory]
                   (let [[lgr_i lgr_v] (util/max-indexed memory)]
                     (recur
                      (assoc seen memory (count seen))
                      (assoc memory lgr_i 0)
                      (rem (inc lgr_i) length)
                      lgr_v)))
                 (let [memory (update memory bank inc)
                       bank (rem (inc bank) length)]
                   (recur seen memory bank (dec blocks)))))))

(defn part-1
  "Day 06 Part 1"
  [input] (let [memory (->> (split input #"\s+")
                            (mapv util/parse-int))]
            (count (first (reallocate memory)))))

(defn part-2
  "Day 06 Part 2"
  [input] (let [memory (->> (split input #"\s+")
                            (mapv util/parse-int))
                [seen memory] (reallocate memory)]
            (- (count seen) (get seen memory))))
