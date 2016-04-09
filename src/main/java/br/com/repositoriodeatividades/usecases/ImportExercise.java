package br.com.repositoriodeatividades.usecases;


import br.com.repositoriodeatividades.domains.extractors.ExerciseExtractor;
import br.com.repositoriodeatividades.domains.parsers.ExerciseParser;
import br.com.repositoriodeatividades.domains.enums.FileReaderType;
import br.com.repositoriodeatividades.domains.interfaces.Importable;
import br.com.repositoriodeatividades.domains.interfaces.Readable;
import br.com.repositoriodeatividades.entities.Exercise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImportExercise implements Importable {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ExerciseExtractor exerciseExtractor;

    @Autowired
    ExerciseParser exerciseParser;

    @Override
    public List<Exercise> execute(MultipartFile file) {
        List<Exercise> exerciseList = new ArrayList<Exercise>();
        try {
            log.info("Initiating importation of the file " + file.getOriginalFilename());
            Readable fileReader = getFileReader(file.getOriginalFilename());
            String fileContent = fileReader.read(file);
            List<String> exercisesAsString = exerciseExtractor.extract(fileContent);
            exerciseList = exerciseParser.parse(exercisesAsString);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return exerciseList;
    }


    private Readable getFileReader(String contentType) {
        int startIndex = contentType.indexOf(".") + 1;
        String text = contentType.substring(startIndex, contentType.length());
        return FileReaderType.valueOf(text.toUpperCase()).getReader();
    }
}
