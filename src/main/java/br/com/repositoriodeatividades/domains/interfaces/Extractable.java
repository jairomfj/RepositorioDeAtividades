package br.com.repositoriodeatividades.domains.interfaces;

import java.io.File;
import java.io.IOException;

public interface Extractable {
    String read(File file) throws IOException;
}
