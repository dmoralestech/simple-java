module Print3 where

main:: IO ()
main = do
    putStrLn "Count 1 to 4"
    putStr "1, "
    putStr "2, "
    putStr "3, "
    putStrLn "4 "
    putStrLn myGreeting
    putStrLn nextGreeting
    where nextGreeting = concat [hello, " ", world]

myGreeting :: String
myGreeting = "My " ++ "Greeting"

hello :: String
hello = "hello"

world :: String
world = "world"