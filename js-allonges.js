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