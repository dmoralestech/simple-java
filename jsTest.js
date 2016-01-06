/**
 * Created by dmorales on 9/12/2015.
 */

function PersonObj2(name) {
    Object.defineProperty(this, "name", {
        get: function() {
            return name;
        },
        set: function(value) {
            name = value;
        },
        enumerable: true,
        configurable: true
    });
//test comment
    this.sayName = function() {
        console.log('Hello ' + this.name);
    }

}

PersonObj2.prototype.favorites = [];
PersonObj2.prototype.counter = 0;

PersonObj2.prototype.addOneToCounter = function() {
    PersonObj2.prototype.counter += 1;
}

var personObj2 = new PersonObj2("Nova");
var personObj3 = new PersonObj2("Daniel");

personObj2.favorites.push("pizza");
personObj3.favorites.push("pasta");
personObj2.favorites.push("steak");

personObj2.addOneToCounter();
personObj3.addOneToCounter();
personObj3.addOneToCounter();
personObj3.addOneToCounter();
personObj2.addOneToCounter();

console.log(personObj2.counter);
console.log(personObj3.counter);

console.log(personObj2.name);
personObj2.sayName();

console.log(personObj2.favorites); //outputs 5
console.log(personObj3.favorites); //outputs 5

personObj2.name = 'Sitti';
console.log(personObj2.name);

var PersonObj = {
    firstName: 'Darwin',
    lastName: 'Morales',
    birthDate: new Date('1900-01-01'),
    gender: 'male',
    getAge: function() {
        var today = new Date();
        var diff = today.getTime() - this.birthDate.getTime();
        var year = 1000 * 60 * 60 * 24 * 365.25;
        return Math.floor(diff / year);
    }
};


console.log(PersonObj['firstName']);
console.log(PersonObj['getAge']());

var bob = Object.create(PersonObj);
bob.firstName = 'Bob';
bob.lastName = 'Sacamato';
bob.birthDate = new Date('1990-8-9');

console.log(bob.firstName);
console.log(bob.lastName);
console.log(bob.getAge());


var sayHi = new Function("console.log('Hi')");
sayHi();

var reflect = function() { //you can create a function with empty paramaters but you can still capture it inside the function
    return arguments[1];
}

console.log(reflect("Hi"));
console.log(reflect("Hi", "Hello", "good"));
console.log(reflect.length);

var sayMessage = new Function("message", "console.log('message');");
sayMessage('Hello world');

var person = {
    name: "Daniel",
    sayName: function() {
        console.log(person.name); // This is bad because it makes a tight coupling between the method and the object.. "this.name" is better.
    }
};

var person2 = {
    name: "Darwin",
    sayName: function() {
        console.log(this.name); // This is bad because it makes a tight coupling between the method and the object.. "this.name" is better.
    }
};

person2.sayName()

function sayNameForAll() {
    console.log(this.name);
}

for (prop in person2) {
    console.log(prop);
    console.log(person2[prop]);
}

var properties = Object.keys(person2);

for (prop in properties) {
    console.log(prop);
    console.log(properties[prop]);
    console.log(person2[properties[prop]]);
}

console.log(person.propertyIsEnumerable('name'));
console.log(Object.keys(person).length);

Object.defineProperty(person, 'name', {enumerable:false});

console.log(person.propertyIsEnumerable('name'));
console.log(Object.keys(person).length);

var person2 = {
    _name: "",
    _age: 0,
    _address: "",

    get name() {
        return this._name;
    },

    set name(value) {
        this._name = value;
    },

    get age() {
        return this._age;
    },

    set age(value) {
        this._age = value;
    },

    get address() {
        return this._address;
    },

    set address(value) {
        this._address = value;
    }

};

person2.name = "Darwin";
person2.age = 20;
person2.address = "Manila";

console.log(person2.name);
console.log(person2.age);
console.log(person2.address);


var person4 = {
    _name: "Darwin"
};

Object.defineProperty(person4, "name", {
    get: function() {
        console.log("Reading name");
        return this._name;
    },
    set: function(value) {
        this._name = value;
    },
    enumerable: true,
    configurable: true
})

var descriptor = Object.getOwnPropertyDescriptor(person4, "name");
console.log(descriptor.propertyIsEnumerable);
console.log(descriptor.configurable);
console.log(descriptor.writable);
console.log(descriptor.value);

Object.preventExtensions(person4);
console.log(Object.isExtensible(person4));

//
//person4.sayName() = function () {
//    console.log(this._name);
//};

console.log("sayName" in person4);

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









