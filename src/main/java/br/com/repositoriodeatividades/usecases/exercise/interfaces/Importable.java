package br.com.repositoriodeatividades.usecases.exercise.interfaces;

import br.com.repositoriodeatividades.entities.Exercise;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface Importable {

    List<Exercise> execute(MultipartFile file) throws Exception;
}
