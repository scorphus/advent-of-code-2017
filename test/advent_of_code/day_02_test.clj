(ns advent-of-code.day-02-test
  (:require [clojure.test :refer [deftest is]]
            [advent-of-code.day-02 :refer [part-1 part-2]]
            [clojure.java.io :refer [resource]]
            [clojure.string :refer [trimr]]))

(deftest part-1-example
  (is (= 18 (part-1 (trimr (slurp (resource "day-02-part-1-example.txt")))))))

(deftest part-2-example
  (is (= 9 (part-2 (trimr (slurp (resource "day-02-part-2-example.txt")))))))
