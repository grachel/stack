$( document ).ready(function() {

    autosize(document.querySelectorAll('textarea'));

    $(".commBtn").on("click", function (e) {
        var id = e.target.parentElement.parentElement.id;
        var text = $("#" + id).find("textarea").val()
        $.ajax({
            type: "POST",
            url: "/comment",
            dataType: "json",
            data: {postid: id.replace("pos", ""), body: text},
            success: function(comment) {
                var id = "#pos" + comment.postid;
                $(id).find("tbody").append(
                    "<tr><td><a id=\"com"
                    + comment.id
                    + "\" class=\"voteup glyphicon glyphicon-chevron-up\">0</a></td><td>"
                    + comment.body
                    + " - "
                    + comment.user
                    + ", on "
                    + comment.date
                    + "</td></tr>");
            }
        });
    });

    $(".voteup").click(function (e) {
        var com_id = e.target.id;
        $.ajax({
            type: "POST",
            url: "/score",
            dataType: "json",
            data: {id: com_id.replace('com', '')},
            success: function (comment) {
                var id = "#com" + comment.id;
                $(id).text(comment.score);
            }
        });
    });
});