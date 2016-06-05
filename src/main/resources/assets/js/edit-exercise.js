

$(document).ready(function(){
    var textareas = document.getElementsByTagName("textarea");
    for(var i = 0; i < textareas.length; i++) {
        var textarea = textareas[i];
        textarea.style.height = "1px";
        textarea.style.height = (25 + textarea.scrollHeight)+"px";
    }
});


$("#edit-exercise").submit(function(e) {
    e.preventDefault();
    var datastring = $("#edit-exercise").serialize();
    $.ajax({
        type: "POST",
        url: "/exercise/edit",
        data: datastring,
        success: function(data) {
            $("#response").text(data);
        },
        error: function() {
            $("#response").text(data);
        }
    });
});
