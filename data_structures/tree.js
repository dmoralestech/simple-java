/**
 * Created by darwinmorales on 21/05/2016.
 */

function Node(data, left, right) {
    this.data = data;
    this.left = left;
    this.right = right;
    this.show = show;
}

function show() {
    "use strict";
    return this.data;
}