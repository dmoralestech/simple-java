/**
 * Created by dmorales on 9/12/2015.
 */

var i;

i = 2 + 3;

console.log("sum is:  " + i);

var proto = {
    hello: function hello() {
        return "Hello, my name is " + this.name;
    },
    sayBye: function() {
        return "Bye " + this.name;
    }
};


var george = Object.create(proto);
george.name = "George";
var message = george.hello();
var message2 = george.sayBye();

var myObj = {
    key: "value"
};

myObj = new Object();
myObj.key = "value";


function assert(value, desc){
    console.log(value ? "pass: " + desc : "fail: " + desc);
    //var li = document.createElement("li");
    //li.className = value ? "pass" : "fail";
    //li.appendChild(document.createTextNode(desc));
    //document.getElementById("results").appendChild(li);
}


function Ninja() {}

Ninja.prototype.swingSword = function() { return true;};

var ninja1 =  Ninja();
assert(ninja1 === undefined, "No instance of Ninja created.");

var ninja2 = new Ninja();
assert(ninja2 && ninja2.swingSword && ninja2.swingSword());

//assert (typeof ninja == "object", "The type of the instance is object");
//assert (ninja instanceof Ninja, "instanceof identifies the constructor");
//assert (ninja.constructor == Ninja, "The ninja object was created by the Ninja function");

function Ninja2() {
    this.swung = false;
    this.swingSword = function() {
        return !this.swung;
    };
}

Ninja2.prototype.swingSword = function() {
    return this.swung;
};

var ninja = new Ninja2();
assert(ninja.swingSword(), "Called the instance method, not the prototype method.");

function Person() {};

Person.prototype.dance = function(){};

function Ninja3(){};

Ninja3.prototype = {dance: Person.prototype.dance};

var ninja3 = new Ninja3();
assert(ninja3 instanceof Ninja3, "ninja3 receives functionality from Ninja3");
assert(ninja3 instanceof Person, "also Person");
assert(ninja3 instanceof Object, "and Object");

Object.prototype.keys = function()  {
    var keys = [];
    for (var p in this) {
        if (this.hasOwnProperty(p))
            keys.push(p);
    }
    return keys;
};

var obj = {a: 1, b: 2, c: 3};
assert(obj.keys().length == 3, "there are 3 properties in the object.");

function User(first, last) {
    this.name = first + " " + last;
    console.log("Name: " + this.name);
}

var name = "Nova Morales";
var user = new User("Darwin", "Morales");
console.log("Name: " + this.name);

function foo() {
    console.log(this.bar);
}

var bar = "bar1";
var o2 = {bar: "bar2", foo: foo};

foo();
o2.foo();

function newUser(){};

newUser.prototype = { first: "darwin",  last: "morales",
        fullName: function() { return this.first + " " + this.last; } };

var newUser1 = new newUser();

console.log (newUser1.fullName());

assert({} == {}, "Yes, they are not the same.");
assert({}.toString() === {}.toString(), "Yes, they are the same.");

var dog = {
    bark: function(count) {}
};

//dog.bark.call(cat, 1);
//dog.bark.apply(cat, [1]);

var foo1 = "bar1"; //global scope

function bar() {
    var foo1 = "baz"; //local scope under bar()
}

function baz(foo1) {
    foo1 = "bam"; // local scope under baz (paramter)
    bam = "yay"; // if not in strict mode it would create it
}









