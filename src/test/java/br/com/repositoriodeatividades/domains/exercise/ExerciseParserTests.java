package br.com.repositoriodeatividades.domains.exercise;

import br.com.repositoriodeatividades.Application;
import br.com.repositoriodeatividades.domains.extractors.ExerciseExtractor;
import br.com.repositoriodeatividades.domains.parsers.ExerciseParser;
import br.com.repositoriodeatividades.domains.util.RepositoryUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ExerciseParserTests {

    @Autowired
    ExerciseExtractor exerciseExtractor;

    @Autowired
    ExerciseParser exerciseParser;

    @Autowired
    RepositoryUtils repositoryUtils;

    @Test
    public void shouldExtractExercise() throws Exception {
        String path = "classpath:multiple_exercises_enumerated_by_number_and_choices_by_alphabet.txt";
        List<String> exerciseList = exerciseExtractor.extract(repositoryUtils.getFileContent(path));
        exerciseParser.parse(exerciseList);

        assertEquals(5, exerciseList.size());
    }



}