package br.com.repositoriodeatividades.domains.interfaces;

import br.com.repositoriodeatividades.entities.Exercise;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by jairomendes on 3/9/16.
 */
public interface Importable {

    List<Exercise> execute(MultipartFile file);
}
