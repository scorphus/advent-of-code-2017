(ns advent-of-code.day-12-test
  (:require [clojure.test :refer [deftest is]]
            [advent-of-code.day-12 :refer [part-1 part-2]]
            [clojure.java.io :refer [resource]]
            [clojure.string :refer [trimr]]))

(deftest part-1-example
  (is (= 6 (part-1 (trimr (slurp (resource "day-12-example.txt")))))))

(deftest part-2-example
  (is (not= "" (part-2 (trimr (slurp (resource "day-12-example.txt")))))))
