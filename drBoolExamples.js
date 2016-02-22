/**
 * Created by dmorales on 23/02/2016.
 */


var _ = require('ramda');
var curry = require('lodash.curry');

var match = curry(function(what, str) {
    return str.match(what);
});

var prop = _.curry(function(property, object) {
    return object[property];
});

var Maybe = function(x) {
    this.__value = x;
}

Maybe.of = function (x) {
    return new Maybe(x);
}


Maybe.prototype.isNothing = function() {
    return (this.__value === null || this.__value === undefined);
}

Maybe.prototype.map = function(f) {
    return this.isNothing() ? Maybe.of(null) : Maybe.of(f(this.__value));
}

var maybe1 = Maybe.of('DaM DaM').map(match(/a/ig));

var add = function(x) {
    return function(y) {
        return x + y;
    };
};

var borisMaybe = Maybe.of({
    name: 'Boris',
}).map(_.prop('age')).map(add(10));

var maybeDinah1 = Maybe.of({
    name: 'Dinah',
    age: 14,
}).map(_.prop('age'));

var maybeDinah = Maybe.of({
    name: 'Dinah',
    age: 14,
}).map(_.prop('age')).map(add(10));


var Container = function(x) {
    this.__value = x;
}

Container.of = function(x) {
    return new Container(x);
};

console.log(Container.of("darwin"));
console.log(Container.of({name: "darwin", address: "Sydney"}));


Container.prototype.map = function(f) {
    return Container.of(f(this.__value))
};

console.log(
    Container.of(2).map(function (two) {
        return two + 2;
    }));

console.log(
    Container.of("flame").map(function (s) {
        return s.toUpperCase()
    })
);

console.log(
    Container.of({name: "darwin", address: "Sydney"}).map(function (obj) {
        //obj.address = obj.address.toUpperCase();
        //return obj;
        return {newAddress: obj.address.toUpperCase()};
    })
);
