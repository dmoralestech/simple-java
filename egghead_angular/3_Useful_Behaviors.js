/**
 * Created by dmorales on 21/03/2016.
 */

var app = angular.module("behaviorApp", []);

app.directive("enter", function() {
    return function(scope, element, attrs) {
        element.bind("mouseenter", function() {
            console.log(attrs.enter);
            element.addClass(attrs.enter);
            console.log(attrs.leave);
            element.removeClass(attrs.leave);
        });
    };
});

app.directive("leave", function() {
    return function(scope, element, attrs) {
        element.bind("mouseleave", function() {
            element.addClass(attrs.leave);
            element.removeClass(attrs.enter);

        });
    };
});