$( document ).ready(function() {

    autosize(document.querySelectorAll('textarea'));

    $(document).on("click", ".commBtn", function (e) {
        var id = e.target.parentElement.parentElement.parentElement.id;
        var text = $("#" + id).find(".comments").first().find("textarea").val()
        $.ajax({
            type: "POST",
            url: "/comment",
            dataType: "json",
            data: {postid: id, body: text},
            success: function(comment) {
                var id = "#" + comment.postid;
                $(id).find("tbody").first().append(
                    "<tr><td><a id=\"com"
                    + comment.id
                    + "\" class=\"vote commUp glyphicon glyphicon-chevron-up\">0</a></td><td>"
                    + comment.body
                    + " - "
                    + comment.user
                    + ", on "
                    + comment.date
                    + "</td></tr>");
                $(id).find(".comments").first().find("textarea").val("");
            }
        });
    });

    $(".commUp").click(function (e) {
        var com_id = e.target.id;
        $.ajax({
            type: "POST",
            url: "/comment/score",
            dataType: "json",
            data: {id: com_id.replace('com', '')},
            success: function (comment) {
                var id = "#com" + comment.id;
                $(id).text(comment.score);
            }
        });
    });

    $(".postUp").click(function (e) {
        var pos_id = e.target.parentElement.parentElement.id;
        scoreUp(pos_id)
    });

    $(".answUp").click(function (e) {
        var pos_id = e.target.parentElement.id;
        scoreUp(pos_id)
    });

    function scoreUp(postid) {
        $.ajax({
            type: "POST",
            url: "/post/score",
            dataType: "json",
            data: {id: postid },
            success: function (post) {
                var id = "#" + post.id;
                $(id).find('.vote').first().text(post.score);
            }
        });
    }



    $(".postBtn").on("click", function (e) {
        var id = e.target.parentElement.parentElement.parentElement.id;
        var text = $("#subAnswer").find("textarea").val()
        $.ajax({
            type: "POST",
            url: "/post/answer",
            dataType: "json",
            data: {postid: id.replace("pos", ""), body: text},
            success: function(answer) {
                $("#answers").append("<div class=\"post\" id=\""
                    + answer.id
                    + "\"><a class=\"vote answUp glyphicon glyphicon-chevron-up\">0</a> <textarea class=\"post\" readonly>"
                    + answer.body
                    + "</textarea><span class=\"datetime\">Posted by "
                    + answer.user
                    + ", "
                    + answer.date
                    +"</span><div class=\"comments\"><table><tbody class=\"tcomm\"></tbody></table><h4>Leave a Comment:</h4><form role=\"form\"><div class=\"form-group\"><textarea class=\"form-control\" rows=\"3\" required></textarea></div><button type=\"button\" class=\"commBtn btn btn-success\">Submit</button></form></div></div>");
                $("#subAnswer").find("textarea").val("")
            }
        });
    });
});



