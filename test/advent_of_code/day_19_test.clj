(ns advent-of-code.day-19-test
  (:require [clojure.test :refer [deftest is]]
            [advent-of-code.day-19 :refer [part-1 part-2]]
            [clojure.java.io :refer [resource]]
            [clojure.string :refer [trim-newline]]))

(deftest part-1-example
  (is (= "ABCDEF" (part-1 (trim-newline (slurp (resource "day-19-example.txt")))))))

(deftest part-2-example
  (is (not= "" (part-2 (trim-newline (slurp (resource "day-19-example.txt")))))))
