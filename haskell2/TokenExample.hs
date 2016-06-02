import Data.Char

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