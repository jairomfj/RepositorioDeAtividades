package br.com.repositoriodeatividades.usecases.exercise.interfaces;

import java.util.List;

public interface Extractable {

    List<String> extract(String fileContent) throws Exception;

}
