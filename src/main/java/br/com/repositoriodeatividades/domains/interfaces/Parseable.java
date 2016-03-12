package br.com.repositoriodeatividades.domains.interfaces;

import br.com.repositoriodeatividades.entities.Exercise;

import java.util.List;

public interface Parseable {

    List<Exercise> parse(List<String> exercise) throws Exception;

}
