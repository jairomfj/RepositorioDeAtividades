package br.com.repositoriodeatividades.usecases.exercise.imports.models

import br.com.repositoriodeatividades.usecases.exercise.BaseIntegration
import org.springframework.beans.factory.annotation.Autowired

class ExerciseExtractorIntegrationSpec extends BaseIntegration {

    @Autowired
    ExerciseExtractor exerciseExtractor

    def 'should extract single exercise'() {
        given:
        String fileContent = readFile("single_exercise_enumerated_by_number_and_choices_by_alphabet.txt");

        when:
        def exercises = exerciseExtractor.extract(fileContent)

        then:
        1 == exercises.size()
    }

}
