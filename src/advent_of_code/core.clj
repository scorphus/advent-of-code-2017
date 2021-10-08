(ns advent-of-code.core
  (:require [clojure.java.io :refer [resource]]
            [clojure.string :refer [trim-newline]]
            [advent-of-code.day-01]
            [advent-of-code.day-02]
            [advent-of-code.day-03]
            [advent-of-code.day-04]
            [advent-of-code.day-05]
            [advent-of-code.day-06]
            [advent-of-code.day-07]
            [advent-of-code.day-08]
            [advent-of-code.day-09]
            [advent-of-code.day-10]
            [advent-of-code.day-11]
            [advent-of-code.day-12]
            [advent-of-code.day-13]
            [advent-of-code.day-14]
            [advent-of-code.day-15]
            [advent-of-code.day-16]
            [advent-of-code.day-17]
            [advent-of-code.day-18]
            [advent-of-code.day-19]
            [advent-of-code.day-20]
            [advent-of-code.day-21]
            [advent-of-code.day-22]
            [advent-of-code.day-23]
            [advent-of-code.day-24]
            [advent-of-code.day-25]))

(defn read-input
  [day]
  (trim-newline (slurp (resource (format "day-%s.txt" day)))))

(defn -main
  "Used to dispatch tasks from the command line.

  $ lein run 01 1"
  [day part]
  (let [name-space (format "advent-of-code.day-%s" day)
        name (format "part-%s" part)
        func (resolve (symbol name-space name))]
    (assert (some? func) (format "%s.%s not found" name-space name))
    (println (func (read-input day)))))
