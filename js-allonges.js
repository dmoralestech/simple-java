/**
 * Created by dmorales on 23/05/2016.
 */
// sample code from this book..

function once(fn) {
    var done = false;

    return function()  {
        return done ? void 0 : ((done = true), fn.apply(this, arguments))
    }
}

var askedOnBlindDate = once( function() {
    return "sure why not?!"
});

console.log(askedOnBlindDate());
console.log(askedOnBlindDate());

function maybe(fn) {
    "use strict";
    return function(argument) {
        if (argument != null) {
            return fn.call(this, argument)
        }
    }
}

var someObject = {
    setSize: maybe(function () {
        "use strict";
        this.size = size;
        return this;
    })
}

var newList =  [1, 2, 3].map((x) => x + 2);
// let fibonacci = {
//    [Symbol.iterator]() {
//        let pre = 0, cur=1;
//        return {
//            next() {
//                [pre, cur] = [cur, pre + cur];
//                return {done: false, value: cur}
//            }
//        }
//    }
// };

console.log(newList);

