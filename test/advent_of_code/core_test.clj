(ns advent-of-code.core-test
  (:require [clojure.test :refer [deftest is]]
            [advent-of-code.core :refer [-main]]))

(deftest test-main
  (with-redefs [advent-of-code.day-01/part-1 (fn [_] "this-is-part-1")
                println identity]
    (is (= "this-is-part-1" (-main "01" "1")))))
