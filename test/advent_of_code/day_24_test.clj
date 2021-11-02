(ns advent-of-code.day-24-test
  (:require [clojure.test :refer [deftest is]]
            [advent-of-code.day-24 :refer [part-1 part-2]]
            [clojure.java.io :refer [resource]]))

(deftest part-1-example
  (is (= 31 (part-1 (slurp (resource "day-24-example.txt"))))))

(deftest part-1-example-1
  (is (= 65 (part-1 "
0/5
0/2
2/2
2/21
9/11
11/5
21/4
21/15
"))))

(deftest part-1-example-2
  (is (= 65 (part-1 "
0/1
0/2
2/2
2/21
9/11
11/1
21/4
21/15
"))))

(deftest part-2-example
  (is (= 19 (part-2 (slurp (resource "day-24-example.txt"))))))
