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
//# sourceMappingURL=hello_world_ts.js.map