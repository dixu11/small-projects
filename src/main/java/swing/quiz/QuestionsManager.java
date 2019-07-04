package swing.quiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class QuestionsManager {
    private QuestionsDatabase questionsDatabase;
    private Question currentQuestion;
    private Category currentCategory;
    private List<Category> expiredCategories;
    private List<Question> currentQuestions;
    private int points;
    private int tries;

    public QuestionsManager(QuestionsDatabase questionsDatabase) {
        this.questionsDatabase = questionsDatabase;

        QuestionsFileAccess questionsFileAccess = new QuestionsFileAccess("questions");
        QuestionsBuilder questionsBuilder = new QuestionsBuilder(questionsDatabase, questionsFileAccess);
        questionsBuilder.populateLocalDatabase();
        prepareManager();
    }

    public void prepareManager() {
        currentCategory = Category.MUSIC; // ustawiam domyslna
        expiredCategories = new ArrayList<>();
        populate();
        points = 0;
        tries = 3;
    }

    public void populate() {
        currentQuestions = new ArrayList<>();
        List<Question> questions = questionsDatabase.getQuestions();
        for (Question question : questions) {
            if (question.getCategory() == currentCategory) {
                currentQuestions.add(question);
            }
        }
    }

    public void printCurrentQuestions() {
        for (Question question : currentQuestions) {
            System.out.println(question);
        }
    }

    public void changeCategory(Category category) {
        if (category != currentCategory) {
            System.out.println("Category changed to: " + category.toString().toLowerCase());
            currentCategory = category;
            populate();
        }
    }

    public boolean checkCategory() {
        //1. zmienia kategorie gdy skoncza sie pytania
        //2. zwraca flage true gdy skoncza sie kategorie
        if (currentQuestions.size() == 0) {
            expiredCategories.add(currentCategory);
            for (Category category : Category.values()) {
                if (!expiredCategories.contains(category)) {
                    changeCategory(category);
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public String getQuestion() {
        Random random = new Random();
        int questionIdex = random.nextInt(currentQuestions.size());
        currentQuestion = currentQuestions.get(questionIdex);
        return currentQuestion.toString();
    }

    // wersja do trybu konsolowego
    public String askUser() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public boolean isCorrect(String userAnswer) {
        if (currentQuestion.isAnswerCorrent(userAnswer)) {
            points++;
            System.out.println("Correct answer!\n");
            return true;
        } else {
            if (points >0) {
                points--;
            }
            tries--;
            System.out.println("Wrong answer..\n");
            return false;
        }
    }


    public void printIntro() {
        System.out.println("Game started!");
        System.out.println("Default category: " + currentCategory.toString().toLowerCase());
        System.out.println();
    }

    public void printPlayerData() {
        System.out.println("Point: " + points);
        System.out.println("Tries: " + tries);
        System.out.println();
    }

    public void printEndGameQuestion() {
        System.out.println("Do you wan't to play game again? Y/N\n");
    }

    public void printEndGameMessage() {
        if (tries < 1) {
            System.out.println("Game over!");
        } else {
            System.out.println("Congratulations! Game won!");
            int extraPoint = tries * 3;
            System.out.println("You got: " + extraPoint + " extra points!");
            points += extraPoint;
        }
        printPlayerData();
    }

    public boolean repeat(String decision) {
        char chosenChar = 'N';
        if (decision != null && decision.length() == 1) {
            decision = decision.toUpperCase();
            chosenChar = decision.charAt(0);
        }
        if (chosenChar == 'Y') {
            prepareManager();
            return true;
        }
        return false;
    }

    public boolean isQuestionOpen() {
        if (currentQuestion instanceof OpenQuestion) {
            return true;
        } else {
            return false;
        }
    }

    public String generateEndGameScore() { ;
            int extraPoint = tries * 3;
           String message = "You got: " + extraPoint + " extra points!";
            points += extraPoint;
        message += "\nYour final score is: " + points;
        return message;
}


    public void removeQuestion() {
        currentQuestions.remove(currentQuestion);
    }

    public int getPoints() {
        return points;
    }

    public int getTries() {
        return tries;
    }

    public Category getCurrentCategory() {
        return currentCategory;
    }
}
