class HelloWorld {
    constructor(public message: string) {}
}

var hello = new HelloWorld('Hello Typescript');
console.log(hello.message);


const add1 = (a) => a + 1;
const times2 = (a) => a * 2;
const compose = (a, b) => (c) => a(b(c));
const add10Times2 = compose(add1, times2);
console.log(add10Times2(5));

var startTimer = function() {

};

var pauseTimer = function() {

};

var clearTimer = function() {

};

var init: (s: string, p: string, c: string) => void = function (startButton, pauseButton, clearButton) {
    document.getElementById(startButton).addEventListener("click", startTimer, false);
    document.getElementById(pauseButton).addEventListener("click", pauseTimer, false);
    document.getElementById(clearButton).addEventListener("click", clearTimer, false);
};

init('startButton', 'pauseButton', 'clearButton');

module demo {
    var person: string;
    person = 'Darwin Morales';
    console.log(person);

    var person2 = {
        name: 'Darwin',
        age: 22
    }
    console.log(person2.name.substring(1, 4));
}

var names: string[] = ['Darwin', 'Nova', 'Daniel', 'Sitti'];

module demo1 {
    var data: any;
    var doSomething: (args: any) => void = function(args) {
        return args;
    };

    var age: number = 32;
    var price: number = 1.99;
    var hasData: boolean = true;
    var hasMoney: boolean = true;

    var birthDate: Date = null;




}