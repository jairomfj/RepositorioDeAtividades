

// function called on exercises.html
function deleteExercise(id) {
    var confirmation = confirm("Deseja mesmo deletar esse exercício?");
    if (confirmation === true) deleteExerciseViaAjax(id);
}

//todo this is TOTALLY INCORRECT
function deleteExerciseViaAjax(id) {
    var _csrf = $("input[name=_csrf]").val();
    $.post("http://localhost:9090/exercise/delete", { id: id, _csrf: _csrf })
        .done(function( response ) {
            alert("Exercício excluído com sucesso!");
            window.location = "http://localhost:8080/exercise/";
        })
        .fail(function(response) {
            alert("Erro ao excluir o exercício selecionado.");
        });
}
