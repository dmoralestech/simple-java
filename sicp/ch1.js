/**
 * Created by darwinmorales on 28/03/2016.
 */

function count_change(amount) {
    return cc(amount, 5);
}

function cc(amount, kind_of_coins) {
    if (amount === 0 ) {
        return 1;
    } else if (amount < 0 || kind_of_coins === 0 ) {
        return 0;
    } else {
        return cc(amount, kind_of_coins - 1) + cc(amount - first_denomination(kind_of_coins), kind_of_coins);
    }
}

function first_denomination(kinds_of_coins) {
    switch (kinds_of_coins) {
        case 1: return 1;
        case 2: return 5;
        case 3: return 10;
        case 4: return 25;
        case 5: return 50;
    }
}

console.log(count_change(1));
console.log(count_change(45));

function inc(n) {
    console.log("increment: ", n);
    return n + 1;
}

function dec(n) {
    return n - 1;
}

function plus(a, b) {
    console.log(a, b);
    if (a === 0)
        return b;
    else
        return inc(plus(dec(a), b));
}

function plusv2(a, b) {
    return a === 0 ? b : plusv2(dec(a), inc(b));
}

console.log(plus(4, 5));
console.log(plusv2(4, 5));

function findLongestWord(str) {
    var strSplit = str.split(' ');

    var longestWord = strSplit.reduce(function (longest, currentWord) {
        console.log(longest, currentWord);
        if (currentWord.length > longest.length) {
            return currentWord;
        } else {
            return longest;
        }
    }, "");

    console.log(longestWord);
    return longestWord.length;
}

console.log(findLongestWord('The quick brown fox jumped over the lazy dog.'));

function fibonacci(n) {
    console.log("getting fibonacci:", n);
    if (n === 0) {
        return 0;
    } else if (n === 1) {
        return 1;
    } else {
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}

//console.log(fibonacci(2));
//console.log(fibonacci(3));
//console.log(fibonacci(4));
//console.log(fibonacci(5));
//console.log(fibonacci(6));
//console.log(fibonacci(7));

function fib(n) {
    return fib_iter(1, 0, n);
}

function fib_iter(a, b, count) {
    console.log(a, b, count);
    if (count === 0) {
        console.log("b: ", b);
        return b;
    } else {
        console.log("fib_iter: ", a + b, a, "counter: ", count - 1);
        return fib_iter(a + b, a, count - 1);
    }
}

console.log(fib(3));
console.log(fib(7));
console.log(fib(8));

function factorial(n) {
    return fact_iter(1, 1, n);
}

function fact_iter(product, counter, maxCount) {
    if (counter > maxCount) {
        return product;
    } else {
        return fact_iter(counter * product, counter + 1, maxCount);
    }
}

function fact_iter2(product, counter, maxCount) {
    return (counter > maxCount) ? product : fact_iter(counter * product,
        counter + 1,
        maxCount);
}

console.log(factorial(150));

function factorial2(n) {
    return n === 1 ? 1 : n * factorial2(n - 1);
}

console.log(factorial2(150));

function Ackermann(x, y) {
    if (y === 0) {
        console.log("Y return 0");
        return 0;
    } else if (x === 0) {
        console.log("X is 0, return: ", 2 * y);
        return 2 * y;
    } else if (y === 1) {
        console.log("Y return: 2");
        return 2;
    } else {
        console.log("recursion call: ", x - 1, "Ackermann( ", x, ",", y - 1, ")");
        return Ackermann(x - 1, Ackermann(x, y - 1));
    }
}

console.log(Ackermann(1, 3));
console.log(Ackermann(1, 10));
console.log(Ackermann(2, 4));
console.log(Ackermann(3, 3));



