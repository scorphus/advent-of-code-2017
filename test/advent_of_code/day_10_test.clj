(ns advent-of-code.day-10-test
  (:require [clojure.test :refer [deftest is]]
            [advent-of-code.day-10 :refer [part-1 part-2]]
            [clojure.java.io :refer [resource]]
            [clojure.string :refer [trimr]]))

(deftest part-1-example
  (is (= 12 (part-1 (trimr (slurp (resource "day-10-example.txt"))) 5))))

(deftest part-2-example
  (is (not= "" (part-2 (trimr (slurp (resource "day-10-example.txt")))))))
