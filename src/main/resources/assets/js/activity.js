

$("#submit").click(function(){
    var fields = document.getElementsByClassName("tags");
    validateFields(fields);
});


function validateFields(fields) {
    for(var i = 0; i < fields.length; i++) {
        if(fields[i].value.trim() == '') {
            var fieldNumber = fields[i].name.slice(-1);
            var input = "amount_" + fieldNumber + "";
            var inputField = document.getElementsByName(input)[0];
            inputField.removeAttribute("required");
        }
    }
}
