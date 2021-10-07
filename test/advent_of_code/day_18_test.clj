(ns advent-of-code.day-18-test
  (:require [clojure.test :refer [deftest is]]
            [advent-of-code.day-18 :refer [part-1 part-2]]
            [clojure.java.io :refer [resource]]
            [clojure.string :refer [trimr]]))

(deftest part-1-example
  (is (= 4 (part-1 (trimr (slurp (resource "day-18-example.txt")))))))

(deftest part-2-example
  (is (= 1 (part-2 (trimr (slurp (resource "day-18-example.txt")))))))

(deftest part-2-example-1
  (is (= 3 (part-2 "snd 1
snd 2
snd p
rcv a
rcv b
rcv c
rcv d"))))
