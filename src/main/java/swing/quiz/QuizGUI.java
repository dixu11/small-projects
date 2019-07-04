package swing.quiz;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizGUI extends JFrame {
    private JPanel mainPanel;
    private JTextArea questionsPrinter;
    private JPanel ButtonsPanel;
    private JPanel StatsPanel;
    private JButton buttonA;
    private JButton buttonB;
    private JButton buttonC;
    private JButton buttonD;
    private JTextField openQInput;
    private JButton buttonOpen;
    private JLabel livesLbl;
    private JLabel pointsLbl;
    private JLabel categoryLbl;
    private JTextField livesField;
    private JTextField pointsField;
    private JTextField categoryField;
    private QuestionsManager questionsManager;


    public QuizGUI(QuestionsManager questionsManager) {
        this.questionsManager = questionsManager;
        showQuestion();
        showStatistics();
        buttonA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeAnswer("a");
            }
        });
        buttonB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeAnswer("b");
            }
        });
        buttonC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeAnswer("c");
            }
        });
        buttonD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeAnswer("d");
            }
        });
        buttonOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeAnswer(openQInput.getText());
                openQInput.setText("");
            }
        });
        openQInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeAnswer(openQInput.getText());
                openQInput.setText("");
            }
        });
    }

    public void showQuestion() {
       String question = questionsManager.getQuestion();
        questionsPrinter.setText(question);
        setQuestionMode();

    }

    public void setQuestionMode() {
        if (questionsManager.isQuestionOpen()) {
            buttonA.setEnabled(false);
            buttonB.setEnabled(false);
            buttonC.setEnabled(false);
            buttonD.setEnabled(false);
            buttonOpen.setEnabled(true);
            openQInput.setEnabled(true);
        } else {
            buttonA.setEnabled(true);
            buttonB.setEnabled(true);
            buttonC.setEnabled(true);
            buttonD.setEnabled(true);
            buttonOpen.setEnabled(false);
            openQInput.setEnabled(false);
        }
    }

    public void nextQuestion() {
        questionsManager.removeQuestion();
        boolean isGameEnded = questionsManager.checkCategory();
        if (isGameEnded || questionsManager.getTries() == 0) {
            endGame();
        }
        showQuestion();
        showStatistics();
    }

    public void showStatistics() {
        livesField.setText( Integer.toString(questionsManager.getTries()) );
        pointsField.setText(Integer.toString(questionsManager.getPoints()));
        categoryField.setText(questionsManager.getCurrentCategory().toString());
    }

    public void executeAnswer(String answer) {
        boolean isCorrect = questionsManager.isCorrect(answer);
        if (isCorrect) {
            JOptionPane.showMessageDialog(null, "Correct answer!");
        } else {
            JOptionPane.showMessageDialog(null, "Wrong answer...");
        }
        nextQuestion();
    }

    public void endGame() {
        if (questionsManager.getTries() > 0) {
            JOptionPane.showMessageDialog(null, "You won! Congratulations!");
        } else {
            JOptionPane.showMessageDialog(null, "You lost... 0 lives left");
        }
        String msg = questionsManager.generateEndGameScore();
        JOptionPane.showMessageDialog(null, msg);
        int decision = JOptionPane.showConfirmDialog(null, "Do you want to play again?");

        if (decision == 0) {
            questionsManager.repeat("y");
        } else {
            dispose();
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        // przygotowanie pytan z pliku
        QuestionsFileAccess questionsFileAccess = new QuestionsFileAccess("questions");
        QuestionsDatabase questionsDatabase = new QuestionsDatabase();
        QuestionsBuilder questionsBuilder = new QuestionsBuilder(questionsDatabase, questionsFileAccess);

        //przygotowanie managera
        QuestionsManager questionsManager = new QuestionsManager(questionsDatabase);

        JFrame frame = new JFrame("QuizGUI");
        frame.setContentPane(new QuizGUI(questionsManager).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
