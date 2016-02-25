function Note() {
}
Note.annotations = [
  new angular.ComponentAnnotation({
    selector: "note"
  }),
  new angular.ViewAnnotation({
    template: "<div>Simple message</div>"
  })
];

function Egghead() {
}
Egghead.annotations = [
  new angular.ComponentAnnotation({
    selector: "egghead"
  }),
  new angular.ViewAnnotation({
    directives: [Note],
    template: "<div><h1>Hello everyone</h1><note></note></div>"
  })
];

document.addEventListener("DOMContentLoaded", function () {
  angular.bootstrap(Egghead);
});
