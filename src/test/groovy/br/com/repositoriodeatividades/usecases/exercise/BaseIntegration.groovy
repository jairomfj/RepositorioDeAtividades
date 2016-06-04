package br.com.repositoriodeatividades.usecases.exercise

import br.com.repositoriodeatividades.Application
import org.apache.commons.io.FileUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.IntegrationTest
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import spock.lang.Specification

@ContextConfiguration(loader = SpringApplicationContextLoader, classes = Application)
@WebAppConfiguration
@IntegrationTest
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
