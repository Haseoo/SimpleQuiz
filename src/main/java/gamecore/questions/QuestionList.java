package gamecore.questions;
import java.util.Vector;

public class QuestionList {
    private Vector <Question> list;

    public QuestionList(String categoryFilePath) {
        list = new Vector <Question>();
    }

    Question getNewQuestion() {
        return null;
    }

    void addQuestion(int qno, int cno) {
        Question newQestion = new Question(cno, qno);
        int ind = list.indexOf(newQestion);
        System.out.println(ind);
        if (ind == -1) { 
            list.add(newQestion);
        }
        else {
            System.out.println("juz bylo!");
        }

    }

    public static void main(String args[]) {
        QuestionList ql = new QuestionList("xd");
        ql.addQuestion(1, 2);
        ql.addQuestion(5, 3);
        ql.addQuestion(1, 2);
    }

}