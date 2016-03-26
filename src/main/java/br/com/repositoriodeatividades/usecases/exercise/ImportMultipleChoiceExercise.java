package br.com.repositoriodeatividades.usecases.exercise;


import br.com.repositoriodeatividades.domains.dao.exercise.ExerciseDao;
import br.com.repositoriodeatividades.domains.exercise.ExerciseExtractor;
import br.com.repositoriodeatividades.domains.exercise.ExerciseParser;
import br.com.repositoriodeatividades.domains.exercise.PdfReader;
import br.com.repositoriodeatividades.domains.interfaces.Importable;
import br.com.repositoriodeatividades.entities.Exercise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImportMultipleChoiceExercise implements Importable {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PdfReader pdfReader;

    @Autowired
    ExerciseExtractor exerciseExtractor;

    @Autowired
    ExerciseParser exerciseParser;

    @Autowired
    ExerciseDao exerciseDao;

    @Override
    public List<Exercise> execute(MultipartFile file) {
        List<Exercise> exerciseList = new ArrayList<Exercise>();
        try {
            log.info("Initiating parse of the file " + file.getName());
            String fileContent = pdfReader.read(file);
            List<String> exercisesAsString = exerciseExtractor.extract(fileContent);
            exerciseList = exerciseParser.parse(exercisesAsString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exerciseList;
    }
}
