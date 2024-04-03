package br.com.repositoriodeatividades.usecases.exercise.imports;


import br.com.repositoriodeatividades.usecases.exercise.imports.models.ExtractedExercise;
import br.com.repositoriodeatividades.usecases.exercise.imports.models.ExerciseExtractor;
import br.com.repositoriodeatividades.usecases.exercise.imports.models.ExerciseParser;
import br.com.repositoriodeatividades.usecases.exercise.imports.models.exceptions.InvalidFileFormatException;
import br.com.repositoriodeatividades.usecases.exercise.utils.enums.FileReaderType;
import br.com.repositoriodeatividades.usecases.exercise.utils.interfaces.Importable;
import br.com.repositoriodeatividades.usecases.exercise.utils.interfaces.Readable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ImportExercise implements Importable {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ExerciseExtractor exerciseExtractor;

    @Autowired
    ExerciseParser exerciseParser;

    @Override
    public List<ExtractedExercise> execute(MultipartFile file) throws Exception {
        log.info("Initiating importation of the file " + file.getOriginalFilename());

        if (file.getSize() == 0) {
            throw new IllegalArgumentException("No file was selected");
        }

        Readable fileReader = getFileReader(file.getOriginalFilename());
        String fileContent = fileReader.read(file);
        List<String> exercisesAsString = exerciseExtractor.extract(fileContent);
        return exerciseParser.parse(exercisesAsString);
    }

    @Override
    public List<ExtractedExercise> executeV2(MultipartFile file) throws Exception {
        log.info("Initiating importation of the file " + file.getOriginalFilename());

        if (file.getSize() == 0) {
            throw new IllegalArgumentException("No file was selected");
        }

        Readable fileReader = getFileReader(file.getOriginalFilename());
        String fileContent = fileReader.read(file);
        List<String> exercisesAsString = exerciseExtractor.extract(fileContent);

        return exerciseParser.parse(exercisesAsString);
    }

    private Readable getFileReader(String contentType) {
        try {
            int startIndex = contentType.indexOf(".") + 1;
            String text = contentType.substring(startIndex);
            return FileReaderType.valueOf(text.toUpperCase()).getReader();
        } catch (Exception e) {
            throw new InvalidFileFormatException("This format is not accepted.");
        }
    }
}
