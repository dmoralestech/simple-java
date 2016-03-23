 (ns Clojure-Hello)

(defn factorial [n]
      (reduce * (range 1 (inc n))))

(println (factorial 5))

(println "hello world")

(defn wisdom
  [words]
  (str words ", Daniel-san"))

(defn year-end-evaluation
  []
  (if (> (rand) 0.5)
    "You get a raise!"
    "Better luck next year!"))

(def great-baby-name "Rosanthony")
great-baby-name
; => "Rosanthony"

(let [great-baby-name "Bloodthunder"]
  great-baby-name)
; => "Bloodthunder"

great-baby-name
; => "Rosanthony"

 ;(sum [3 4 4])
(defn sum
  ([vals] (sum vals 0))
  ([vals accumulating-total]
       (if (empty? vals)
             accumulating-total
             (sum (rest vals) (+ (first vals) accumulating-total)))))

(println (sum [23 3 4 4 7 45]))