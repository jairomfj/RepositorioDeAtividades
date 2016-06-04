package br.com.repositoriodeatividades.usecases.exercise.imports.models

import br.com.repositoriodeatividades.usecases.exercise.BaseIntegration
import org.springframework.beans.factory.annotation.Autowired

class ExerciseParserIntegrationSpec extends BaseIntegration {

    @Autowired
    ExerciseParser exerciseParser

    def 'should parse exercises successfully'() {
        given:
        def exerciseList = []
        exerciseList.add("1) Exercise 1")
        exerciseList.add("2) Exercise 1")
        exerciseList.add("3) Exercise 1")
        exerciseList.add("4) Exercise 1")
        exerciseList.add("5) Exercise 1")

        when:
        def parsedExercises = exerciseParser.parse(exerciseList)

        then:
        5 == parsedExercises.size()


    }

}
