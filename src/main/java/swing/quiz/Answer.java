package swing.quiz;

public class Answer {
    private String description;
    private boolean isCorrect;
    private int connectedQuestion;

    public Answer(String description, boolean isCorrect, int connectedQuestion) {
        this.description = description;
        this.isCorrect = isCorrect;
        this.connectedQuestion = connectedQuestion;
    }

    public String toString() {
        return description;
    }

    public int getConnectedQuestion(){
        return connectedQuestion;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public String getDescription() {
        return description;
    }
}
