{:_id 26 :title "Fibonacci Sequence"
:tests ["(= (__ 3) '(1 1 2))"
"(= (__ 6) '(1 1 2 3 5 8))"
"(= (__ 8) '(1 1 2 3 5 8 13 21))"]
:description "Write a function which returns the first X fibonacci numbers."
:tags ["easy" "Fibonacci" "seqs"]}

; using loop/recur
; fails on 93 on my machine

(defn fib [n]
  (loop [x [1 1]]
    (if (>= (count x) n)
      x
      (recur (conj x (apply + (take-last 2 x)))))))


; using destructuring
; also overflows on 93

(defn nfibos [n]
  (take n
    (map first
      (iterate
        (fn [[a b]]
            [b (+ a b)])
        [1 1]))))

user=> (nfibos 8)
(1 1 2 3 5 8 13 21)

=> ((fn [[a b]] [b (+ a b)]) [1 1])
[1 2]

=> (take 8 (iterate
             (fn [[a b]] [b (+ a b)])
             [1 1]))
([1 1] [1 2] [2 3] [3 5] [5 8] [8 13] [13 21] [21 34])

user=> (map first '([1 1] [1 2] [2 3] [3 5] [5 8] [8 13] [13 21] [21 34]))
(1 1 2 3 5 8 13 21)

user> (doc iterate)
-------------------------
clojure.core/iterate
([f x])
  Returns a lazy sequence of
  x, (f x), (f (f x)) etc.
  f must be free of side-effects
