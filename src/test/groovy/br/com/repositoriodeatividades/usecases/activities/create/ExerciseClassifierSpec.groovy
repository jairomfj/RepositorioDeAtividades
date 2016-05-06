package br.com.repositoriodeatividades.usecases.activities.create

import br.com.repositoriodeatividades.entities.Exercise
import br.com.repositoriodeatividades.entities.Tag
import spock.lang.Specification

class ExerciseClassifierSpec extends Specification {

    def exerciseClassifier

    def setup() {
        exerciseClassifier = new ExerciseClassifier();
    }

    def 'should classify and return list ordered'() {
        given:
        def tagString = ["A", "B", "C"]
        def tags1 = createTags(tagString)
        def tags2 = createTags(["A"])
        def tags3 = createTags(["A", "B"])

        def exercises = new ArrayList()
        exercises.add(createExercise(tags1))
        exercises.add(createExercise(tags2))
        exercises.add(createExercise(tags3))

        when:
        def response = exerciseClassifier.classify(exercises, (String[]) tagString.toArray())

        then:
        1.0 == response[0].score
        0.67 == response[1].score.round(2)
        0.33 == response[2].score.round(2)

    }

    def createExercise(def tags) {
        return new Exercise(tags: tags)
    }

    def createTags(def tags) {
        def tagList = new ArrayList()
        tags.each {
            tagList.add(new Tag(label: it))
        }
        return tagList
    }

}
