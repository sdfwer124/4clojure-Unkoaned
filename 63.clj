;; 63 Group a Seq

{:_id 63 :restricted ["group-by"]
:title "Group a Sequence"
:tests [
"(= (__ #(> % 5) #{1 3 6 8}) {false [1 3], true [6 8]})"
"(= (__ #(apply / %) [[1 2] [2 4] [4 6] [3 6]])\n   {1/2 [[1 2] [2 4] [3 6]], 2/3 [[4 6]]})"
"(= (__ count [[1] [1 2] [3] [1 2 3] [2 3]])\n   {1 [[1] [3]], 2 [[1 2] [2 3]], 3 [[1 2 3]]})"]
:description "Given a function f and a sequence s, write a function which returns a map.
The keys should be the values of f applied to each item in s.
The value at each key should be a vector of corresponding items in the order they appear in s."
:tags ["medium" "seqs" "core-functions"]}

Takes func and seq, returns map
keys are vals of f applied to each item in s.
val at each key is a vec of corresponding items
in the order they appear in s as in group-by

user=> (source group-by)
#object[clojure.core$reduce 0x1855459 "clojure.core$reduce@1855459"]#object[clojure.core$_STAR_ 0x14ca5ec "clojure.core$_STAR_@14ca5ec"]2[0 1 2 3 4](defn group-by 
  "Returns map of elements of coll
  keyed by result of f on each element.
  The val at each key will be a vec of the
  corresponding elements, in the order they appeared in coll."
  [f coll]  
  (persistent!
   (reduce
    (fn [ret x]
      (let [k (f x)]
        (assoc! ret k (conj (get ret k []) x))))
    (transient {}) coll))

(#(apply merge-with into (for [v %2] {(% v) [v]})) #(> % 5) [1 3 6 8]) ;;=> {false [1 3], true [6 8]}
	
(#(apply merge-with into (for [v %2] {(% v) [v]})) #(apply / %) [[1 2] [2 4] [4 6] [3 6]]) ;;=> {1/2 [[1 2] [2 4] [3 6]], 2/3 [[4 6]]}

(#(apply merge-with into (for [v %2] {(% v) [v]})) count [[1] [1 2] [3] [1 2 3] [2 3]]) ;;=> {1 [[1] [3]], 2 [[1 2] [2 3]], 3 [[1 2 3]]}
