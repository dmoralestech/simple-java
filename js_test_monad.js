/**
 * Created by dmorales on 18/04/2016.
 */
/**
 * Created by dmorales on 18/04/2016.
 */

function MONAD() {
    "use strict";

    return function unit(value) {
        var monad = Object.create(null);
        monad.bind = function(func) {
            return func(value);
        };
        return monad;
    }
}

var identity = MONAD();
var monad = identity("Hello World");
monad.bind(console.log);

var sine = function (x) {
    return [Math.sin(x), 'sine  was called'];
};

var square = function (x) {
    return [x * x, 'sine  was called'];
};

var cube = function (x) {
    return [x * x * x, 'cube was called'];
};

var compose = function (f, g) {
    return function (x) {
        return f(g(x));
    };
};

// unit :: Number -> (Number,String)
var unit = function (x) {
    return [x, ''];
};

// bind :: (Number -> (Number,String)) -> ((Number,String) -> (Number,String))
var bind = function (f) {
    return function (tuple) {
        var x = tuple[0]
        var s = tuple[1]
        var fx = f(x)
        var y = fx[0]
        var t = fx[1];
        return [y, s + t];
    };
};

var sineBind = bind(sine);
var cubeBind = bind(cube);

var f = compose(sineBind, cubeBind);

console.log(f(unit(3)));

/*
Axioms
bind(unit(value), f) ==== f(value)

bind(monad, unit) ==== monad

bind(bind(monad, f), g) ===== bind(monad, function(value) {
                                            return bind(f(value), g); })si


 */

