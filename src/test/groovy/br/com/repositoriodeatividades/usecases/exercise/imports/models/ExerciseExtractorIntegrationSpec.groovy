package br.com.repositoriodeatividades.usecases.exercise.imports.models

import br.com.repositoriodeatividades.Application
import br.com.repositoriodeatividades.usecases.exercise.BaseIntegration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.IntegrationTest
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration

@ContextConfiguration(loader = SpringApplicationContextLoader, classes = Application)
@WebAppConfiguration
@IntegrationTest
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
