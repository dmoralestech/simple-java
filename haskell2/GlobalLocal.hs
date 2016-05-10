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

-- filter  (> 5)  [2, 4, 5, 6, 7, 8]
-- takeWhile (<=20) [2, 4..]
-- foldl (*) 2 [1, 2,3 ] -- 12
-- [ 3^n | n <- [1..10]]
-- [[ x * y | y <- [1..10]] | x <- [1..10]]

listTimes3 = [ x * 3 | x <- [1..10], x * 3 <= 10]
divisibleBy13And9 = [x | x <- [1..500], x `mod` 13 ==0, x `mod` 9 == 0]
sumOfList = zipWith (+) [1,2, 3] [4, 5, 6]

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

bobSmith = ("Bob Smith", 22)

bobsName = fst bobSmith

bobsAge = snd bobSmith

names = ["B1", "B2"]
address = ["Manly", "Balgowlah"]

namesAndAddress = zip names address

awesome = ["Papuchon", "curry", ":)"]
alsoAwesome = ["Quake", "The Simons"]
allAwesome = [awesome, alsoAwesome]

-- length [1, 2, 3, 4, 5] -- 5
-- length [(1, 2), (2, 3), (3, 4)] --3
-- length allAwesome -- 2
-- length (concat allAwesome) --5

-- pab = ["Pizza", "Banana", "Apple"]
-- foldl ( \ b a -> take 3 a ++ b) "" pab

f:: (a,b) -> (c,d) -> ((b,d), (a,c))
f x y = ((snd x, snd y), (fst x, fst y))-- length (concat allAwesome) --5

isPalindrome :: (Eq a) => [a] -> Bool
isPalindrome xs = xs == (reverse xs)

addStuff:: Integer -> Integer -> Integer
addStuff a b =  a + b + 5

addTen = addStuff 5

fifteen = addTen 5


nonSense :: Bool -> Integer
nonSense True = 434
nonSense False = 22

typicalCurriedFunction :: Integer -> Bool -> Integer
typicalCurriedFunction i b =  i + (nonSense b)

unCurriedFunction :: (Integer, Bool) -> Integer
unCurriedFunction (i, b)  = i + (nonSense b)

anonymous :: Integer -> Bool -> Integer
anonymous = \i b  -> i + (nonSense b)

anonymousAndManuallyNested :: Integer -> Bool -> Integer
anonymousAndManuallyNested = \i -> \b -> i + (nonSense b)


curryDm f a b = f (a, b)
-- curry fst 1 2

unCurry f (a,b) = f a b
-- unCurry (+)  (1,2)

increment = ( + 1 ) -- returns a function

-- 6 / fromIntegral ( length [1, 2, 3])

f1 ::  Num  a =>  a -> a -> a
f1 x y = x + y + 3

f2:: [Char] -> [Char] -> [Char]
f2 a b = a ++ b ++ " nice"


f3 :: Integer -> Integer -> Integer
f3 x y = x + y + 5

divideThenAdd :: Fractional a => a -> a -> a
divideThenAdd x y = (x / y) + 1