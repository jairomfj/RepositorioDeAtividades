package br.com.repositoriodeatividades.usecases.activities.create.models;

import br.com.repositoriodeatividades.entities.ExerciseEntity;
import br.com.repositoriodeatividades.entities.TagEntity;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ExerciseClassifier {

    public List<Map> classify(List<ExerciseEntity> exerciseList, String[] tags) {
        List<Map> exerciseMapList = new ArrayList();

        for(ExerciseEntity exercise : exerciseList) {
            Map map = new HashMap();
            String[] exerciseTags = extractExerciseTags(exercise.getTags());
            map.put("score", calculateScore(exerciseTags, tags));
            map.put("exercise", exercise);
            exerciseMapList.add(getIndex((Double) map.get("score"), exerciseMapList), map);
        }

        return exerciseMapList;
    }

    private int getIndex(double score, List<Map> exerciseMapList) {

        int listSize = exerciseMapList.size();
        if(listSize == 0) {
            return 0;
        }

        int place = 0;
        for(; place < listSize; place++) {
            double listScore = (Double) (exerciseMapList.get(place).get("score"));
            if(score > listScore) {
                break;
            }
        }

        return place;
    }

    private Double calculateScore(String[] exerciseTags, String[] tags) {

        int tagsSize = tags.length;
        double match = 0;
        for(String tag : tags) {
            for(String exerciseTag : exerciseTags) {
                if(exerciseTag.equals(tag)) {
                    match++;
                }
            }
        }
        return match / tagsSize;
    }

    private String[] extractExerciseTags(List<TagEntity> exerciseTags) {

        List<String> stringTagList = new ArrayList<>();

        for(TagEntity tag : exerciseTags) {
            stringTagList.add(tag.getLabel());
        }

        String[] tagArray = new String[exerciseTags.size()];
        return stringTagList.toArray(tagArray);
    }



}
