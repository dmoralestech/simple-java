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

isElem c (d : rest) = if c == d
                        then True
                        else isElem c rest
isElem _ [] = False
