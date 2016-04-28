sayHello :: String -> IO ()
sayHello x = putStrLn ("Hello, world " ++ x ++ "!")

add2Numbers :: Int -> Int -> Int
add2Numbers x y = x + y

triple:: Int -> Int
triple x = x * 3

square: Int -> Int
square x = x * x

cube:: Int -> Int
cube x = x * x * x

doubleAndMultiplewithPi x = 3.14 * (x * x)