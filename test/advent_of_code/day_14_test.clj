(ns advent-of-code.day-14-test
  (:require [clojure.test :refer [deftest is]]
            [advent-of-code.day-14 :refer [part-1 part-2]]
            [clojure.java.io :refer [resource]]
            [clojure.string :refer [trimr]]))

(deftest part-1-example
  (is (= 8108 (part-1 (trimr (slurp (resource "day-14-example.txt")))))))

(deftest part-2-example
  (is (= 1242 (part-2 (trimr (slurp (resource "day-14-example.txt")))))))
