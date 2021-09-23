(ns advent-of-code.day-06-test
  (:require [clojure.test :refer [deftest is]]
            [advent-of-code.day-06 :refer [part-1 part-2]]
            [clojure.java.io :refer [resource]]))

(deftest part-1-example
  (is (= 5 (part-1 "0	2	7	0"))))

(deftest part-2-example
  (is (not= "" (part-2 "0	2	7	0"))))
