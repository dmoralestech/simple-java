/**
 * Created by darwinmorales on 28/03/2016.
 */

function factorial(n) {
    return fact_iter(1, 1, n);
}

function fact_iter(product, counter, maxCount) {
    if (counter  > maxCount) {
        return product;
    } else {
        return fact_iter(counter * product, counter + 1, maxCount);
    }
}

function fact_iter2(product, counter, maxCount) {
    return (counter  > maxCount) ? product : fact_iter(counter * product,
                                                       counter + 1,
                                                       maxCount);
}

console.log(factorial(180));

function factorial2(n) {
    return n === 1 ? 1 : n *  factorial2( n - 1);
}

console.log(factorial2(180));