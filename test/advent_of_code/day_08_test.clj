(ns advent-of-code.day-08-test
  (:require [clojure.test :refer [deftest testing is]]
            [advent-of-code.day-08 :refer [part-1 part-2]]
            [clojure.java.io :refer [resource]]))

(deftest part-1-example
  (let [expected ""]
    (is (= expected (part-1 (slurp (resource "day-08-example.txt")))))))

(deftest part-2-example
  (let [expected ""]
    (is (= expected (part-2 (slurp (resource "day-08-example.txt")))))))
