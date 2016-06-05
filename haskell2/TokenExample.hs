import Data.Char

data Franchise = Toyota | Hyundai | Ford
    deriving (Show, Eq)

data Part = Part String String String -- PartNumber PNC Desc

instance Show Part where
   show (Part partNo pnc desc) = "Part partnumber:" ++ (show partNo)  ++ " pnc:" ++ (show pnc) ++ " desc:" ++ (show desc)

partA = Part "9434-34000" "9434" "Bolt"

data Operator = Plus | Minus | Times | Div
    deriving (Show, Eq)

data Token = TokOp Operator
           | TokIdent String
           | TokNum Int
           | TokSpace
    deriving (Show, Eq)

operator :: Char -> Operator
operator c | c == '+' = Plus
           | c == '-' = Minus
           | c == '*' = Times
           | c == '/' = Div

-- tokenize :: String -> [Token]
-- tokenize (c : rest) =
--     if isDigit c
--     then Digit : tokenize rest
--     else Alpha : tokenize rest
-- tokenize [] = []

-- main = print $ tokenize "passwd123"

isElem _ [] = False
isElem c (d : rest) = if c == d
                        then True
                        else isElem c rest

is3elem :: [Char] -> Bool
is3elem str = isElem '3' str
-- is3elem = isElem '3'

tokenizeChar :: Char -> Token
tokenizeChar c | elem c "+-*/" = TokOp(operator c)
               | isDigit c     = TokNum (digitToInt c)
               | isAlpha c     = TokIdent [c]
               | isSpace c     = TokSpace
               | otherwise     = error $ "Cannot tokenize " ++ [c]

filter' :: ( a -> Bool) -> [a] -> [a]
filter' _ [] = []
filter' p (a : as) = if p a
                    then a : filter' p as
                    else filter' p as

deSpace :: [Token] -> [Token]
deSpace = filter notSpace

notSpace :: Token -> Bool
notSpace t = t /= TokSpace

tokenize :: String -> [Token]
tokenize str = map tokenizeChar str
-- tokenize = map tokenizeChar -- can be shortened to this..

-- main = putStrLn $ filter' isDigit "1x+3y4"
--main = print $ deSpace $ tokenize " 1 + 4 / x + 5 - y * 3 "

toInts :: String -> [Int]
toInts = map digitToInt

-- main = print $ toInts "30750" --[3,0,7,5,0]


squares :: [Int] -> [Int]
squares = map(\x -> x * x)

type Point = (Double, Double)
inCircle2 :: [Point] -> [Point]
inCircle2 = filter' (\(x,y) -> sqrt ( x * x + y * y) <= 2.0)

alnums :: String -> (String, String)
alnums str = als "" str
  where
    als acc [] = (acc, [])
    als acc (c : cs) | isAlphaNum c =
                           let (acc', cs') = als acc cs
                           in (c:acc', cs')
                     | otherwise = (acc, c:cs)

-- main = print $ alnums "R2D2+C3Po" -- ("R2D2","+C3Po")


identifier c cs = let (str, cs') = alnums cs in
                  TokIdent (c : cs) : tokenize cs'

squares' ::  [Int] -> Int
squares' = foldl (\acc x ->  acc +  x * x) 0

rev :: [a] -> [a]
rev [a] = foldl (\acc a -> a : acc) [] [a]
-- rev  = foldl (\acc a -> a : acc) []

main = print  $ rev "darwin morales"