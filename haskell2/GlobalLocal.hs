module GlobalLocal where

printSecond :: IO ()
printSecond = do
  putStrLn "Mate"

main :: IO ()
main = do
putStrLn greeting
where greeting = "Yarrrrr"
-- printSecond




topLevelFunction:: Integer -> Integer
topLevelFunction x = x + woot + topLevelValue
    where woot :: Integer
          woot = 10

topLevelValue :: Integer
topLevelValue = 5


area d = pi * (r * r)
    where r = d / 2

concatList = (++) [2, 3] [4, 5]

customGreeting = (++) "hello" ((++) " " "world")