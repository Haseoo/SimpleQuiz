package gamecore.questions;

public class Question{
    private int numberOfCategory,
                numberOfQuestion;

    public Question(int numberOfCategory, int numberOfQuestion) {
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

        Question ques2 = (Question)obj;

        return (this.numberOfCategory == ques2.numberOfCategory && this.numberOfQuestion == ques2.numberOfQuestion);
    }

    public int getNumberOfQueston() {
        return numberOfQuestion;
    }

    public int getNumberOfCategory() {
        return numberOfCategory;
    }

    public static void main(String args[]) {
        Question q1 = new Question(1,2),
                 q2 = new Question(1,2);
        System.out.println(q1.equals(q2));
    }
}