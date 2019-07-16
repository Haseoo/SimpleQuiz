package gamecore.questions;

final public class Category {
    private int id,
                numberOfQuestions;

    private String  name,
                    filePath;
    public Category(int id, String name, String filePath) {
        this.id = id;
        this.name = name;
        this.filePath = filePath;
    }

    public String getName() {
        return name;
    }

    public String getFilePath() {
        return filePath;
    }

    public int getID() {
        return id;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }


    @Override
    public boolean equals(Object other) {
        if(this.getClass() != other.getClass()) {
            return false;
        }

        Category otherObject = (Category)other;

        return  (otherObject.id == this.id);
    }

    @Override
    public int hashCode() {
        return super.hashCode() + id;
    }
}
