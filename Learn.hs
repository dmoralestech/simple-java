-- Learn module

module Learn where

x = 10
    * 5 + y

myResult = x * 5

y = 10

double x = x * 2

printInc n = print plusTwo
             where plusTwo = n + 2

printInc2 n = let plusTwo = n + 2
              in print plusTwo

mult1 = x * y
      where x = 5
            y = 6

mult0 x y  = x * y

-- let x = 3; y = 1000 in x * 3 + y
--(\x y -> x * 3 + y) 3 1000


-- let y = 10; x = 10 * 5 + y in x * 5
--  (\y x -> x * 5) 10 (10 * 5 + y)

let x = 7; y = negate x; z = y * 10 in z / x + y
-- (