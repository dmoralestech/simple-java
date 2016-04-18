/**
 * Created by dmorales on 18/04/2016.
 */
function Identity(value) {
    this.value = value;
}
Identity.prototype.bind = function (transform) {
    return transform(this.value);
};
Identity.prototype.toString = function () {
    return 'Identity(' + this.value + ')';
};
var result = new Identity(5).bind(function (value) {
    return new Identity(6).bind(function (value2) {
        return new Identity(value + value2);
    });
});
console.log(result);
//# sourceMappingURL=js_mon.js.map