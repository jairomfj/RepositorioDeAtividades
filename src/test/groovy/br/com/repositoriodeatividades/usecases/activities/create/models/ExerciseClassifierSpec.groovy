package br.com.repositoriodeatividades.usecases.activities.create.models


import spock.lang.Specification

class ExerciseClassifierSpec extends Specification {

    def exerciseClassifier

    def setup() {
        exerciseClassifier = new ExerciseClassifier()
    }

    def 'should classify exercises'() {
        given:
        def filterTags = ["A", "B", "C", "D"]

        def tags1 = [new Tag("A"), new Tag("B"), new Tag("C"), new Tag("D")]
        def exercise1 = new Exercise(); exercise1.setTags(tags1)

        def tags2 = [new Tag("A"), new Tag("B"), new Tag("C")]
        def exercise2 = new Exercise(); exercise2.setTags(tags2)

        def tags3 = [new Tag("A"), new Tag("B")]
        def exercise3 = new Exercise(); exercise3.setTags(tags3)

        def tags4 = [new Tag("A")]
        def exercise4 = new Exercise(); exercise4.setTags(tags4)

        when:
        def response = exerciseClassifier.classify([exercise1, exercise2, exercise3, exercise4],  filterTags as String[])

        then:
        null != response.size()
        1.0  == response.get(0).score
        0.75 == response.get(1).score
        0.5  == response.get(2).score
        0.25 == response.get(3).score

    }


}
