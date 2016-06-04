package br.com.repositoriodeatividades.usecases.exercise

import org.apache.commons.io.FileUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ConfigurableApplicationContext
import spock.lang.Specification

class BaseIntegration extends Specification {

    @Autowired
    ConfigurableApplicationContext context;

    public String readFile(String path) {
        try {
            path = "classpath:" + path;
            return FileUtils.readFileToString(context.getResource(path).getFile());
        } catch (Exception e) {
            return null;
        }
    }

}
