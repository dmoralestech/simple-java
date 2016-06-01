import Data.Char

data Token = Digit | Alpha
    deriving (Show, Eq)

tokenize :: String -> [Token]
tokenize (c : rest) =
    if isDigit c
    then Digit : tokenize rest
    else Alpha : tokenize rest
tokenize [] = []

main = print $ tokenize "passwd123"

isElem _ [] = False
isElem c (d : rest) = if c == d
                        then True
                        else isElem c rest

is3elem :: [Char] -> Bool
is3elem str = isElem '3' str
-- is3elem = isElem '3'
