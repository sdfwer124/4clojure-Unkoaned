;; 95

Check whether or not a given sequence represents a binary tree.
Each node in the tree must have a value,
a left child, and a right child.

(fn tree? [coll]
  (or (nil? coll)
      (and (sequential? coll) 
           (= 3 (count coll))
           (tree? (second coll))
           (tree? (nth coll 2)))))

((fn t? [[v l r :as t]]
   (and (= (count t) 3)
        (every? #(or (nil? %) (and (coll? %) (t? %))) [l r])))
 '(:a (:b nil nil) nil))
;;=> true

((fn t? [[v l r :as t]] (and (= (count t) 3) (every? #(or (nil? %) (and (coll? %) (t? %))) [l r]))) '(:a (:b nil nil))) ;;=> false
((fn t? [[v l r :as t]] (and (= (count t) 3) (every? #(or (nil? %) (and (coll? %) (t? %))) [l r]))) [1 nil [2 [3 nil nil] [4 nil nil]]])
;;=> true

((fn t? [[v l r :as t]] (and (= (count t) 3) (every? #(or (nil? %) (and (coll? %) (t? %))) [l r]))) [1 [2 nil nil] [3 nil nil] [4 nil nil]])
;;=> false

((fn t? [[v l r :as t]] (and (= (count t) 3) (every? #(or (nil? %) (and (coll? %) (t? %))) [l r]))) [1 [2 [3 [4 nil nil] nil] nil] nil])
;;=> true

((fn t? [[v l r :as t]] (and (= (count t) 3) (every? #(or (nil? %) (and (coll? %) (t? %))) [l r]))) [1 [2 [3 [4 false nil] nil] nil] nil])
;;=> false

((fn t? [[v l r :as t]] (and (= (count t) 3) (every? #(or (nil? %) (and (coll? %) (t? %))) [l r]))) '(:a nil ()))
;;=> false
