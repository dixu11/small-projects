package swing.bank_unfinished.gui;

import swing.bank_unfinished.logic.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AccountManagerUI {
    private JPanel mainPane;
    private JTable mainTable;
    private JPanel RightPanel;
    private JPanel LeftPanel;
    private JLabel nameLabel;
    private JLabel lastNTable;
    private JLabel mailLabel;
    private JTextField nameTxt;
    private JTextField lastNTxt;
    private JTextField mailTxt;
    private JTextField IdTxt;
    private JButton saveBt;
    private JButton prevBt;
    private JButton nextBt;
    private JButton deleteBt;
    private JButton goToAccountActivitiesButton;
    private JButton addNewCustomerButton;
    private JLabel amountLbl;
    private JTextField amountTxt;
    private JLabel receiverLbl;
    private JTextField receiverIdTxt;
    private JButton transferBtn;
    private int actualAccountID;
    private Account actualAccount;

    private Bank bank;

    // obsluga bledu ktory nie ma prawa wystapic przerzucona do throws
    public AccountManagerUI(Bank bank) throws NonExistingAccountException {
        this.bank = bank;

       // nie mamy logiki odpowiedzialnej za wyswietlanie id aktualnie przegladanego konta
        // dlatego ta informacja jest umieszczona w kodzie na sztywno - do zmiany wraz z rozwojem aplikacji
        actualAccountID = 100;

        actualAccount = bank.findAccountByID(actualAccountID);

        //kod odpowiedzialny za dodanie dzialania przycisku
        transferBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // przygotowanie wprowadzonych przez usera danych
                String amoundInput = amountTxt.getText();
                String receiverInput = receiverIdTxt.getText();

                // obsluguje sytuacje w ktorych zostawiono puste pola - konczymy dzialanie wyswietlamy komunikat bledu
                if (amoundInput.equals("")|| receiverInput.equals("")) {
                    JOptionPane.showMessageDialog(null,"Fields amount and receiver cannot be empty!");
                    return;
                }

                // przygotowuje pola na wyciagniete typy z tekstu
                double amound = 0;
                int recId = 0;
                try {
                    // probuje parsowac tekst do odpowiednich typow - to moze spowodowac blad NumberFormatE ktory lapie i obsluguje
                    amound = Double.parseDouble(amoundInput);
                    recId = Integer.parseInt(receiverInput);

                    // jesli do tego momentu nie wystapil blad to odpalam transfer
                    // transfer moze spowodowac 2 bledy ktore obsluguje
                    bank.transfer(actualAccountID, recId, amound);
                    JOptionPane.showMessageDialog(null, "Operation succesfull! Have a nice day!");
                } catch (NumberFormatException e2) {
                    JOptionPane.showMessageDialog(null, "Fields amount and receiver must contain numbers");
                }catch (NotEnoughMoneyException e3) {
                    JOptionPane.showMessageDialog(null,"Not enough money on account! Ballance: "+ e3.getBalance()
                    + "\nSearching your other accounts...");

                    // w przypadku zbyt malej ilosci pieniedzy na koncie chcemy przeszukac inne konta czy moze z nich mozna zrobic przelew
                    // poniewaz ta czesc zawiera dodatkowy blok try catch (metoda transfer wymusza obsluge bledu) strasznie pogarsza to
                    // czytelnosc kodu - wyciagam ja więc do osobnej metody i ta w ktorej jestesmy wyglada o niebo czytelniej
                    transferToOtherAccounts(recId,amound);


                } catch (NonExistingAccountException e4) {
                    JOptionPane.showMessageDialog(null,"No existing account! ID: "+ e4.getAccountID());
                }
            }
        });
    }


    public void transferToOtherAccounts(int recId, double amound) {
        // znajduke konta po aktualnym customerze
        List<Account> accounts = bank.findAccountsByCustomer(actualAccount.getCustomer());

        //przegladam konta w poszukiwaniu konta ktore ma wieksza lub rowna kwote jak ta ktora chce przelac
        for (Account account : accounts) {
            if (account.getBalance().doubleValue() >= amound) {

                // znalazlem takie konto wiec wyswietlam zapytanie czy user chce przelac pieniadze z tego konkretnego innego konta
                int answer = JOptionPane.showConfirmDialog(null, "You have enough money on account " +
                        account.getAccountID() + " ! Would you like to transfer money from this account?");

                //interesuje mnie tylko sytuacja w ktorej menu wyboru zwrocilo 0 -> to oznacza ze user sie zgodzil
                if (answer == 0) {
                    try {
                        bank.transfer(account.getAccountID(), recId, amound);
                        JOptionPane.showMessageDialog(null, "Operation succesfull! Have a nice day!");
                    } catch (Exception ee) {
                        //try catch blok wymuszony przez ponowne wywołanie metody transfer
                    }

                    // jesli user sie zgodzil konczymy metode i tym samym iteracje -> robota metody zostala wykonana
                    return;
                }

            }
        }
    }



    public static void main(String[] args) throws NonExistingAccountException {
        // ponieważ zgodnie z zadaniem zaimlementowalem tylko funkcje transferu - aby ja przetestowac
        // najpier odpalam z kodu symulacje ktora pozwoli mi jej użyć
        Bank bank = new Bank();
        symulateOperations(bank);

        // kod automatycznie wygenerowany do uruchomienia aplikacji
        JFrame frame = new JFrame("AccountManagerUI");
        frame.setContentPane(new AccountManagerUI(bank).mainPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void symulateOperations(Bank bank) {
        Customer c1 = bank.newCustomer("John",
                "Brown", "john@brown.com");
        Customer c2 = bank.newCustomer("Anna",
                "Smith", "anna@smith.com");
        Account a1 = bank.newAccount(c1, "Debit");

        Account a2 = bank.newAccount(c1, "Savings");
        Account a3 = bank.newAccount(c1, "");
        Account a4 = bank.newAccount(c2, "Debit");
        Account a5 = bank.newAccount(c2, "Savings");
        a1.deposit(150.0);
        a2.deposit(1000.0);
        a3.deposit(200.0);
    }
}
