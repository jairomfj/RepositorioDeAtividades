package br.com.repositoriodeatividades.usecases.exercise.imports.models;

import br.com.repositoriodeatividades.usecases.exercise.utils.enums.ExerciseEnumeration;
import br.com.repositoriodeatividades.usecases.exercise.utils.vo.MultipleChoiceExerciseItem;
import br.com.repositoriodeatividades.usecases.exercise.utils.interfaces.ExerciseItem;
import br.com.repositoriodeatividades.usecases.util.RepositoryUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.List;

@Scope("Request")
public class ExerciseItemBuilder {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private RepositoryUtils repositoryUtils = new RepositoryUtils();
    private String exerciseAsString;

    public ExerciseItemBuilder(String exerciseAsString) {
        this.exerciseAsString = exerciseAsString;
    }

    public List<ExerciseItem> buildExerciseItems() throws Exception {

        log.info("Building exercise items");

        if(exerciseAsString == null || exerciseAsString.equals("")) {
            throw new IllegalArgumentException("Exercise cannot be null or empty");
        }

        String[] splittedText = exerciseAsString.split("\\n");
        List<Integer> enumeratedIndexes = getEnumeratedIndexes(splittedText);
        return extractExerciseItems(enumeratedIndexes, splittedText);
    }

    private List<ExerciseItem> extractExerciseItems(List<Integer> enumeratedIndex, String[] splittedText) {

        if(enumeratedIndex.size() == 0) {
            throw new IllegalArgumentException("Enumerated index is invalid");
        }

        if(splittedText.length == 0) {
            throw new IllegalArgumentException("Splitted text is invalid");
        }


        List<ExerciseItem> exerciseItemList = new ArrayList();
        for(int i = 0; i < enumeratedIndex.size(); i++) {
            int startIndex;

            if(i == (enumeratedIndex.size() -1)) {
                startIndex = splittedText.length - 1;
            } else {
                startIndex = enumeratedIndex.get(i + 1) - 1;
            }

            int endIndex = enumeratedIndex.get(i);
            MultipleChoiceExerciseItem exerciseItem = new MultipleChoiceExerciseItem(new Long(i + 1));
            exerciseItemList.add(buildExerciseItem(startIndex, endIndex, splittedText, exerciseItem));
        }
        return exerciseItemList;
    }

    private MultipleChoiceExerciseItem buildExerciseItem(int startIndex, int endIndex, String[] splittedText, MultipleChoiceExerciseItem multipleChoiceExerciseItem) {

        if(splittedText == null) {
            return null;
        }

        if(endIndex > startIndex) {
            return multipleChoiceExerciseItem;
        }

        multipleChoiceExerciseItem.label = splittedText[startIndex] + " " + multipleChoiceExerciseItem.label.trim();
        return buildExerciseItem(--startIndex, endIndex, splittedText, multipleChoiceExerciseItem);
    }

    private List<Integer> getEnumeratedIndexes(String[] splittedString) throws Exception {

        if(splittedString == null || splittedString.equals("")) {
            throw new IllegalAccessException("Content cannot be null or empty");
        }

        List<Integer> indexList = new ArrayList<Integer>();
        for(int i = 0; i < splittedString.length; i++) {
            ExerciseEnumeration exerciseEnumeration = repositoryUtils.findEnumeration(splittedString[i]);

            if(exerciseEnumeration != ExerciseEnumeration.NONE) {
                indexList.add(i);
            }
        }
        return indexList;
    }



}
