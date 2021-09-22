(ns advent-of-code.core-test
  (:require [clojure.test :refer [deftest is]]
            [advent-of-code.core :refer [-main]]))

(deftest test-main
  (with-redefs [advent-of-code.day-01/part-1 (fn [_] "this-is-part-1")
                println identity]
    (is (= "this-is-part-1" (-main "01" "1")))))

(deftest test-main-not-found
  (try
    (-main "01" "0")
    (throw (Exception. "shouldn't reach this line"))
    (catch AssertionError _ _)))
