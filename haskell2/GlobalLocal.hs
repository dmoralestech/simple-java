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

subtractThenAdd :: Num a => a -> a -> a
subtractThenAdd x y = (x - y) + 1

myConcat x  = x ++ " yo"


myMult x = ( x  / 3 ) * 5

myCom  x = x  > (length [1..10])

myAlpha x =  x < 'z'

triple x = x * 3

triple2 :: Integer -> Integer
triple2 x = x * 3 :: Integer -- If we only limit this function to an Integer

triple3 x = tripleItYo x
        where tripleItYo:: Integer -> Integer
              tripleItYo y = y * 3


-- class is for creating typeclass and this is where you define operators
class Numberish a where
    fromNumber :: Integer -> a
    toNumber ::  a -> Integer
    defaultNumber :: a

newtype Age  =
        Age  Integer
        deriving (Eq, Show)

-- instance is where you implement the functionality for the operators of type Age
instance Numberish Age where
    fromNumber n = Age n
    toNumber (Age n)  = n
    defaultNumber = Age 42

newtype Year =
        Year Integer
        deriving (Eq, Show)

instance Numberish Year where
        fromNumber n = Year n
        toNumber (Year n) = n
        defaultNumber = Year 1998

sumNumberish :: Numberish  a => a -> a -> a
sumNumberish  a a' = fromNumber  summed
    where  integerOfA = toNumber a
           integerOfAPrime  = toNumber a'
           summed = integerOfA + integerOfAPrime


elem2 :: Eq a => a -> [a] -> Bool
elem2 _ [] = False
elem2 x (y : ys)
    | x == y    = True
    | otherwise = elem2 x ys

data RGB  = RGB Int Int Int


instance Eq RGB where
    (RGB r1 g1 b1) == (RGB r2 g2 b2) =
        (r1 == r2) && (g1 == g2) && (b1 == b2)

instance Show RGB where
   show (RGB r g b) = "RGB " ++ (show r)  ++ " " ++ (show g) ++ " " ++ (show b)

colors = [RGB 255 0 0, RGB 0 255 0, RGB 0 0 255]
green = RGB  0 255 0
isGreenInColors = elem2 green colors

data Maybe' a = Nothing' | Just' a

instance (Eq a) => Eq (Maybe' a) where
    Nothing' == Nothing'   = True
    Nothing' == ( Just' _) = False
    (Just' _)  == Nothing' = False
    (Just' x)  == (Just' y) = x == y

type DmInt = Int

dmAdd :: DmInt -> DmInt -> DmInt
dmAdd x y = x + y

type Point = (Double, Double)

midPoint' :: (Double, Double) -> (Double, Double) -> (Double, Double)
midPoint' (x, y) (x2, y2) =
        ((x + x2) / 2,(y + y2) / 2)

midPoint :: Point -> Point -> Point
midPoint (x, y) (x2, y2) =
        ((x + x2) / 2,(y + y2) / 2)

newtype CustomerId = CustomerId Int
    deriving Show


-- this won't work
-- badCustomer :: CustomerId
-- badCustomer = 13


goodCustomer :: CustomerId
goodCustomer = CustomerId 13


customerToInt :: CustomerId -> Int
customerToInt (CustomerId i) = i

--customerToInt (goodCustomer)  --13

data Customer = MakeCustomer{
    customerId :: CustomerId,
    name :: String,
    luckyNumber :: Int}
    deriving Show

darwin :: Customer
darwin  = MakeCustomer {
    customerId = CustomerId 22,
    name = "Darwin",
    luckyNumber = 33
}
