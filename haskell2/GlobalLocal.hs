module GlobalLocal where

printSecond :: IO ()
printSecond = do
  putStrLn "Mate"

main :: IO ()
main = do
putStrLn greeting
where greeting = "Yarrrrr"
-- printSecond

tailDarwin = tail "Darwin"

headDarwin = head "Darwin"

take1Darwin = take 1 "Darwin"

take6Darwin = take 6 "Darwin"

drop4Darwin = drop 4 "Darwin"

index4Darwin = "Darwin" !! 4

getNthChar:: String -> Int -> Char
getNthChar s i = s !! i

-- head "Darwin" --"D"
-- init "Darwin -- "Darwi"
-- tail "Darwin" --"arwin"
-- last "Darwin" -- "n"
-- 'r' `elem` "Darwin" -- True
-- maximum "Darwin" -- 'w'
-- minimum "Darwin" -- 'D'
-- minimum "DARWIN" -- 'A'

listTimes3 = [ x * 3 | x <- [1..10], x * 3 <= 10]
divisibleBy13And9 = [x | x <- [1..500], x `mod` 13 ==0, x `mod` 9 == 0]

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