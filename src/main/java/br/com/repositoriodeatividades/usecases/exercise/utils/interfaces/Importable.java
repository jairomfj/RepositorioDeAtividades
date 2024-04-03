package br.com.repositoriodeatividades.usecases.exercise.utils.interfaces;

import br.com.repositoriodeatividades.entities.ExerciseEntity;
import br.com.repositoriodeatividades.usecases.exercise.imports.models.ExtractedExercise;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface Importable {

    List<ExtractedExercise> execute(MultipartFile file) throws Exception;
    List<ExtractedExercise> executeV2(MultipartFile file) throws Exception;
}
