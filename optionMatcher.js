/**
 * Created by darwinmorales on 22/12/2015.
 */
var demo = angular.module("app", []);

demo.controller(
    "MyCtrl",
    function ($scope) {

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
            defaultOption: "D",
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
        $scope.radioButtonValue = $scope.optionList[1];

    }


)
;
