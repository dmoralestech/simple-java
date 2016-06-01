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

main = do
   print (sumLst lst)
   print (sumLst [])
