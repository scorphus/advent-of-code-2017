(ns advent-of-code.day-04-test
  (:require [clojure.test :refer [deftest is]]
            [advent-of-code.day-04 :refer [part-1 part-2]]
            [clojure.java.io :refer [resource]]
            [clojure.string :refer [trimr]]))

(deftest part-1-example
  (is (= 2 (part-1 (trimr (slurp (resource "day-04-example.txt")))))))

(deftest part-2-example
  (let [expected ""]
    (is (not= expected (part-2 (trimr (slurp (resource "day-04-example.txt"))))))))
