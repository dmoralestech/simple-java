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

