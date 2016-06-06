package br.com.repositoriodeatividades.usecases.util;


import br.com.repositoriodeatividades.usecases.exercise.utils.enums.ExerciseEnumeration;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class RepositoryUtils {

    public ExerciseEnumeration findEnumeration(String text) {
        text = text.replaceAll(" ","");
        for(ExerciseEnumeration enumeration : ExerciseEnumeration.values()) {
            Pattern pattern = Pattern.compile(enumeration.getFullEnumeration());
            Matcher matcher = pattern.matcher(text);
            if(matcher.find()) {
                return enumeration;
            }
        }
        return ExerciseEnumeration.NONE;
    }

    public String extractEnumerationFromString(String text) {
        return text.replaceAll(findEnumeration(text).getFullEnumeration(),"").trim();
    }

}
