package gamecore.services;

import exceptions.questions.UnableToDrawQuestionException;
import gamecore.projections.questions.Question;
import gamecore.projections.questions.QuestionCoords;
import gamecore.repositories.IQuestionRepository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static gamecore.utility.Constants.BEGIN_INDEX;
import static java.util.stream.Collectors.groupingBy;

public class QuestionService implements IQuestionService{
    private List<QuestionCoords> availableQuestions;
    private final IQuestionRepository questionRepository;
    private Random random;

    public QuestionService(IQuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
        random = new Random();
        availableQuestions = new LinkedList<>();
        IntStream categoryIndexesSequence = IntStream.range(BEGIN_INDEX, questionRepository.getNumberOfCategories());
        categoryIndexesSequence.forEach(this::generateCoordsForCategory);
    }

    @Override
    public Integer getNumberOfAvailableQuestions() {
        return availableQuestions.size();
    }

    @Override
    public Question getRandomQuestion() {
        int index = random.nextInt(availableQuestions.size());
        return questionRepository.getQuestionByCoords(getQuestionCoords(index));
    }

    @Override
    public Question getRandomQuestion(Integer categoryIndex) {
        List<QuestionCoords> availableQuestionsWithCategory = getQuestionCoordsWithGivenCategory(categoryIndex);
        if(availableQuestionsWithCategory.isEmpty()) {
            throw new UnableToDrawQuestionException();
        }
        int index = random.nextInt(availableQuestionsWithCategory.size());
        return questionRepository.getQuestionByCoords(getQuestionCoords(index));
    }

    @Override
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
        IntStream.range(BEGIN_INDEX, questionRepository.getNumberOfQuestionInCategory(categoryIndex))
        .forEach(questionIndex -> availableQuestions.add(new QuestionCoords(categoryIndex, questionIndex)));
    }
}
