;;  68 Recurring Theme

{:_id 68
:title "Recurring Theme"
:tests [
"(= __\n  (loop [x 5\n         result []]\n    (if (> x 0)\n      (recur (dec x) (conj result (+ 2 x)))\n      result)))"],
:description "Clojure only has one non-stack-consuming looping construct: recur.
Either a function or a loop can be used as the recursion point.
Either way, recur rebinds the bindings of the recursion point to the values it is passed.
Recur must be called from the tail-position, and calling it elsewhere will result in an error."
:tags ["elementary" "recursion"]}

The only non-stack-consuming looping construct is recur.
A func or a loop can be used as the recursion point.
recur rebinds the bindings of the recursion point
to the values it is passed. Must be called from tail-position.

clojure.core/loop
  (loop [bindings*] exprs*)
Special Form
Evaluates the exprs in a lexical context in which
the symbols in the binding-forms are bound to
their respective init-exprs or parts therein.
Acts as a recur target.

http://clojure.org/special_forms#loop

(loop [bindings* ] exprs*)
Like let, except it establishes a recursion point
at the top of the loop, with arity equal to
the number of bindings. See recur.

Evaluates the exprs in order, then, in parallel, rebinds the bindings of the recursion point to the values of the exprs. If the recursion point was a fn method, then it rebinds the params. If the recursion point was a loop, then it rebinds the loop bindings. Execution then jumps back to the recursion point. The recur expression must match the arity of the recursion point exactly. In particular, if the recursion point was the top of a variadic fn method, there is no gathering of rest args - a single seq (or null) should be passed. recur in other than a tail position is an error.

Note that recur is the only non-stack-consuming looping construct in Clojure. There is no tail-call optimization and the use of self-calls for looping of unknown bounds is discouraged. recur is functional and its use in tail-position is verified by the compiler.

(def factorial
  (fn [n]
    (loop [cnt n acc 1]
       (if (zero? cnt)
            acc
          (recur (dec cnt) (* acc cnt))))))

  (loop [x 5
         result []]
    (if (> x 0)
      (recur (dec x) (conj result (+ 2 x)))
      result))

;;=> [7 6 5 4 3]
