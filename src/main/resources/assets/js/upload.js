
$(document).ready(function(){

    adaptTextareaSize();

    $(".save-exercise").click(function(e) {
        e.preventDefault();
        var clickedForm = $(this).parent();
        while(true) {
            var itemTagName = clickedForm[0].tagName;
            if(itemTagName == 'FORM') break;
            if(itemTagName == 'BODY') { clickedForm = null; break; }
            clickedForm = clickedForm.parent()
        }
        var uri = clickedForm.attr("action");
        var formData = clickedForm.serializeArray();

        postFormData(clickedForm, uri, formData);

    });

    function postFormData(clickedForm, uri, formData) {
        $.post(uri, formData, function(data, textStatus, jqXHR) {})
            .done(function() {
                alert("Exercício importado com sucesso!");
                clickedForm.slideUp("1000");
                updateExercisesLeftAmmount();
            })

            .fail(function() {
                alert("Erro ao importar o exercício selecionado!");
            })
    }

    function adaptTextareaSize() {
        var textareas = document.getElementsByTagName("textarea");
        for(var i = 0; i < textareas.length; i++) {
            var textarea = textareas[i];
            textarea.style.height = "1px";
            textarea.style.height = (25 + textarea.scrollHeight)+"px";
        }
    }

});
