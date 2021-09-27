(ns advent-of-code.day-09-test
  (:require [clojure.test :refer [deftest is]]
            [advent-of-code.day-09 :refer [part-1 part-2]]))

(deftest part-1-example-1
  (is (= 1 (part-1 "{}"))))

(deftest part-1-example-2
  (is (= 6 (part-1 "{{{}}}"))))

(deftest part-1-example-3
  (is (= 5 (part-1 "{{},{}}"))))

(deftest part-1-example-4
  (is (= 16 (part-1 "{{{},{},{{}}}},"))))

(deftest part-1-example-5
  (is (= 1 (part-1 "{<a>,<a>,<a>,<a>}"))))

(deftest part-1-example-6
  (is (= 9 (part-1 "{{<ab>},{<ab>},{<ab>},{<ab>}}"))))

(deftest part-1-example-7
  (is (= 9 (part-1 "{{<!!>},{<!!>},{<!!>},{<!!>}}"))))

(deftest part-1-example-8
  (is (= 3 (part-1 "{{<a!>},{<a!>},{<a!>},{<ab>}}"))))

(deftest part-2-example
  (is (= "" (part-2 ""))))
