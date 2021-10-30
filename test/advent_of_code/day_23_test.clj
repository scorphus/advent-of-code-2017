(ns advent-of-code.day-23-test
  (:require [clojure.test :refer [deftest is]]
            [advent-of-code.day-23 :refer [part-1 part-2 prime?]]
            [clojure.java.io :refer [resource]]
            [clojure.string :refer [trim-newline]]))

(deftest part-1-example
  (is (= 2 (part-1 (trim-newline (slurp (resource "day-23-example.txt")))))))

(deftest part-2-case-1
  (is (= 49 (part-2 "set b 1\nset c 1000"))))

(deftest part-2-case-2
  (is (= 511 (part-2 "set b 1\nset c 10000"))))

(deftest part-2-case-3
  (is (= 18 (part-2 "set b 359\nset c 719"))))

(deftest prime?-case
  (is (prime? 2)))
