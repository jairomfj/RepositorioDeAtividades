package br.com.repositoriodeatividades.usecases.exercise.utils.interfaces;

import java.util.List;

public interface Parseable {

    List parse(List<String> content) throws Exception;

}
