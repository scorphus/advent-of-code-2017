(ns advent-of-code.day-03-test
  (:require [clojure.test :refer [deftest is]]
            [advent-of-code.day-03 :refer [part-1 part-2]]))

(deftest part-1-example-1
  (is (= 0 (part-1 "1"))))

(deftest part-1-example-2
  (is (= 3 (part-1 "12"))))

(deftest part-1-example-3
  (is (= 2 (part-1 "23"))))

(deftest part-1-example-4
  (is (= 31 (part-1 "1024"))))

(deftest part-1-example-5
  (is (= 5 (part-1 "26"))))

(deftest part-1-example-6
  (is (= 3 (part-1 "28"))))

(deftest part-1-example-7
  (is (= 6 (part-1 "49"))))

(deftest part-2-example-1
  (is (= 806 (part-2 "747"))))

(deftest part-2-example-2
  (is (= 931 (part-2 "880"))))

(deftest part-2-example-3
  (is (= 45220 (part-2 "42452"))))

(deftest part-2-example-4
  (is (= 45220 (part-2 "45219"))))
