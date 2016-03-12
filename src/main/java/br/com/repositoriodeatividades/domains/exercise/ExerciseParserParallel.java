package br.com.repositoriodeatividades.domains.exercise;

import br.com.repositoriodeatividades.domains.enums.ExerciseEnumeration;
import br.com.repositoriodeatividades.domains.interfaces.ExerciseItem;
import br.com.repositoriodeatividades.domains.util.RepositoryUtils;
import br.com.repositoriodeatividades.domains.vo.exercise.MultipleChoiceExerciseItem;
import br.com.repositoriodeatividades.entities.Exercise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ExerciseParserParallel implements Runnable {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private String exerciseString;
    private List<Exercise> exerciseList;

    private RepositoryUtils repositoryUtils = new RepositoryUtils();

    ExerciseParserParallel(String exerciseString, List<Exercise> exerciseList) {
        this.exerciseString = exerciseString;
        this.exerciseList = exerciseList;
    }

    @Override
    public void run() {
        try {
            List<List<ExerciseItem>> exerciseItemList = buildExerciseItems();
            parseExerciseItemsToExerciseList(exerciseItemList);
        } catch (Exception e) {
            log.error("Error during parse:" + e.getStackTrace().toString());
            e.printStackTrace();
        }
    }

    private void parseExerciseItemsToExerciseList(List<List<ExerciseItem>> exerciseItemList) {
        try {
            exerciseList.add(new ExerciseBuilder().build(exerciseItemList).get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<List<ExerciseItem>> buildExerciseItems() throws Exception {

        log.info("Building exercise items");

        List<List<ExerciseItem>> exerciseItemList = new ArrayList<List<ExerciseItem>>();

        if(exerciseString == null) {
            throw new IllegalAccessException("Exercise list cannot be null");
        }

        try {
            String[] splittedText = exerciseString.split("\\n");
            List<Integer> enumeratedIndex = getEnumeratedIndexes(splittedText);
            List<ExerciseItem> multipleChoiceExerciseItemList = extractExerciseItems(enumeratedIndex, splittedText);
            exerciseItemList.add(multipleChoiceExerciseItemList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exerciseItemList;
    }

    private List<ExerciseItem> extractExerciseItems(List<Integer> enumeratedIndex, String[] splittedText) {
        List<ExerciseItem> multipleChoiceExerciseItemList = new ArrayList();
        for(int i = 0; i < enumeratedIndex.size(); i++) {

            int startIndex;
            if(i == (enumeratedIndex.size() -1)) {
                startIndex = splittedText.length - 1;
            } else {
                startIndex = enumeratedIndex.get(i + 1) - 1;
            }

            int endIndex = enumeratedIndex.get(i);
            MultipleChoiceExerciseItem multipleChoiceExerciseItem = new MultipleChoiceExerciseItem(new Long(i + 1));
            multipleChoiceExerciseItemList.add(buildExerciseItem(startIndex, endIndex, splittedText, multipleChoiceExerciseItem));
        }
        return multipleChoiceExerciseItemList;
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
            log.error("Cannot get enumerated index of an invalid array");
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
