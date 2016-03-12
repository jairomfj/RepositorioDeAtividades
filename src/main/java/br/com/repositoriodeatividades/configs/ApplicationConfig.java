package br.com.repositoriodeatividades.configs;


import br.com.repositoriodeatividades.usecases.exercise.ImportMultipleChoiceExercise;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public ImportMultipleChoiceExercise importMultipleChoiceExercise() {
        return new ImportMultipleChoiceExercise();
    }

}
