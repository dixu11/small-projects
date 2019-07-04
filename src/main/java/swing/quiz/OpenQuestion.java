package swing.quiz;

public class OpenQuestion extends Question {

  private  Answer answer;

    public OpenQuestion(int id, String discription, Category category, Answer answer) {
        super(id, discription, category);
        this.answer = answer;
    }

    @Override
    public boolean isAnswerCorrent(String userAnswer) {
        if (userAnswer != null) {
            userAnswer = userAnswer.toLowerCase();
        }
        if (userAnswer.equals(answer.getDescription().toLowerCase())) {
            System.out.println("Answer is correct");
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return discription + "\n";
    }
}
