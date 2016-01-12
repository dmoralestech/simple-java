/**
 * Created by dmorales on 12/01/2016.
 */


Stack = function() {
    var stack = {}, list = {};

    stack.push = function(elem) {

    };

    stack.add = function(elem) {

    };

    stack.pop = function() {

    };

    stack.peek = function() {
        return list.first();
    };

    stack.size = function() {
        return list.size();
    };

    stack.contains = function (elem, equalsFunction) {

    };

    stack.isEmpty = function () {
        return list.isEmpty();
    };

    stack.clear = function () {
        list.clear();
    };

    stack.forEach = function(callback) {
        list.forEach(callBack);
    };

    stack.toArray = function() {
        return list.toArray();
    };

    stack.equals = function(other, equalsFunction) {

    };

    return stack;

};

var myStack = new Stack();
myStack.add('a');
myStack.add('b');