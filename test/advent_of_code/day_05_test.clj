(ns advent-of-code.day-05-test
  (:require [clojure.test :refer [deftest is]]
            [advent-of-code.day-05 :refer [part-1 part-2]]
            [clojure.java.io :refer [resource]]))

(deftest part-1-example
  (is (= 5 (part-1 (slurp (resource "day-05-example.txt"))))))

(deftest part-2-example
  (is (= 10 (part-2 (slurp (resource "day-05-example.txt"))))))
