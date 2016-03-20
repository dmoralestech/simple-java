var app = angular.module("twitterApp", []);

app.controller("AppCtrl", function() {
    var appCtrl = this;
    appCtrl.loadMoreTweets = function() {
        alert("Loading tweets!");
    };

    appCtrl.deleteTweets = function() {
        alert("deleting tweets");
    };
});

app.directive("enter", function() {
    return function(scope, element, attrs) {
        element.bind("mouseenter", function() {
            scope.$apply(attrs.enter);
        });
    };
});


var app2 = angular.module("superhero", []);

app2.directive("superhero", function() {
   return {
       restrict: "E",
       template: "<div> Here I am to save the day </div>"
   } ;
});

