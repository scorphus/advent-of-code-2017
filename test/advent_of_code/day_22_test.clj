(ns advent-of-code.day-22-test
  (:require [clojure.test :refer [deftest is]]
            [advent-of-code.day-22 :refer [part-1 part-2 turn evolve]]
            [clojure.java.io :refer [resource]]
            [clojure.string :refer [trim-newline]]))

(deftest turn-right
  (is (= [0, 1] (turn [-1, 0] :infected)))
  (is (= [1, 0] (turn [0, 1] :infected)))
  (is (= [0, -1] (turn [1, 0] :infected)))
  (is (= [-1, 0] (turn [0, -1] :infected))))

(deftest turn-left
  (is (= [0, -1] (turn [-1, 0] nil)))
  (is (= [1, 0] (turn [0, -1] nil)))
  (is (= [0, 1] (turn [1, 0] nil)))
  (is (= [-1, 0] (turn [0, 1] nil))))

(deftest turn-not
  (is (= [-1, 0] (turn [-1, 0] :weakened)))
  (is (= [0, -1] (turn [0, -1] :weakened)))
  (is (= [1, 0] (turn [1, 0] :weakened)))
  (is (= [0, 1] (turn [0, 1] :weakened))))

(deftest turn-back
  (is (= [1, -0] (turn [-1, 0] :flagged)))
  (is (= [-0, 1] (turn [0, -1] :flagged)))
  (is (= [-1, -0] (turn [1, 0] :flagged)))
  (is (= [-0, -1] (turn [0, 1] :flagged))))

(deftest evolve-should-not-evolve
  (is (= :infected (evolve false nil)))
  (is (= nil (evolve false :infected))))

(deftest evolve-should-evolve
  (is (= :weakened (evolve true nil)))
  (is (= :infected (evolve true :weakened)))
  (is (= :flagged (evolve true :infected)))
  (is (= nil (evolve true :flagged))))

(deftest part-1-example
  (is (= 5587 (part-1 (trim-newline (slurp (resource "day-22-example.txt")))))))

(deftest part-2-example
  (is (= 2511944 (part-2 (trim-newline (slurp (resource "day-22-example.txt")))))))
