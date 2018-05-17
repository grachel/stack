$( document ).ready(function() {
    autosize(document.querySelectorAll('textarea'));

     $(".vote").click(function (e) {
        var element = $(this);
         $.ajax({
             type: "POST",
             url: "/" + element.attr('data-type') + "/vote",
             dataType: "json",
             data: {id: element.attr('id') },
             success: function (response) {
                element.text(response);
             }
         });
     });
});



