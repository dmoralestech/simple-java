/**
 * Created by darwinmorales on 28/03/2016.
 */

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
    console.log(x, y);
    if (y === 0) {
        return 0;
    } else if (x === 0) {
        return 2 * y;
    } else if (y === 1) {
        return 2;
    } else {
        console.log("recursion call: ", x - 1, Ackermann(x, y - 1));
        return Ackermann(x - 1, Ackermann(x, y - 1));
    }
}

console.log(Ackermann(1, 10));
console.log(Ackermann(2, 4));
console.log(Ackermann(3, 3));

