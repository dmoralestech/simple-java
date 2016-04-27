sayHello :: String -> IO ()
sayHello x = putStrLn ("Hello, world " ++ x ++ "!")

add2Numbers :: Int -> Int -> Int
add2Numbers x y = x + y