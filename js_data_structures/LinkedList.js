/**
 * Created by darwinmorales on 16/03/2016.
 */



//https://github.com/Bishop92/JavaScript-Data-Structures/blob/master/lib/LinkedList/LinkedList.js

function LLNode(item) {
    this.item = item;
    this.next = null;
}

function Aggregrate()  {

}

Aggregrate.prototype.getIterator = function() {

};

LinkedList.prototype = new Aggregrate();
LinkedList.prototype.constructor = LinkedList;

function LinkedList(args) {
    this.first = null;
    this.last = null;
    this.length = null;
    if (args && args.length) {
        this.fromArray(args);
    } else {
        this.fromArray(arguments);
    }
}

LinkedList.prototype.getIterator = function() {
    return new LinkeListIterator(this);
}