package br.com.repositoriodeatividades.usecases.util

import br.com.repositoriodeatividades.usecases.exercise.utils.enums.ExerciseEnumeration
import spock.lang.Specification

class RepositoryUtilsSpec extends Specification {


    def repositoryUtils

    def setup() {
        repositoryUtils = new RepositoryUtils()
    }

    def 'should find exercise BULLET enumeration'() {
        given:
        def text = "• This is an example"

        when:
        def enumeration = repositoryUtils.findEnumeration(text)

        then:
        ExerciseEnumeration.BULLET == enumeration

    }

    def 'should find exercise NUMBER_BAR enumeration'() {
        given:
        def text = "1) This is an example"

        when:
        def enumeration = repositoryUtils.findEnumeration(text)

        then:
        ExerciseEnumeration.NUMBER_BAR == enumeration

    }

    def 'should find exercise NUMBER_DOT enumeration'() {
        given:
        def text = "1. This is an example"

        when:
        def enumeration = repositoryUtils.findEnumeration(text)

        then:
        ExerciseEnumeration.NUMBER_DOT == enumeration

    }

    def 'should find exercise NUMBER_HYPHEN enumeration'() {
        given:
        def text = "1- This is an example"

        when:
        def enumeration = repositoryUtils.findEnumeration(text)

        then:
        ExerciseEnumeration.NUMBER_HYPHEN == enumeration

    }

    def 'should find exercise NUMBER_SPACE_HYPHEN enumeration'() {
        given:
        def text = "1 - This is an example"

        when:
        def enumeration = repositoryUtils.findEnumeration(text)

        then:
        ExerciseEnumeration.NUMBER_HYPHEN == enumeration

    }

    def 'should find exercise CHAR_BAR enumeration'() {
        given:
        def text = "a) This is an example"

        when:
        def enumeration = repositoryUtils.findEnumeration(text)

        then:
        ExerciseEnumeration.CHAR_BAR == enumeration

    }

    def 'should find exercise CHAR_DOT enumeration'() {
        given:
        def text = "a. This is an example"

        when:
        def enumeration = repositoryUtils.findEnumeration(text)

        then:
        ExerciseEnumeration.CHAR_DOT == enumeration

    }

    def 'should find exercise CHAR_HYPHEN enumeration'() {
        given:
        def text = "a- This is an example"

        when:
        def enumeration = repositoryUtils.findEnumeration(text)

        then:
        ExerciseEnumeration.CHAR_HYPHEN == enumeration

    }

    def 'should find exercise CHAR_SPACE_HYPHEN enumeration'() {
        given:
        def text = "a - This is an example"

        when:
        def enumeration = repositoryUtils.findEnumeration(text)

        then:
        ExerciseEnumeration.CHAR_HYPHEN == enumeration

    }

    def 'should find exercise PARENTHESIS enumeration'() {
        given:
        def text = "( ) This is an example"

        when:
        def enumeration = repositoryUtils.findEnumeration(text)

        then:
        ExerciseEnumeration.PARENTHESIS == enumeration

    }

    def 'should find exercise NONE enumeration'() {
        given:
        def text = "This is an example"

        when:
        def enumeration = repositoryUtils.findEnumeration(text)

        then:
        ExerciseEnumeration.NONE == enumeration

    }

    def 'should remove exercise BULLET enumeration'() {
        expect:
        parsedText == repositoryUtils.extractEnumerationFromString(text)

        where:
        parsedText           | text
        "This is an example" | "• This is an example"
        "This is an example" | "1) This is an example"
        "This is an example" | "1. This is an example"
        "This is an example" | "1- This is an example"
        "This is an example" | "a) This is an example"
        "This is an example" | "a. This is an example"
        "This is an example" | "a- This is an example"
        "This is an example" | "() This is an example"
        "This is an example" | " This is an example"

    }

}
