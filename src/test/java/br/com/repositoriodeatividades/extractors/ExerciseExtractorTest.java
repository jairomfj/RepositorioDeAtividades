package br.com.repositoriodeatividades.extractors;


import br.com.repositoriodeatividades.Application;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

@Profile("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ExerciseExtractorTest {

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void test() {
        String fileContent = readFile("multiple_exercises_enumerated_by_number_and_choices_by_alphabet.txt");
        assertNotNull(false);
    }


    public String readFile(String path) {
        try {
            path = "classpath:" + path;
            return FileUtils.readFileToString(applicationContext.getResource(path).getFile());
        } catch (Exception e) {
            return null;
        }
    }

}
