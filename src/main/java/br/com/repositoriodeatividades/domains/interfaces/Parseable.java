package br.com.repositoriodeatividades.domains.interfaces;

import br.com.repositoriodeatividades.entities.Exercise;

import java.util.List;

public interface Parseable {

    List parse(List content) throws Exception;

}
