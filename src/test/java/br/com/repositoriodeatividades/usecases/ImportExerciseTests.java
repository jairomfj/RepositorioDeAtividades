package br.com.repositoriodeatividades.usecases;

import br.com.repositoriodeatividades.Application;
import br.com.repositoriodeatividades.usecases.exercise.ImportExercise;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ImportExerciseTests {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    ImportExercise importExercise;

//    @Test
//    public void contextLoads() throws IOException {
//        Readable fileReader = importExercise.getFileReader("application/pdf");
//
//        assertTrue(fileReader instanceof PdfReader);
//
//    }



}
