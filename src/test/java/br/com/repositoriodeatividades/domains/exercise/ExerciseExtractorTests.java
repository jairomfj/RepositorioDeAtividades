package br.com.repositoriodeatividades.domains.exercise;

import br.com.repositoriodeatividades.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;


@Profile("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ExerciseExtractorTests {

    @Autowired
    ExerciseExtractor exerciseExtractor;

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void shouldExtractExercise() throws Exception {
        String path = "classpath:multiple_exercises_enumerated_by_number_and_choices_by_alphabet.txt";
        List<String> exerciseList = exerciseExtractor.extract(getFileContent(path));

        assertEquals(5, exerciseList.size());

    }

    private String getFileContent(String path) {
        BufferedReader br = null;
        String fileContent = "";
        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader(applicationContext.getResource(path).getFile()));

            while ((sCurrentLine = br.readLine()) != null) {
                fileContent += sCurrentLine + "\n";
            }
            return fileContent;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
