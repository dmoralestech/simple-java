/**
 * Created by dmorales on 18/03/2016.
 */


var app = angular.module("choreApp", []);

app.controller("ChoreCtrl", function() {
    var choreCtrl = this;
    choreCtrl.myTask = "Clean the car";
    choreCtrl.logChore = function(chore) {
        alert(chore + " is done!");
    };
    return this;
});

app.directive("kid", function() {
    return {
        restrict: "E",
        scope: {
            done: "&"
        },
        template: '<input type="text" ng-model="chore">' +
        ' {{chore}}' +
        ' <div class="button" ng-click="done({chore:chore})">I\'m done!</div>'
    };
});

app.directive("task", function() {
    return {
        restrict: "E",
        scope: {
            finished: "&"
        },
        template: '<h2> {{myTask}}} </h2>'
    }
});