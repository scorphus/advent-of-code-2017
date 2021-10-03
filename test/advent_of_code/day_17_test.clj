(ns advent-of-code.day-17-test
  (:require [clojure.test :refer [deftest is]]
            [advent-of-code.day-17 :refer [part-1 part-2]]
            [clojure.java.io :refer [resource]]))

(deftest part-1-example
  (is (= 638 (part-1 (slurp (resource "day-17-example.txt"))))))

(deftest part-2-example
  (is (= 1222153 (part-2 (slurp (resource "day-17-example.txt"))))))
