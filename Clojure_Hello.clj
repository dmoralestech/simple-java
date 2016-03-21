 (ns Clojure-Hello)

(defn factorial [n]
      (reduce * (range 1 (inc n))))

(println (factorial 5))

(println "hello world")