$( document ).ready(function() {
    autosize(document.querySelectorAll('textarea'));

     $(".postUp").click(function (e) {
        var element = $(this);
         $.ajax({
             type: "POST",
             url: "/post/vote",
             dataType: "json",
             data: {id: e.target.id },
             success: function (post) {
                element.text(post.score);
             }
         });
     });

    $(".commUp").click(function (e) {
        var element = $(this);
        $.ajax({
            type: "POST",
            url: "/comment/vote",
            dataType: "json",
            data: {id: e.target.id },
            success: function (comment) {
                element.text(comment.score);
            }
        });
    });

    $(".answUp").click(function (e) {
        var element = $(this);
        $.ajax({
            type: "POST",
            url: "/answer/vote",
            dataType: "json",
            data: {id: e.target.id },
            success: function (answer) {
                element.text(answer.score);
            }
        });
    });
});



