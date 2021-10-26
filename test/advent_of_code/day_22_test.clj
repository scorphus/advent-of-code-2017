(ns advent-of-code.day-22-test
  (:require [clojure.test :refer [deftest is]]
            [advent-of-code.day-22 :refer [part-1 part-2 turn]]
            [clojure.java.io :refer [resource]]
            [clojure.string :refer [trim-newline]]))

(deftest turn-right
  (is (= [0, 1] (turn [-1, 0] true)))
  (is (= [1, 0] (turn [0, 1] true)))
  (is (= [0, -1] (turn [1, 0] true)))
  (is (= [-1, 0] (turn [0, -1] true))))

(deftest turn-left
  (is (= [0, -1] (turn [-1, 0] false)))
  (is (= [1, 0] (turn [0, -1] false)))
  (is (= [0, 1] (turn [1, 0] false)))
  (is (= [-1, 0] (turn [0, 1] false))))

(deftest part-1-example
  (is (= 5587 (part-1 (trim-newline (slurp (resource "day-22-example.txt")))))))

(deftest part-2-example
  (is (not= "" (part-2 (trim-newline (slurp (resource "day-22-example.txt")))))))
