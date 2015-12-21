/**
 * Created by darwinmorales on 20/12/2015.
 */


var foo = "bar";

function bar() {
    var foo = "baz";

    function baz(foo) {
        foo = "bam";
        bam = "yay";
    }
    baz();
}

bar();
console.log(foo);
console.log(bam);
//baz();


function foo2(str) {
    eval(str);
    console.log(bar);
}

foo2("var bar = 42;");


var Person = {
  firstName: "Darwin",
    lastName: "Morales",
    birthDate: new Date('1955-9-23'),
    gender: "male",
    getAge : function () {
        var today = new Date();
        var diff = today.getTime() - this.birthDate.getTime();
        var year = 1000 * 60 * 60 * 24 * 365.25;
        return Math.floor( diff / year );
    },
    extend: function(config) {
        var tmp = Object.create(this);
        for (var key in config) {
            if (config.hasOwnProperty(key)) {
                tmp[key] = config[key];
            }
        }
        return tmp;
    }
};

var bob = Object.create(Person);
console.log(bob.firstName);
console.log(bob.lastName);

var bob2 = Person.extend( {firstName: "bob", lastName: "Sacamanto"} );

console.log( bob2.__proto__ == Person) ;