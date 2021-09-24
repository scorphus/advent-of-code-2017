(ns advent-of-code.day-07
  (:require [clojure.string :refer [split split-lines]]))

(defn extract-on-hold
  [programs line] (if-let [on-hold (second (split line #" -> " 2))]
                    (into programs (split on-hold #", "))
                    programs))

(defn find-bottom
  [on-hold line] (let [program (first (split line #"\s+" 2))]
                   (if (contains? on-hold program) on-hold (reduced program))))

(defn part-1
  "Day 07 Part 1"
  [input] (let [lines (split-lines input)
                on-hold (reduce extract-on-hold #{} lines)]
            (reduce find-bottom on-hold lines)))

(defn build-tower
  [tower line] (let [[program weight & on-hold] (split line #"[^\w]+")]
                 (assoc tower program [(Integer/parseInt weight) on-hold])))

(defn find-unbalanced
  [tower bottom] (let [[weight on-hold] (get tower bottom)
                       wgts (mapv #(first (get tower %)) on-hold)
                       total-wgts (mapv #(find-unbalanced tower %) on-hold)]
                   (if (and (pos? (count total-wgts)) (apply not= total-wgts))
                     [wgts total-wgts]
                     (apply + weight total-wgts))))

(defn fix-unbalanced
  [[wgts total-wgts]]
  (if-let [total-wgts (first (filter vector? total-wgts))]
    (fix-unbalanced total-wgts)
    (let [wgts-count (into {} (map #(vector (count (second %)) (first %))
                                   (group-by identity total-wgts)))
          unbalanced (get wgts-count 1)
          balanced (get wgts-count (dec (count total-wgts)))
          diff (- unbalanced balanced)
          unbalanced-idx (.indexOf total-wgts unbalanced)
          culprit (get wgts unbalanced-idx)]
      (- culprit diff))))

(defn part-2
  "Day 07 Part 2"
  [input] (let [lines (split-lines input)
                tower (reduce build-tower {} lines)
                bottom (part-1 input)]
            (->>
             (find-unbalanced tower bottom)
             (fix-unbalanced))))
