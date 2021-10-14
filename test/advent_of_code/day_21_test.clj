(ns advent-of-code.day-21-test
  (:require [clojure.test :refer [deftest is]]
            [advent-of-code.day-21 :refer [iter]]
            [clojure.java.io :refer [resource]]
            [clojure.string :refer [trim-newline]]))

(deftest part-1-example
  (is (= 12 (iter (trim-newline (slurp (resource "day-21-example.txt"))) 2))))
