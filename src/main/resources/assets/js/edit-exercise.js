

// function called on edit-exercise.html
function update() {

};


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