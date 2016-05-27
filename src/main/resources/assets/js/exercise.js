

// function called on exercise.html
function deleteExercise(id) {
    var confirmation = confirm("Deseja mesmo deletar esse exercício?");
    if (confirmation == true) deleteExerciseViaAjax(id);
}

function deleteExerciseViaAjax(id) {
    var _csrf = $("input[name=_csrf]").val();
    $.post("http://localhost:8080/exercise/delete", { id: id, _csrf: _csrf })
        .done(function( response ) {
            alert("Exercício excluído com sucesso!");
            location.reload();
        })
        .fail(function(response) {
            alert("Erro ao excluir o exercício selecionado.");
        });
}
