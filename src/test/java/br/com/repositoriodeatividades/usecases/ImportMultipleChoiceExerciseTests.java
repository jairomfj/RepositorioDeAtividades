package br.com.repositoriodeatividades.usecases;

import br.com.repositoriodeatividades.Application;
import br.com.repositoriodeatividades.usecases.exercise.ImportMultipleChoiceExercise;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;

import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ImportMultipleChoiceExerciseTests {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    ImportMultipleChoiceExercise importMultipleChoiceExercise;

    @Test
    public void contextLoads() throws IOException {
        Resource resource = applicationContext.getResource("classpath:exercise_enumerated_by_number_choices_enumerated_by_alphabet.pdf");
        importMultipleChoiceExercise.execute(resource.getFile());
    }

}
