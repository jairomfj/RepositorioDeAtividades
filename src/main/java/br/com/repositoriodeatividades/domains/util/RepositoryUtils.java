package br.com.repositoriodeatividades.domains.util;


import br.com.repositoriodeatividades.domains.exercise.enums.ExerciseEnumeration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class RepositoryUtils {

    @Autowired
    ApplicationContext applicationContext;

    public ExerciseEnumeration findEnumeration(String text) {
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
        String finalText = "";
        finalText = text.replaceAll(findEnumeration(text).getFullEnumeration(),"");
        return finalText;
    }

    public String getFileContent(String path) {
        BufferedReader br = null;
        String fileContent = "";
        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader(applicationContext.getResource(path).getFile()));

            while ((sCurrentLine = br.readLine()) != null) {
                fileContent += sCurrentLine + "\n";
            }
            return fileContent;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
