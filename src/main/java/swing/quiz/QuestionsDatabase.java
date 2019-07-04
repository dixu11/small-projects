package swing.quiz;// baza danych z pytaniami i odpowiedziami

import java.util.ArrayList;
import java.util.List;

public class QuestionsDatabase {
    private List<Answer> answers;
    private List<Question> questions;

    public QuestionsDatabase() {
        answers = new ArrayList<>();
        questions = new ArrayList<>();
    }


    public void addAnswer(String description, boolean isCorrect, int connectedQuestion) {
        answers.add(new Answer(description, isCorrect, connectedQuestion));
    }

    public void addQuestion(int id, String discription, Category category, boolean isOpen) {
        if (isOpen) {
            Answer concreteAnswer = null;
            for (Answer answer : answers) {
                if (answer.getConnectedQuestion() == id) {
                    concreteAnswer = answer;
                    break;
                }
            }
            questions.add(new OpenQuestion(id, discription, category, concreteAnswer ));
        } else {
            List<Answer> concreteAnswers = new ArrayList<>();
            for (Answer answer : answers) {
                if (answer.getConnectedQuestion() == id) {
                    concreteAnswers.add(answer);
                }
            }
            questions.add(new ClosedQuestion(id, discription, category, concreteAnswers));
        }
    }


    public List<Question> getQuestions() {
        return questions;
    }

    public List<Answer> getAnswers() {
        return answers;
    }


    public void printAllAnswers() {
        for (Answer answer : answers) {
            System.out.println(answer);
        }
    }

    public void printAllQuestions() {
        for (Question question : questions) {
            System.out.println(question);
        }
    }

}
