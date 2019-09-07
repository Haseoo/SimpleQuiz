package gamecore.repositories;

import exceptions.repositories.RepositoryInitalizationException;
import gamecore.projections.questions.Question;
import gamecore.projections.questions.QuestionCoords;
import gamecore.utility.GlobalQuestionRepository;

public class QuestionRepository implements IQuestionRepository{
    public QuestionRepository() throws RepositoryInitalizationException {
        GlobalQuestionRepository.initRepository();
    }

    @Override
    public Question getQuestionByCoords(QuestionCoords questionCoords) {
        return GlobalQuestionRepository.getQuestionByCoords(questionCoords);
    }

    @Override
    public Integer getNumberOfCategories() {
        return GlobalQuestionRepository.getNumberOfCategories();
    }

    @Override
    public Integer getNumberOfQuestionInCategory(Integer categoryIndex) {
        return GlobalQuestionRepository.getNumberOfQuestionInCategory(categoryIndex);
    }
}
