package br.com.repositoriodeatividades.domains.interfaces;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by jairomendes on 4/2/16.
 */
public interface Readable {

    public String read(MultipartFile multipartFile) throws Exception;
}
