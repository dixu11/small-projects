package swing.quiz;

import java.util.List;

public class ClosedQuestion extends Question {

   private List<Answer> answers;

   public ClosedQuestion(int id, String discription, Category category, List<Answer> answers) {
      super(id, discription, category);
      this.answers = answers;
   }


   public boolean isAnswerCorrent(String userAnswer) {
      String letters = "ABCD";
      char chosenChar;
      int answerIndex = -1;
      if (userAnswer != null && userAnswer.length() == 1) {
         userAnswer = userAnswer.toUpperCase();
         chosenChar = userAnswer.charAt(0);
         answerIndex = letters.indexOf(chosenChar);
      }
      if (answerIndex != -1 && answerIndex<answers.size() &&  answers.get(answerIndex).isCorrect()) {
         System.out.println("Odpowiedz poprawna");
         return true;
      }
      return false;
   }



   public String toString() {
      String letters = "ABCD";
      String allAnswers = "";
      for (int i = 0 ; i<answers.size(); i++) {
         allAnswers +=   letters.charAt(i) +   " : " +   answers.get(i) + "\n";
      }
      return discription + "\n\n" + allAnswers;
   }


}
