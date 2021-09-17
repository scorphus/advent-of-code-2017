(ns advent-of-code.day-01-test
  (:require [clojure.test :refer [deftest testing is]]
            [advent-of-code.day-01 :refer [part-1 part-2]]
            [clojure.java.io :refer [resource]]))

(deftest part1-example
  (is (= 0 (part-1 (slurp (resource "day-01-example.txt"))))))

(deftest part1-1
  (is (= 3 (part-1 "1122"))))

(deftest part1-2
  (is (= 4 (part-1 "1111"))))

(deftest part1-3
  (is (= 0 (part-1 "1234"))))

(deftest part1-4
  (is (= 9 (part-1 "91212129"))))

(deftest part2
  (is (= 0 (part-2 (slurp (resource "day-01-example.txt"))))))

(deftest part2-1
  (is (= 6 (part-2 "1212"))))

(deftest part2-2
  (is (= 0 (part-2 "1221"))))

(deftest part2-3
  (is (= 4 (part-2 "123425"))))

(deftest part2-4
  (is (= 12 (part-2 "123123"))))

(deftest part2-5
  (is (= 4 (part-2 "12131415"))))
