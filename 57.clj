;; 57 Recursion

A recursive function calls itself.

(defn foo [x]
  (when (> x 0) (conj (foo (dec x)) x)))
#'user/foo
user> (foo 5)
(5 4 3 2 1)
