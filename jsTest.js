/**
 * Created by dmorales on 9/12/2015.
 */

function doSomething(callback) {
    var data = 0;
    callback(data);
}

function domSomethingAgain(callback) {
    doSomething( function (data) {
        callback(data);
    });
}

function callBackTest() {
    domSomethingAgain(function functionName(data) {
        console.log(data);
    });
}


callBackTest();

var obj1 = {status: 'sad'};

if (!obj1.name) {
    obj1.name = 'darwin';
}

if (!obj1.address) {
    obj1.address = 'oz';
}

if (!obj1.status) {
    obj1.status = 'happy';
}


console.log(obj1.name); //darwin
console.log(obj1.address); //oz
console.log(obj1.status); //sad

function sandwichMaker(ingredient) {
    return function (filling) {
        return ingredient + " and " + filling;
    };
}

var hamAnd = sandwichMaker("ham");
console.log(hamAnd("cheese"));
console.log(hamAnd("bacon"));

var burgerAnd = sandwichMaker("burger");
console.log(burgerAnd("lettuce"));
console.log(burgerAnd("egg"));


console.log(new Object() === new Object()); // false. this creates 2 unique objects.
console.log((new Object()).constructor === (new Object()).constructor); // true
console.log((new Object()).__proto__ === (new Object()).__proto__); // true

function EventTarget() {
}

EventTarget.prototype = {
    constuctor: EventTarget,
    addListener: function (type, listener) {
        if (!this.hasOwnProperty("_listeners")) {
            this._listeners = [];
        }

        if (typeof this._listeners[type] == 'undefined') {
            this._listeners[type] = [];
        }

        this._listeners[type].push(listener);

    },
    fire: function (event) {
        if (!event.target) {
            event.target = this;
        }

        if (!event.type) {
            throw new Error("Event object missing 'type' property");
        }

        if (this._listeners && this._listeners[event.type] instanceof Array) {
            var listeners = tis._listeners[event.type];
            for (var i = 0, len = listeners.length; i < len; i++) {
                listeners[i].call(this, event);
            }
        }
    },
    removeListener: function (type, listener) {

    }
};

function PersonE(name) {
    this.name = name;
}

PersonE.prototype = Object.create(EventTarget.prototype);
PersonE.prototype.constructor = PersonE;

PersonE.prototype.sayName = function () {
    console.log(this.sayName);
    this.fire({type: "namesaid", name: name});
};

var personE = new PersonE("Darwin");

console.log(personE instanceof PersonE); //true
console.log(personE instanceof EventTarget); //true

function mixin(receiver, supplier) {
    for (var property in supplier) {
        if (supplier.hasOwnProperty(property)) {
            receiver[property] = supplier[property];
        }
    }

    return receiver;
}

function PersonF(name) {
    this.name = name;
}
mixin(PersonF.prototype, new EventTarget());
mixin(PersonF.prototype, {
    constructor: PersonF,
    sayName: function () {
        console.log(this.name);
        this.fire({type: "namesaid", name: name});
    }
});

var personF = new PersonF("nova");
console.log(personF instanceof PersonF); //true
console.log(personF instanceof EventTarget); //false

for (prp in personE) {
    console.log(prp);
}


function People() {
    var i = 1;
    console.log('i = ' + ++i);
}
var people1 = new People();
var people2 = new People();

console.log(people1.constructor === People); //true
console.log(people1.constructor === people2.constructor); //true
console.log(people1 instanceof People); //true

var peopleConsturctor = people1.constructor;
peopleConsturctor();

var objectTemp = {
    value1: 2,
    value2: 5
};

console.log(objectTemp.constructor === Object); // for all Object literal this is tru

function myConstructor() {
}

myConstructor.prototype = Object.create(Object.prototype, {
    constructor: {
        configurable: true,
        enumerable: true,
        value: myConstructor,
        writable: true
    }
});

function Rectangle(width, length) {
    this.length = length;
    this.width = width;
}

Rectangle.prototype.getArea = function () {
    return this.length * this.width;
}

Rectangle.prototype.toString = function () {
    return "[Rectangle: " + this.length + " x " + this.width + "]";
}


function Square(size) {
    this.length = size;
    this.width = size;
}

console.log(Square.prototype); // empty object
Square.prototype = new Rectangle();
console.log(Square.prototype);

console.log(Square.prototype.constructor === Rectangle.prototype.constructor); //true
console.log(Square.prototype === Rectangle.prototype); //false
console.log(Square.prototype == new Rectangle()); //false.. because this creates another new Rectangle object?

Square.prototype = Rectangle.prototype;
console.log(Square.prototype === Rectangle.prototype); //true
console.log(Square.prototype);

Square.prototype.constructor = Square; //not sure why Rectangle.prototype = Square after this??

Square.prototype.toString = function () {
    return "[Square: " + this.length + " x " + this.width + "]";
}

function CustomShape(size) {
    Rectangle.call(this, size, size);
}

CustomShape.prototype = Object.create(Rectangle.prototype, {
        constructor: {
            configurable: true,
            enumerable: true,
            value: CustomShape,
            writable: true
        }
    }
);

console.log(CustomShape.prototype.toString());

square2 = new Square2(7);

console.log(square2.length);
console.log(square2.width);
console.log(square2.getArea());

var rect = new Rectangle(5, 10);
var square = new Square(7);

console.log(rect.getArea());
console.log(square.getArea());

console.log(rect.toString());
console.log(square.toString());

console.log(rect instanceof Rectangle);
console.log(rect instanceof Object);

console.log(square instanceof Square);
console.log(square instanceof Rectangle);
console.log(square instanceof Object);

var personA = {
    name: "darwin",
    sayName: function () {
        console.log(this.name);
    },
    sayWhat: function () {
        var what = "what " + this.name;
        console.log(what);
        // var what = "what " + this.name; //won't work if it's declared here..
    }
};

var personB = Object.create(personA, {
    name: {
        value: "nova"
    }
});

personA.sayWhat();
personB.sayWhat();

personA.sayName();
personB.sayName();
console.log(personB.__proto__ === personA); //true
console.log(personA.__proto__ == Object.prototype); //true
console.log(personA.__proto__ == personB.__proto__); //false


console.log(personA.hasOwnProperty("sayName")); //true
console.log(personB.hasOwnProperty("sayName")); //false
console.log(personA.isPrototypeOf(personB)); //true
console.log(Object.isPrototypeOf(personA)); //false
console.log(Object.isPrototypeOf(personB)); //false

console.log(personB.prototype.sayName());

console.log(personB.prototype); // undefined  because personB is not a function.
//Functions in JavaScript are first-class objects, which means you can add members to them and treat them just like ordinary objects:

//personB.prototype.sayHello = function() { //Error! you can't set a function to an undefined object
//    console.log('Hello ' + this.name);
//}

String.prototype.capitalize = function () {
    return this.charAt(0).toUpperCase() + this.substring(1);
};

console.log("darwin morales".capitalize());
console.log("darwin morales".toString() === "darwin morales".valueOf()); //true
console.log("darwin morales".valueOf());
console.log("darwin morales".hasOwnProperty("capitalize")); //false

var book = {
    title: "The Bible", sayHi: function () {
    }
};

console.log("title" in book); //true
console.log(book.hasOwnProperty("title")); //true
console.log("hasOwnProperty" in book); //true
console.log(book.hasOwnProperty("hasOwnProperty")); //false
console.log(Object.prototype.hasOwnProperty("hasOwnProperty")); //true
console.log(book.isPrototypeOf(book.prototype)); //true
console.log(book.isPrototypeOf(Object.prototype)); //false

function hasPrototypeProperty(object, name) {
    return name in object && !object.hasOwnProperty(name);
}

var object = {}; //creates an empty object but there's a hidden property [[Prototype]] that points to the Object.prototype object
var prototype = Object.getPrototypeOf(object);
console.log(prototype === Object.prototype); // true
console.log(object.__proto__ === Object.prototype); //true

for (var prop in Object) { //no output
    console.log(prop);
}

console.log(Object.keys(Object));


function PersonObj2(name) {
    Object.defineProperty(this, "name", {
        get: function () {
            return name;
        },
        set: function (value) {
            name = value;
        },
        enumerable: true,
        configurable: true
    });
    this.sayName = function () {
        console.log('Hello ' + this.name);
    }

}
//test comment
PersonObj2.prototype.favorites = [];
PersonObj2.prototype.counter = 0;

PersonObj2.prototype.addOneToCounter = function () {
    PersonObj2.prototype.counter += 1;
}

console.log(PersonObj2.prototype.__proto__ === Object.prototype); // if my understanding is correct then this should be true!
// the prototype property of PersonObj2.prototype should point to Object.prototype

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
    getAge: function () {
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

var reflect = function () { //you can create a function with empty paramaters but you can still capture it inside the function
    return arguments[1];
}

console.log(reflect("Hi"));
console.log(reflect("Hi", "Hello", "good"));
console.log(reflect.length);

var sayMessage = new Function("message", "console.log('message');");
sayMessage('Hello world');

var person = {
    name: "Daniel",
    sayName: function () {
        console.log(person.name); // This is bad because it makes a tight coupling between the method and the object.. "this.name" is better.
    }
};

var person2 = {
    name: "Darwin",
    sayName: function () {
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

Object.defineProperty(person, 'name', {enumerable: false});

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
    get: function () {
        console.log("Reading name");
        return this._name;
    },
    set: function (value) {
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
    sayBye: function () {
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


function assert(value, desc) {
    console.log(value ? "pass: " + desc : "fail: " + desc);
    //var li = document.createElement("li");
    //li.className = value ? "pass" : "fail";
    //li.appendChild(document.createTextNode(desc));
    //document.getElementById("results").appendChild(li);
}


function Ninja() {
}

Ninja.prototype.swingSword = function () {
    return true;
};

var ninja1 = Ninja();
assert(ninja1 === undefined, "No instance of Ninja created.");

var ninja2 = new Ninja();
assert(ninja2 && ninja2.swingSword && ninja2.swingSword());

//assert (typeof ninja == "object", "The type of the instance is object");
//assert (ninja instanceof Ninja, "instanceof identifies the constructor");
//assert (ninja.constructor == Ninja, "The ninja object was created by the Ninja function");

function Ninja2() {
    this.swung = false;
    this.swingSword = function () {
        return !this.swung;
    };
}

Ninja2.prototype.swingSword = function () {
    return this.swung;
};

var ninja = new Ninja2();
assert(ninja.swingSword(), "Called the instance method, not the prototype method.");

function Person() {
};

Person.prototype.dance = function () {
};

function Ninja3() {
};

Ninja3.prototype = {dance: Person.prototype.dance};

var ninja3 = new Ninja3();
assert(ninja3 instanceof Ninja3, "ninja3 receives functionality from Ninja3");
assert(ninja3 instanceof Person, "also Person");
assert(ninja3 instanceof Object, "and Object");

Object.prototype.keys = function () {
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

function newUser() {
};

newUser.prototype = {
    first: "darwin", last: "morales",
    fullName: function () {
        return this.first + " " + this.last;
    }
};

var newUser1 = new newUser();

console.log(newUser1.fullName());

assert({} == {}, "Yes, they are not the same.");
assert({}.toString() === {}.toString(), "Yes, they are the same.");

var dog = {
    bark: function (count) {
    }
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









