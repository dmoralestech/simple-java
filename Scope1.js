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

