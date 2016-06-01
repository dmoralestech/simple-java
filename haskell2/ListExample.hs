import Data.Char
-- data List = Cons Int List | Empty
--
-- singleton :: List -> Bool
-- singleton (Cons _ Empty) = True
-- singleton _ = False
--
-- sumLst :: [Int] -> Int
-- sumLst (i : rest) = i + sumLst rest
-- sumLst [] = 0
--
-- lst = Cons 2 (Cons 4 (Cons 6 Empty))
--
-- main = do
--    print $ singleton Empty
--    print $ singleton $ Cons 2 Empty
--    print $ singleton $ Cons 3 $ Cons 4 Empty

-- parametized
data List a = Cons a (List a) | Empty

sumLst :: [Int] -> Int
sumLst (i : rest) = i + sumLst rest
sumLst [] = 0

lst = [2, 4, 6]

-- main = do
--    print (sumLst lst)
--    print (sumLst [])
--    print ( 2 : 8 : 64: [])


norm :: [Double] -> Double
norm lst = sqrt ( squares lst)

squares :: [Double] -> Double
squares [] = 0.0
squares (x : xs) = x * x + squares xs


decimate :: [a] -> [a]
decimate (a : _ : _ : rest) = a : decimate rest
decimate (a : _) = [a]
decimate _ = []

zipLst :: ([a], [b]) -> [(a,b)]
zipLst ((x : xs), (y : ys)) = (x,y) : zipLst (xs, ys)
zipLst (_,_) = []



map' :: (a -> b) -> [a] -> [b]
map' _ [] = []
map' f (a : as) = f a : map' f as

main = print $ map toUpper "hello world!"