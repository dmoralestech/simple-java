/**
 * Created by darwinmorales on 22/12/2015.
 */
var demo = angular.module( "app", [] );

demo.controller(
    "MyCtrl",
    function( $scope ) {
        var m = {
            "India": "2",
            "England": "2",
            "Brazil": "3",
            "UK": "1",
            "USA": "3",
            "Syria": "2"
        };
        $scope.items = m;

    }
);
