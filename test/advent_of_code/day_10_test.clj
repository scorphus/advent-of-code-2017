(ns advent-of-code.day-10-test
  (:require [clojure.test :refer [deftest is]]
            [advent-of-code.day-10 :refer [part-1 part-2]]
            [clojure.java.io :refer [resource]]
            [clojure.string :refer [trimr]]))

(deftest part-1-example
  (is (= 12 (part-1 (trimr (slurp (resource "day-10-example.txt"))) 5))))

(deftest part-2-example
  (is (= "4a19451b02fb05416d73aea0ec8c00c0"
         (part-2 (trimr (slurp (resource "day-10-example.txt")))))))

(deftest part-2-example-1
  (is (= "a2582a3a0e66e6e86e3812dcb672a272" (part-2 ""))))

(deftest part-2-example-2
  (is (= "33efeb34ea91902bb2f59c9920caa6cd" (part-2 "AoC 2017"))))

(deftest part-2-example-3
  (is (= "3efbe78a8d82f29979031a4aa0b16a9d" (part-2 "1,2,3"))))

(deftest part-2-example-4
  (is (= "63960835bcdc130f0b66d7ff4f6a5a8e" (part-2 "1,2,4"))))
