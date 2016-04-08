/**
 * Created by dmorales on 8/04/2016.
 */

function test() {
    "use strict";
    //http://www.breck-mckye.com/blog/2016/04/monads-explained-quickly/
    function Foo(value) {
//        this.get = () => value;
        this.get = function() {
            return value;
        }
//        this.map = fn => {
//            let result = fn(value);
//            return new Foo(result);
//        };
        this.map = function(fn) {
            let result = fn(value);
            return new Foo(result);
        };
    }

    function Foo2(value){
        this.get = function() {
            return value;
        }
    }

    var foo1 = new Foo2("Darwin");
    console.log(foo1.get());
    let one = new Foo(1);
    let two = one.map(x => x + 7)
        .map(x => x / 2)
        .map(x => x - 2);
    console.log(two.get() === 2);


}
