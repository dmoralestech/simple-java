/**
 * Created by darwinmorales on 22/12/2015.
 */
var demo = angular.module( "app", [] );

demo.controller(
    "MyCtrl",
    function( $scope ) {


        var option1 = {option1: "A", option2: "B", isTheSame:false, defaultOption: "", validationDate: "", id: "o11", id2: "c21"};
        var option2 = {option1: "C", option2: "D", isTheSame:true, defaultOption: "D", validationDate: "", id: "o12", id2: "c22"};
        var option3 = {option1: "E", option2: "F", isTheSame:false, defaultOption: "", validationDate: "", id: "o13", id2: "c23"};
        $scope.optionList = [option1, option2, option3];
        $scope.isVisible = false;
        $scope.col1 = "Option 1 col";

    }
);
