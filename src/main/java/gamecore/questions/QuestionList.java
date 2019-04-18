package gamecore.questions;
import java.util.Stack;

import java.util.Random;

public class QuestionList {
    private Stack <QuestionCord> list;
    private Random random;

    public QuestionList() {
        list = new Stack<>();
        random = new Random();
    }

    public void newQuestion() throws QuestionGenerationNotPossible {
        QuestionCord tmp;
        boolean result = false;
        RandomLoop:
        for (int i = 0; i < CategoriesList.getTotalNumberOfQuestions(); i++){
            int categoryNo = random.nextInt(CategoriesList.getNumberOfCategories());
            int questionNo = random.nextInt(CategoriesList.getNumberOfQuestions(categoryNo));
            tmp = new QuestionCord(categoryNo, questionNo);
            if (list.indexOf(tmp) == -1) {
                list.add(tmp);
                result = true;
                break RandomLoop;
            }
        }
        if (!result) {
            throw new QuestionGenerationNotPossible("An attempt to generate more questions than possible.");
        }
    }

    public boolean newQuestion(int categoryNo) throws QuestionGenerationNotPossible {
        QuestionCord tmp;
        boolean result = false;
        RandomLoop:
        for (int i = 0; i < CategoriesList.getTotalNumberOfQuestions(); i++){
            int questionNo = random.nextInt(CategoriesList.getNumberOfQuestions(categoryNo));
            tmp = new QuestionCord(categoryNo, questionNo);
            if (list.indexOf(tmp) == -1) {
                list.add(tmp);
                result = true;
                break RandomLoop;
            }
        }
        if (!result) {
            String exceptionLabel = String.format("An attempt to generate more questions than possible from category %s.",
                                                  CategoriesList.getCategoryName(categoryNo));
            throw new QuestionGenerationNotPossible(exceptionLabel);
        }
        return result;
    }

    QuestionCord getLastQuestion() {
        return list.peek();
    }

    public static void main(String args[]) {
        CategoriesList.initList();
        QuestionList ql = new QuestionList();

        try {
            for (int i = 0; i < 9; i++ ) {
                ql.newQuestion();
                System.out.printf("%d, %d\n", ql.getLastQuestion().getNumberOfCategory(), ql.getLastQuestion().getNumberOfQuestion());
            }
            ql.newQuestion();
            System.out.printf("%d, %d\n", ql.getLastQuestion().getNumberOfCategory(), ql.getLastQuestion().getNumberOfQuestion());
        } catch(Exception e) {
            System.out.println(e);
        }
    }

}