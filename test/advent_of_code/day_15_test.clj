(ns advent-of-code.day-15-test
  (:require [clojure.test :refer [deftest is]]
            [advent-of-code.day-15 :refer [part-1 part-2]]
            [clojure.java.io :refer [resource]]))

(deftest part-1-example
  (let [expected ""]
    (is (= expected (part-1 (slurp (resource "day-15-example.txt")))))))

(deftest part-2-example
  (let [expected ""]
    (is (= expected (part-2 (slurp (resource "day-15-example.txt")))))))
