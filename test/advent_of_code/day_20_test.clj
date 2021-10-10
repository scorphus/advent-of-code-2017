(ns advent-of-code.day-20-test
  (:require [clojure.test :refer [deftest is]]
            [advent-of-code.day-20 :refer [part-1 part-2]]
            [clojure.java.io :refer [resource]]
            [clojure.string :refer [trim-newline]]))

(deftest part-1-example
  (is (= 0 (part-1 (trim-newline (slurp (resource "day-20-example.txt")))))))

(def example-2 "p=<-6,0,0>, v=< 3,0,0>, a=< 0,0,0>
p=<-4,0,0>, v=< 2,0,0>, a=< 0,0,0>
p=<-2,0,0>, v=< 1,0,0>, a=< 0,0,0>
p=< 3,0,0>, v=<-1,0,0>, a=< 0,0,0>")

(deftest part-1-example-2
  (is (= 3 (part-1 example-2))))

(deftest part-2-example-2
  (is (= 1 (part-2 example-2))))
