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

module demoPrimitives {
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

module demoObjects {
    var points1 = {x: 10, y: 15};
    var coordX: number = points1.x;

    var rectangle = {
        h: 10,
        w: 20,
        calcArea: function() {
            return this.h * this.w;
        }
    };

    var squareIt = function(rect: {h: number; w?: number}) {
        if (rect.w === undefined) {
            return rect.h * rect.h;
        }
        return rect.h * rect.w;
    }
    var sq1: number = squareIt({h: 10});
    console.log('area of sq1 ', sq1);

}


var myFunc = (h: number, w: number) => h * w;

var helloWorld:(name?:string) => void = function(name?) {
    console.log('Hello ' + (name || 'unknown person'));}



var squareIt: (rect: {h: number, w?: number}) => number;

squareIt = function(rect) {
    if (rect.w === undefined) {
        return rect.h * rect.h;
    }
    return rect.w * rect.h;
}

var rectA = {h: 7};
var rectB = {h: 7, w: 10};

console.log(squareIt(rectA));
console.log(squareIt(rectB));

module demoInterface {
    interface squareFunction {
        (x: number): number;
    }

    var squareItBasic: squareFunction = (num) => num * num;

    interface Rectangle {
        h: number;
        w?: number;
    }

    var squareIt: (rect: Rectangle) => number; //OLD WAY: var squareIt: (rect: {h: number, w?: number}) => number;

    interface Person {
        name: string;
        age?: number;
        kids: number;
        calcPets: () => number;
        makeYounger: (years: number) => void;
        greet: (msg: string) => string;
    }

    var p: Person = {
        //favoriteMovie: 'NBA Jordan', // This should work
        name: 'Darwin',
        age: 33,
        kids: 2,
        calcPets: function() {
            return this.kids * 2;
        },
        makeYounger: function(years: number) {
            this.age -= years;
        },
        greet: function (msg: string) {
            return msg + ',' + this.name;
        }
    };

    function getPerson(): Person {
        return {
            name: 'Darwin',
            age: 2,
            kids: 2,
            calcPets: () => {return 1},
            makeYounger: (years: number) => {
                this.age -= years;
            },
            greet: (msg: string) => {
                return msg + ',' + this.name;
            }
        }
    }

    interface SessionEval {
        addRating: (rating: number) => void;
        calcRating: () => number
    }

    function sessionEvaluator(): SessionEval {
        var ratings: number[] = [];
        var addRating = (rating: number =5) => {
            ratings.push(rating);
        }
        var calcRating = ()  => {
            var sum: number = 0;
            ratings.forEach(function(score) {
                sum += score;
            });
            return sum/ratings.length;
        };
        return {
            addRating: addRating,
            calcRating: calcRating
        }

    }


}

module demoClasses {
    class Car {
        engine: string;

        constructor(engine: string) {
            this.engine = engine;
        }

        //constructor(public engine: string) {
        //    this.engine = engine;
        //}


    }

    //class Car2 {
    //    private _engine: string;
    //
    //    constructor(engine: string) {
    //        this.engine = engine;
    //    }
    //
    //    start() {
    //        return "Started " + this._engine;
    //    }
    //
    //    stop() {
    //        return "Stopped " + this._engine;
    //    }
    //
    //    get engine(): string {
    //        return this._engine;
    //    }
    //
    //    set engine(value: string) {
    //        if (value === undefined) throw 'Supply an engine'
    //        this._engine = value;
    //    }
    //}

    class Engine  {
        constructor(public horsePower: number, public engineType: string) {}
    }

    class Car3 {
        private _engine: Engine;

        constructor(engine: Engine) {
            this._engine = engine;
        }




    }

}
