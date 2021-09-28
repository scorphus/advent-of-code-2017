(ns advent-of-code.day-11-test
  (:require [clojure.test :refer [deftest is]]
            [advent-of-code.day-11 :refer [part-1 part-2]]
            [clojure.java.io :refer [resource]]))

(deftest part-1-example-1
  (is (= 3 (part-1 "ne,ne,ne"))))

(deftest part-1-example-2
  (is (= 0 (part-1 "ne,ne,sw,sw"))))

(deftest part-1-example-3
  (is (= 2 (part-1 "ne,ne,s,s"))))

(deftest part-1-example-4
  (is (= 3 (part-1 "se,sw,se,sw,sw"))))

(deftest part-1-example-5
  (is (= 2 (part-1 "ne,ne,nw,nw"))))

(deftest part-1-example-6
  (is (= 2 (part-1 "se,sw,se,sw"))))

(deftest part-2-example
  (is (= "" (part-2 ""))))
