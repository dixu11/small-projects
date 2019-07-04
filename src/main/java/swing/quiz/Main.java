package swing.quiz;// klasa do testowania aplikacji

public class Main {



    public static void main(String[] args) {
        // old main for version without GUI



    /*    QuestionsFileAccess questionsFileAccess = new QuestionsFileAccess("questions");
        QuestionsDatabase questionsDatabase = new QuestionsDatabase();
        QuestionsBuilder questionsBuilder = new QuestionsBuilder(questionsDatabase, questionsFileAccess);
        questionsBuilder.populateLocalDatabase();
*/

        //prepareDatabase();

   /*     // przygotowanie pytan z pliku
        QuestionsFileAccess questionsFileAccess = new QuestionsFileAccess("questions");
        QuestionsDatabase questionsDatabase = new QuestionsDatabase();
        QuestionsBuilder questionsBuilder = new QuestionsBuilder(questionsDatabase, questionsFileAccess);

        //przygotowanie managera
        QuestionsManager questionsManager = new QuestionsManager(questionsDatabase);
        questionsManager.printIntro();
        boolean gameNotEnded = true;

        //petla gry
        while (gameNotEnded) {
            while (questionsManager.getTries() > 0 && questionsManager.getPoints() < 7) {
                questionsManager.printPlayerData();
                boolean gameEnded = questionsManager.checkCategory();
                if (gameEnded) {
                    break;
                }
                System.out.println(questionsManager.getQuestion());
                String odpowiedz = questionsManager.askUser();
                questionsManager.isCorrect(odpowiedz);
                questionsManager.removeQuestion();
            }
            questionsManager.printEndGameMessage();
            questionsManager.printEndGameQuestion();
            String lastAnswer = questionsManager.askUser();
            gameNotEnded = questionsManager.repeat(lastAnswer);
        }*/
    }


   /* public static void prepareDatabase() {
        questionsDatabase.addAnswer("odpowiedz1", true, 1);
        questionsDatabase.addAnswer("odpowiedz2", false, 1);
        questionsDatabase.addAnswer("odpowiedz3", false, 1);
        questionsDatabase.addAnswer("odpowiedz4", false, 1);

        questionsDatabase.addAnswer("odpowiedz1b", false, 2);
        questionsDatabase.addAnswer("odpowiedz2b", false, 2);
        questionsDatabase.addAnswer("odpowiedz3b", true, 2);
        questionsDatabase.addAnswer("odpowiedz4b", false, 2);

        questionsDatabase.addAnswer("odpowiedz1c", false, 3);
        questionsDatabase.addAnswer("odpowiedz2c", false, 3);
        questionsDatabase.addAnswer("odpowiedz3c", false, 3);
        questionsDatabase.addAnswer("odpowiedz4c", true, 3);

      *//*  questionsDatabase.addAnswer("odpowiedz1d", false, 4);
        questionsDatabase.addAnswer("odpowiedz2d", false, 4);
        questionsDatabase.addAnswer("odpowiedz3d", false, 4);
        questionsDatabase.addAnswer("odpowiedz4d", true, 4);

        questionsDatabase.addAnswer("odpowiedz1e", false, 5);
        questionsDatabase.addAnswer("odpowiedz2e", true, 5);

        questionsDatabase.addAnswer("odpowiedz1f", true, 6);
        questionsDatabase.addAnswer("odpowiedz2f", false, 6);

        questionsDatabase.addAnswer("odpowiedz1g", false, 7);
        questionsDatabase.addAnswer("odpowiedz2g", true, 7);

        questionsDatabase.addAnswer("odpowiedz1h", false, 8);
        questionsDatabase.addAnswer("odpowiedz2h", true, 8);

        questionsDatabase.addAnswer("odpowiedz1i", false, 9);
        questionsDatabase.addAnswer("odpowiedz2i", true, 9);
        questionsDatabase.addAnswer("odpowiedz3i", false, 9);*//*

      questionsDatabase.addAnswer("kot",true,10);
      questionsDatabase.addAnswer("KOT",true,11);
      questionsDatabase.addAnswer("Kot",true,12);

        questionsDatabase.addQuestion(1, "pytanie1", Category.BIOLOGY, false);
        questionsDatabase.addQuestion(2, "pytanie2", Category.CHAMISTRY, false);
        questionsDatabase.addQuestion(3, "pytanie3", Category.COMPUTER_SIENCE, false);
       *//* questionsDatabase.addQuestion(4, "pytanie4", Category.BIOLOGY, false);
        questionsDatabase.addQuestion(5, "pytanie5", Category.CHAMISTRY, false);
        questionsDatabase.addQuestion(6, "pytanie6", Category.CHAMISTRY, false);
        questionsDatabase.addQuestion(7, "pytanie7", Category.COMPUTER_SIENCE, false);
        questionsDatabase.addQuestion(8, "pytanie8", Category.COMPUTER_SIENCE, false);
        questionsDatabase.addQuestion(9, "pytanie9", Category.BIOLOGY, false);*//*
       questionsDatabase.addQuestion(10,"pytanie otwarte1", Category.BIOLOGY,true);
       questionsDatabase.addQuestion(11,"pytanie otwarte2", Category.CHAMISTRY,true);
       questionsDatabase.addQuestion(12,"pytanie otwarte3", Category.COMPUTER_SIENCE,true);
    }*/
}
