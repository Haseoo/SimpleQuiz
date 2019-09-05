package gamecore.projections.questions;

import exceptions.questions.UnableToDrawQuestionException;
import gamecore.repositories.QuestionRepository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.groupingBy;
import static utility.Constants.BEGIN_INDEX;

public class QuestionList {
    private List<QuestionCoords> availableQuestions;
    private Random random;

    public QuestionList() {
        random = new Random();
        availableQuestions = new LinkedList<>();
        IntStream categoryIndexesSequence = IntStream.range(BEGIN_INDEX, QuestionRepository.getNumberOfCategories());
        categoryIndexesSequence.forEach(this::generateCoordsForCategory);
    }

    public Integer getNumberOfAvilableQuestions() {
        return availableQuestions.size();
    }

    public QuestionCoords getRandomQuestion() {
        int index = random.nextInt(availableQuestions.size());
        return getQuestionCoords(index);
    }

    public QuestionCoords getRandomQuestion(Integer categoryIndex) {
        List<QuestionCoords> availableQuestionsWithCategory = getQuestionCoordsWithGivenCategory(categoryIndex);
        if(availableQuestionsWithCategory.isEmpty()) {
            throw new UnableToDrawQuestionException();
        }
        int index = random.nextInt(availableQuestionsWithCategory.size());
        return getQuestionCoords(index);
    }

    public Integer[] getAvailableCategoryIndexes() {
        Map<Integer, Long> map = availableQuestions.stream()
                .collect(groupingBy(QuestionCoords::getCategoryIndex, Collectors.counting()));
        return getShuffledCategoryIndexes(map);
    }

    private Integer[] getShuffledCategoryIndexes(Map<Integer, Long> map) {
        List<Integer> categoryIndexes = map
                .keySet()
                .stream()
                .filter(key -> map.get(key) > 0)
                .collect(Collectors.toList());
        Collections.shuffle(categoryIndexes);
        return categoryIndexes.toArray(new Integer[0]);
    }

    private QuestionCoords getQuestionCoords(int index) {
        QuestionCoords questionCoords = availableQuestions.get(index);
        availableQuestions.remove(index);
        return questionCoords;
    }

    private List<QuestionCoords> getQuestionCoordsWithGivenCategory(Integer categoryIndex) {
        return availableQuestions
                    .stream()
                    .filter(categoryCoords -> categoryCoords.getCategoryIndex().equals(categoryIndex))
                    .collect(Collectors.toList());
    }

    private void generateCoordsForCategory(Integer categoryIndex){
        IntStream.range(BEGIN_INDEX, QuestionRepository.getNumberOfQuestionInCategory(categoryIndex))
        .forEach(questionIndex -> availableQuestions.add(new QuestionCoords(categoryIndex, questionIndex)));
    }
}
