function AnotherNote() {
}
AnotherNote.annotations = [
  new angular.ComponentAnnotation({
    selector: "anotherNote"
  }),
  new angular.ViewAnnotation({
    template: "<div>Darwin's message</div>"
  })
];



function Note() {
}
Note.annotations = [
  new angular.ComponentAnnotation({
    selector: "note"
  }),
  new angular.ViewAnnotation({
    template: "<div> Simple message <button type='button'>Click Me!</button></div>"
  })
];

function Egghead() {
}
Egghead.annotations = [
  new angular.ComponentAnnotation({
    selector: "egghead"
  }),
  new angular.ViewAnnotation({
    directives: [Note,AnotherNote],
    template: "<div><h1>Hello everyone</h1><note></note> <anotherNote></anotherNote></div>"
  })
];

document.addEventListener("DOMContentLoaded", function () {
  angular.bootstrap(Egghead);
});
