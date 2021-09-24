(ns advent-of-code.day-07-test
  (:require [clojure.test :refer [deftest is]]
            [advent-of-code.day-07 :refer [part-1 part-2]]
            [clojure.java.io :refer [resource]]))

(deftest part-1-example
  (is (= "tknk" (part-1 (slurp (resource "day-07-example.txt"))))))

(deftest part-2-example
  (is (= 60 (part-2 (slurp (resource "day-07-example.txt"))))))
