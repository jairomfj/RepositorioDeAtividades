package br.com.repositoriodeatividades.usecases.interfaces;

import java.util.List;

public interface Extractable {

    List<String> extract(String fileContent) throws Exception;

}
