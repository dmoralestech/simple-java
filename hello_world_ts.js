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
var myFunc = function (h, w) { return h * w; };
var helloWorld = function (name) {
    console.log('Hello ' + (name || 'unknown person'));
};
var squareIt;
squareIt = function (rect) {
    if (rect.w === undefined) {
        return rect.h * rect.h;
    }
    return rect.w * rect.h;
};
var rectA = { h: 7 };
var rectB = { h: 7, w: 10 };
console.log(squareIt(rectA));
console.log(squareIt(rectB));
var demoInterface;
(function (demoInterface) {
    var squareItBasic = function (num) { return num * num; };
    var squareIt; //OLD WAY: var squareIt: (rect: {h: number, w?: number}) => number;
    var p = {
        //favoriteMovie: 'NBA Jordan', // This should work
        name: 'Darwin',
        age: 33,
        kids: 2,
        calcPets: function () {
            return this.kids * 2;
        },
        makeYounger: function (years) {
            this.age -= years;
        },
        greet: function (msg) {
            return msg + ',' + this.name;
        }
    };
    function getPerson() {
        var _this = this;
        return {
            name: 'Darwin',
            age: 2,
            kids: 2,
            calcPets: function () { return 1; },
            makeYounger: function (years) {
                _this.age -= years;
            },
            greet: function (msg) {
                return msg + ',' + _this.name;
            }
        };
    }
    function sessionEvaluator() {
        var ratings = [];
        var addRating = function (rating) {
            if (rating === void 0) { rating = 5; }
            ratings.push(rating);
        };
        var calcRating = function () {
            var sum = 0;
            ratings.forEach(function (score) {
                sum += score;
            });
            return sum / ratings.length;
        };
        return {
            addRating: addRating,
            calcRating: calcRating
        };
    }
})(demoInterface || (demoInterface = {}));
var demoClasses;
(function (demoClasses) {
    var Car = (function () {
        function Car(engine) {
            this.engine = engine;
        }
        return Car;
    })();
    var Car2 = (function () {
        function Car2(engine) {
            this.engine = engine;
        }
        Car2.prototype.start = function () {
            return "Started " + this._engine;
        };
        Car2.prototype.stop = function () {
            return "Stopped " + this._engine;
        };
        Object.defineProperty(Car2.prototype, "engine", {
            get: function () {
                return this._engine;
            },
            set: function (value) {
                if (value === undefined)
                    throw 'Supply an engine';
                this._engine = value;
            },
            enumerable: true,
            configurable: true
        });
        return Car2;
    })();
})(demoClasses || (demoClasses = {}));
//# sourceMappingURL=hello_world_ts.js.map