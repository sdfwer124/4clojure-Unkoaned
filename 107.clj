;; 107 Simple closures

; Combine lexical scope and first-class funcs to create
lexical closures which store the values of local bindings.
takes integer n, returns func (f x) which computes xn.
This stores value of n for use outside its defined scope.

((partial #(reduce * (repeat %1 %2)) 2) 16)
;;=> 256

;; What's going on here?

An anonymous function is called
Which repeats a digit a set number of times
and multiplies them all.
partial is saying to do that operation
with this number and the one you have.

Multiplies recursively with reduce.
It will do this with the result
of repeating a number x n times.
The func is called taking 2 as its first argument.
The second argument is supplied by partial,
which says "use this with that to do that"

((partial #(reduce * (repeat %1 %2)) 8) 2)
;;=> 256
user=> (map (partial #(reduce * (repeat %1 %2)) 3) [1 2 3 4])
(1 8 27 64)

(= [1 8 27 64] (map (__ 3) [1 2 3 4]))
(= [1 2 4 8 16] (map #((partial #(reduce * (repeat %1 %2)) %) 2) [0 1 2 3 4]))

partial #(reduce * (repeat %1 %2))
