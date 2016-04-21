/**
 * Created by dmorales on 18/04/2016.
 */
/**
 * Created by dmorales on 18/04/2016.
 */

var _ = require("./underscore.js");

var reduce = function (f, acc, xs) {
    "use strict";
    if (xs.length === 0) return acc;
    return reduce(f, f(acc, _.first(xs)), _.rest(xs));
}

var sum = function (xs) {
    "use strict";
    return reduce(function (acc, x) {
        return x + acc;
    }, 0, xs);
};

console.log(sum([1, 2, 3, 4]));

var reverse = function (xs) {
    "use strict";
    return reduce(function (acc, x) {
        return [x].concat(acc)
    }, [], xs);
};

console.log(reverse([1, 2, 3, 4]));

var person1 = {
    "name": "Homer Simpson",
    "address": {
        "street": "123 Fake St.",
        "city": "Springfield"
    }
};

var Maybe = function (value) {
    var Nothing = {
        bind: function (fn) {
            return this;
        },
        isNothing: function () {
            return true;
        },
        val: function () {
            throw new Error("cannot call val() nothing");
        },
        maybe: function (def, fn) {
            return def;
        }
    };

    var Something = function (value) {
        return {
            bind: function (fn) {
                return Maybe(fn.call(this, value));
            },
            isNothing: function () {
                return false;
            },
            val: function () {
                return value;
            },
            maybe: function (def, fn) {
                return fn.call(this, value);
            }
        };
    };

    if (typeof value === 'undefined' || value === null)
        return Nothing;

    return Something(value);
};

var people = [person1];

console.log(Maybe(people)
    .bind(function (people) {
        return people[0]
    })
    .maybe("No person", function (person) {
        return person;
    })
);

function MONAD3(modifier) {
    var prototype = Object.create(null);

    function unit(value) {
        var monad = Object.create(prototype);
        monad.bind = function (func, args) {
            return func(value, args);
        };
        if (typeof modifier === 'function') {
            modifier(monad, value);
        }
        return monad;
    }

    return unit;
}

var maybe = MONAD3(function (monad, value) {
    if (value === null || value === undefined) {
        monad.is_null = true;
        monad.bind = function () {
            return monad;
        }
    }
});

var monad = maybe(null);
monad.bind(console.log);


function MONAD() {
    "use strict";

    return function unit(value) {
        var monad = Object.create(null);
        monad.bind = function (func) {
            return func(value);
        };
        return monad;
    }
}

var identity = MONAD();
var monad = identity("Hello World");
monad.bind(console.log);

function MONAD2() {
    "use strict";
    var prototype = Object.create(null);

    function unit(value) {
        var monad = Object.create(prototype);
        monad.bind = function (func, args) {
            return func(value, args);
        }
        return monad;
    }

    unit.lift = function (name, func) {
        prototype[name] = function (args) {
            return unit(this.bind(func, args));
        }
        return unit;
    };

    return unit;
}

var ajax = MONAD2().lift('log', console.log);
var monad = ajax("Hello world");

monad.log();


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
 unit(value).bind(f) ==== bind(unit(value), f) ==== f(value)

 monad.bind(unit) ===== bind(monad, unit) ==== monad

 monad.bind(f).bind(g) ==== bind(bind(monad, f), g) ===== bind(monad, function(value) {
 return bind(f(value), g); })si


 */

