public class Question{
    private int numberOfCathegory,
                numberOfQuestion;

    public Question(int numberOfCathegory, int numberOfQuestion) {
        this.numberOfCathegory = numberOfCathegory;
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

        return (this.numberOfCathegory == ques2.numberOfCathegory && this.numberOfQuestion == ques2.numberOfQuestion);
    }

    public int getNumberOfQueston() {
        return numberOfQuestion;
    }

    public int getNumberOfCathegory() {
        return numberOfCathegory;
    }

    public static void main(String args[]) {
        Question q1 = new Question(1,2),
                 q2 = new Question(1,2);
        System.out.println(q1.equals(q2));
    }
}