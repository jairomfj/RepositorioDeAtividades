package br.com.repositoriodeatividades.usecases.exercise.create.models

import br.com.repositoriodeatividades.entities.Tag
import spock.lang.Specification

class TagParserSpec extends Specification {

    def tagParser

    def setup() {
        this.tagParser = new TagParser()
    }

    def 'should parse tags successfully'() {
        given:
        String[] tags = ["tag1", "tag2", "tag3"]

        when:
        def response = tagParser.parse(tags)

        then:
        3 == response.size
        response instanceof List<Tag>
    }

    def 'should return null when no tags have been passed'() {
        given:
        String[] tags = null

        when:
        def response = tagParser.parse(tags)

        then:
        [] == response
    }


}
