/**
 * Created by darwinmorales on 28/03/2016.
 */

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
        console.log("return 0");
        return 0;
    } else if (x === 0) {
        console.log("return: ", 2 * y);
        return 2 * y;
    } else if (y === 1) {
        console.log("return: 2");
        return 2;
    } else {
        console.log("recursion call: ", x - 1, "Ackermann( ", x, ",", y - 1, ")");
        return Ackermann(x - 1, Ackermann(x, y - 1));
    }
}

console.log(Ackermann(1, 10));
console.log(Ackermann(1, 3));
console.log(Ackermann(2, 4));
console.log(Ackermann(3, 3));

