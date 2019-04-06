package gamecore.questions;
import java.util.Stack;

public class QuestionList {
    private Stack <QuestionCord> list;

    public QuestionList() {
        list = new Stack<>();
    }

    void addQuestion(int qno, int cno) {
        QuestionCord newQestion = new QuestionCord(cno, qno);
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
        QuestionList ql = new QuestionList();
        ql.addQuestion(1, 2);
        ql.addQuestion(5, 3);
        ql.addQuestion(1, 2);
    }

}