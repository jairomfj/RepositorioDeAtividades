package br.com.repositoriodeatividades.domains.exercise;

import br.com.repositoriodeatividades.Application;
import br.com.repositoriodeatividades.domains.util.RepositoryUtils;
import br.com.repositoriodeatividades.entities.Exercise;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ExerciseParserParallelTests {

    @Autowired
    ExerciseExtractor exerciseExtractor;

    @Autowired
    RepositoryUtils repositoryUtils;

//    @Test
//    public void shouldParseExercise() {
//        String path = "classpath:single_exercise_enumerated_by_number_and_choices_by_alphabet.txt";
//        List<String> exerciseListAsString = exerciseExtractor.extract(repositoryUtils.getFileContent(path));
//        List<Exercise> exerciseList = new ArrayList();
//        ExerciseParserParallel exerciseParserParallel = new ExerciseParserParallel(exerciseListAsString.get(0), exerciseList);
//        exerciseParserParallel.run();
//
//        assertEquals(5, exerciseList.size());
//    }

//    @Test
//    @Ignore
//    public void shouldGetEnumeratedIndexes() {
//        String path = "classpath:single_exercise_enumerated_by_number_and_choices_by_alphabet.txt";
//        List<String> exerciseListAsString = exerciseExtractor.extract(repositoryUtils.getFileContent(path));
//        List<Exercise> exerciseList = new ArrayList<Exercise>();
//        ExerciseParserParallel exerciseParserParallel = new ExerciseParserParallel(exerciseListAsString.get(0), exerciseList);
//        List<Integer> indexes = new ArrayList<Integer>();
//        try {
//            indexes = exerciseParserParallel.getEnumeratedIndexes(exerciseListAsString.get(0).split("\\n"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        assertEquals(6, indexes.size());
//    }

//    @Test
//    public void shouldBuildExerciseItem() {
//
//        String text = "Exercício para nota!\n" +
//                      "1) Assinale a alternativa que contém o plural correto dos seguintes substantivos:\n" +
//                      "monkey - appendix - story - shrimp - tomato - proof";
//        MultipleChoiceExerciseItem exerciseItem = new MultipleChoiceExerciseItem(1l);
//        ExerciseParserParallel exerciseParserParallel = new ExerciseParserParallel(new String(), new ArrayList<Exercise>());
//        MultipleChoiceExerciseItem exerciseItemResultant = exerciseParserParallel.buildExerciseItem(2, 1, text.split("\\n"), exerciseItem);
//
//        assertEquals("1) Assinale a alternativa que contém o plural correto dos seguintes substantivos: monkey - appendix - story - shrimp - tomato - proof", exerciseItemResultant.label);
//
//    }



}
