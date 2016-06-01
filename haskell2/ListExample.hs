data List = Cons Int List | Empty

singleton :: List -> Bool
singleton (Cons _ Empty) = True
singleton _ = False

sumLst :: List -> Int
sumLst (Cons i rest) = i + sumLst rest
sumLst Empty = 0

lst = Cons 2 (Cons 4 (Cons 6 Empty))

main = do
    print (sumLst lst)
    print (sumLst Empty)

-- main = do
--    print $ singleton Empty
--    print $ singleton $ Cons 2 Empty
--    print $ singleton $ Cons 3 $ Cons 4 Empty