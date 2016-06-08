$(function () {
    var text = $('.to-type').html();
    $('.to-type').html('');
    $(".typed").typed({
        strings: [
            text
        ],
        typeSpeed: 0,
        showCursor: true,
        cursorChar: "|",
        loop: false,
        contentType: 'html',
        callback: function () {
            $('a.email').removeClass('opa');
        }
    });
});
