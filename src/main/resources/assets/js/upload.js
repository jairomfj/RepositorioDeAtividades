/**
 * Created by jairomendes on 4/16/16.
 */

$(document).ready(function(){

    var exerciseLength = parseInt($("#exercise-length").val());

    hideThings();

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

    function updateExercisesLeftAmmount() {
        exerciseLength = exerciseLength - 1;
        if(exerciseLength == 0) {
            $("#all-exercises-imported").show();
            $("#extracted-exercises").hide();
        }
    }

    function hideThings() {
        $("#all-exercises-imported").hide();
    }

});
