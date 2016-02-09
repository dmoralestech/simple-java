/**
 * Created by darwinmorales on 22/12/2015.
 */
var demo = angular.module("app", []);

demo.controller(
    "MyCtrl",
    function ($scope, $q, $timeout) {

        $scope.wines = [
            { name: "Wine A", isNew: true },
            { name: "Wine B", isNew: true },
            { name: "wine C", isNew: false },
            { name: "Wine D", isNew: true },
            { name: "Wine E", isNew: true },
            { name: "wine F", isNew: false },
            { name: "wine G", isNew: false},
            { name: "wine H", isNew: true }
        ];
        $scope.filter = {true: false, something: ''};

        $scope.filterByCategory = function (wine) { //this acts like a predicate function
            console.log("filterByCategory: " + wine.name + " - " + $scope.filter[wine.isNew]);
            console.log("$scope.filter: " + $scope.filter);
            return $scope.filter[wine.isNew] || noFilter($scope.filter);
        };

        $scope.promiseSample = function () {
            var defer = $q.defer();

            defer.promise
                .then(function (weapon) {
                    console.log(weapon);
                    return "bow";
                })
                .then(function (weapon) {
                    console.log(weapon);
                    return "axe";
                })
                .then(function (weapon) {
                    console.log(weapon);
                })

            console.log("1");

            $timeout(function () {
                console.log("3");
                defer.resolve("sword");
                console.log("4");
            }, 2000);

            console.log("2");


        };

        function noFilter(filterObj) {
            console.log(filterObj);
            for (var key in filterObj) {
                console.log("key: " + key);
                if (filterObj[key]) {
                    console.log("filterObj[key]: " + filterObj[key]);
                    return false;
                }
            }
            return true;
        }

        $scope.a = {a: "a", b: "b", c: "c", d: "d", myRating: ""};

        $scope.mutlipleButtons = ["Button 1", "Button 2", "Button 3", "Button 4", "Button 5"];

        $scope.objectYes = {value: "yes"};
        $scope.objectNo = {value: "no"};

        var optionResolver1 = {
            varCodes: ["123", "456", "789"],
            varCodesDesc: "Loren Ipsum blah",
            option1: "123",
            option2: "789",
            isValid: undefined
        };

        var optionResolver2 = {
            varCodes: ["ABC", "DEF", "GHI", "JKL", "MNL", "OPQ"],
            varCodesDesc: "Loren Ipsum blah",
            option1: "ABC",
            option2: "GHI",
            isValid: undefined
        };

        var optionResolver3 = {
            varCodes: ["RST", "UVW"],
            varCodesDesc: "Loren Ipsum blah",
            option1: "RST",
            option2: "UVW",
            isValid: undefined
        };

        $scope.optionResolverList = [optionResolver1, optionResolver2, optionResolver3];

        $scope.optionA = "1234";
        $scope.optionB = "5678";

        var option1 = {
            option1: "A123",
            option2: "B123",
            isTheSame: false,
            defaultOption: "",
            validationDate: "",
            id: "o11",
            id2: "c21"
        };
        var option2 = {
            option1: "C456",
            option2: "D456",
            isTheSame: true,
            defaultOption: "",
            validationDate: "",
            id: "o12",
            id2: "c22"
        };
        var option3 = {
            option1: "E789",
            option2: "F789",
            isTheSame: false,
            defaultOption: "",
            validationDate: "",
            id: "o13",
            id2: "c23"
        };
        $scope.optionList = [option1, option2, option3];
        $scope.isVisible = false;
        $scope.col1 = "Option 1 col";
        $scope.radioButtonValue = "A";

    }
)
;
