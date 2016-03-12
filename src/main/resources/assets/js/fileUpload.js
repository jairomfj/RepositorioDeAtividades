/**
 * Created by jairomendes on 3/9/16.
 */


$(document).ready(function() {
    //$("#upload-file-input").on("change", uploadFile);
    //alert("oi");
});

/**
 * Upload the file sending it via Ajax at the Spring Boot server.
 */
function uploadFile() {
    $.ajax({
        url: "/uploadFile",
        type: "POST",
        data: new FormData($("#upload-file-form")[0]),
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        success: function () {
            alert("success");
        },
        error: function () {
            alert("error");
        }
    });
} // function uploadFile