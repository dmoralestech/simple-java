var HelloWorld = (function () {
    function HelloWorld(message) {
        this.message = message;
    }
    return HelloWorld;
})();
var hello = new HelloWorld('Hello Typescript');
console.log(hello.message);
var add1 = function (a) { return a + 1; };
var times2 = function (a) { return a * 2; };
var compose = function (a, b) { return function (c) { return a(b(c)); }; };
var add10Times2 = compose(add1, times2);
console.log(add10Times2(5));
var startTimer = function () {
};
var pauseTimer = function () {
};
var clearTimer = function () {
};
var init = function (startButton, pauseButton, clearButton) {
    document.getElementById(startButton).addEventListener("click", startTimer, false);
    document.getElementById(pauseButton).addEventListener("click", pauseTimer, false);
    document.getElementById(clearButton).addEventListener("click", clearTimer, false);
};
init('startButton', 'pauseButton', 'clearButton');
var demo;
(function (demo) {
    var person;
    person = 'Darwin Morales';
    console.log(person);
    var person2 = {
        name: 'Darwin',
        age: 22
    };
    console.log(person2.name.substring(1, 4));
})(demo || (demo = {}));
var names = ['Darwin', 'Nova', 'Daniel', 'Sitti'];
var demoPrimitives;
(function (demoPrimitives) {
    var data;
    var doSomething = function (args) {
        return args;
    };
    var age = 32;
    var price = 1.99;
    var hasData = true;
    var hasMoney = true;
    var birthDate = null;
})(demoPrimitives || (demoPrimitives = {}));
var demoObjects;
(function (demoObjects) {
    var points1 = { x: 10, y: 15 };
    var coordX = points1.x;
    var rectangle = {
        h: 10,
        w: 20,
        calcArea: function () {
            return this.h * this.w;
        }
    };
    var squareIt = function (rect) {
        if (rect.w === undefined) {
            return rect.h * rect.h;
        }
        return rect.h * rect.w;
    };
    var sq1 = squareIt({ h: 10 });
    console.log('area of sq1 ', sq1);
})(demoObjects || (demoObjects = {}));
//# sourceMappingURL=hello_world_ts.js.map