package gamecore.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.questions.ReadingQuestionFromFileException;
import exceptions.repositories.RepositoryInitalizationException;
import exceptions.repositories.UninitializedRepository;
import gamecore.models.CategoryModel;
import gamecore.projections.questions.Category;
import gamecore.projections.questions.Question;
import gamecore.projections.questions.QuestionCoords;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static utility.Constants.CATEGORIES_LIST_FILE_PATH;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QuestionRepository {
    private static Category[] categories;
    public static void initRepository() throws RepositoryInitalizationException{
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            CategoryModel[] categoryModels = objectMapper.readValue(new File(CATEGORIES_LIST_FILE_PATH), CategoryModel[].class);
            categories = Arrays.stream(categoryModels).map(Category::new).toArray(Category[]::new);
        }catch (IOException | ReadingQuestionFromFileException e) {
            throw new RepositoryInitalizationException(e);
        }
    }
    public static Question getQuestionByCoords(QuestionCoords questionCoords) {
        if (categories == null) {
            throw new UninitializedRepository();
        }
        return categories[questionCoords.getCategoryNo()]
                .getQuestions()[questionCoords.getQuestionNo()];
    }

    public static void main(String...args)throws IOException{
        initRepository();
        Question q = getQuestionByCoords(new QuestionCoords(0, 2));
        log.info(q.getContent());
    }
}