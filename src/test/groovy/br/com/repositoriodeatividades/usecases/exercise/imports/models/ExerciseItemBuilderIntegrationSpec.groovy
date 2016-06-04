package br.com.repositoriodeatividades.usecases.exercise.imports.models

import br.com.repositoriodeatividades.usecases.exercise.BaseIntegration

class ExerciseItemBuilderIntegrationSpec extends BaseIntegration {

    def 'should extract exercise items'() {
        given:
        String exerciseString = readFile("single_exercise_enumerated_by_number_and_choices_by_alphabet.txt")
        def exerciseItemBuilder = new ExerciseItemBuilder(exerciseString);

        when:
        def exerciseItem = exerciseItemBuilder.buildExerciseItems()

        then:
        6 == exerciseItem.size()
    }

    def 'should throw exception when exerciseAsString is null'() {
        given:
        def exerciseItemBuilder = new ExerciseItemBuilder(null);

        when:
        exerciseItemBuilder.buildExerciseItems()

        then:
        thrown(IllegalArgumentException)
    }

    def 'should throw exception when exerciseAsString has not a valid value'() {
        given:
        def exerciseItemBuilder = new ExerciseItemBuilder("This is a invalid exercise");

        when:
        exerciseItemBuilder.buildExerciseItems()

        then:
        thrown(IllegalArgumentException)
    }

}
