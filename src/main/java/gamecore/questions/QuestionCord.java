package gamecore.questions;

public class QuestionCord {
    private int numberOfCategory,
                numberOfQuestion;

    public QuestionCord(int numberOfCategory, int numberOfQuestion) {
        this.numberOfCategory = numberOfCategory;
        this.numberOfQuestion = numberOfQuestion;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        QuestionCord ques2 = (QuestionCord)obj;

        return (this.numberOfCategory == ques2.numberOfCategory && this.numberOfQuestion == ques2.numberOfQuestion);
    }

    @Override
    public int hashCode() {
        return 3 * numberOfCategory + 7 * numberOfQuestion;
    }

    public int getNumberOfQuestion() {
        return numberOfQuestion;
    }

    public int getNumberOfCategory() {
        return numberOfCategory;
    }

    public static void main(String args[]) {
        QuestionCord q1 = new QuestionCord(1,2),
                     q2 = new QuestionCord(1,2);
        System.out.println(q1.equals(q2));
    }
}