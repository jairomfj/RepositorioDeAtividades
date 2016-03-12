package br.com.repositoriodeatividades.domains.interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface Readable {
    String read(MultipartFile file) throws IOException;

}
