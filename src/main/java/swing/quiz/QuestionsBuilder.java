package swing.quiz;

import java.util.Scanner;

public class QuestionsBuilder {
    private QuestionsDatabase questionsDatabase;
    private QuestionsFileAccess questionsFileAccess;

    public QuestionsBuilder(QuestionsDatabase questionsDatabase, QuestionsFileAccess questionsFileAccess) {
        this.questionsDatabase = questionsDatabase;
        this.questionsFileAccess = questionsFileAccess;
    }

    public void populateLocalDatabase() {
        String content = questionsFileAccess.getFileContent();
        String[] questionsAndAnswersRaw = content.split("-----");

        if (questionsAndAnswersRaw.length == 2) {
            prepareAnswers(questionsAndAnswersRaw[1]);
            PrepareQuestions(questionsAndAnswersRaw[0]);
        } else {
            System.out.println("Questions file corrupted -> i can't split file text into questions and answers!");
        }
    }

    private void PrepareQuestions(String questionsString) {
        Scanner scanner = new Scanner(questionsString);
        String rawQuestion = "";
        while (scanner.hasNextLine()) {
            String question = scanner.nextLine();
            String[] questionParts = question.split("#");
            if (questionParts.length == 4) {
                int id = Integer.valueOf(questionParts[0]);
                Category category = Category.valueOf(questionParts[1]);
                boolean isOpen = false;
                if (questionParts[2].equals("t")) {
                    isOpen = true;
                }
                String description = questionParts[3];
                questionsDatabase.addQuestion(id,description,category,isOpen);
            }
        }
    }

    private void prepareAnswers(String answersString) {
        Scanner scanner = new Scanner(answersString);
        String rawAnswer = "";
        while (scanner.hasNextLine()) {
            String answer = scanner.nextLine();
            String[] answerParts = answer.split("#");
            if (answerParts.length == 3) {
                int connectedQuestion = Integer.valueOf(answerParts[0]);
                boolean isCorrect = false;
                if (answerParts[1].equals("t")) {
                    isCorrect = true;
                }
                String description = answerParts[2];
              //  System.out.println("Zaraz zostanie dodane..." + description);
               questionsDatabase.addAnswer(description,isCorrect,connectedQuestion);
            }
        }
    }
}
