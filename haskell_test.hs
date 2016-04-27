sayHello :: String -> IO ()
sayHello x = putStrLn ("Hello, world " ++ x ++ "!")

add2Numbers :: Int -> Int -> Int
add2Numbers x y = x + y

triple x = x * 3
square x = x * x

doubleAndMultiplewithPi x = 3.14 * (x * x)