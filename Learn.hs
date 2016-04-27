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