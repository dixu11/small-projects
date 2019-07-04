package swing.bank_unfinished.logic;

import javax.swing.*;

public class PlayWithBank {

    // do projektu dodano klasy wyjatkow i kont
    //  zaimplementowano transfer, obsłużono wyjątek dot. braku pieniedzy i dot. nie znalezienia konta

    public static void main(String[] args) {
        Bank bank = new Bank();
        Customer c1 = bank.newCustomer("John",
                "Brown", "john@brown.com");
        Customer c2 = bank.newCustomer("Anna",
                "Smith", "anna@smith.com");
        Account a1 = bank.newAccount(c1, "Debit");
        System.out.println(a1);
        Account a2 = bank.newAccount(c1, "Savings");
        Account a3 = bank.newAccount(c1, "");

        Account a4 = bank.newAccount(c2, "Debit");
        Account a5 = bank.newAccount(c2, "Savings");
        a1.deposit(150.0);
        a2.deposit(100.0);


        // Transfer 50.00 from accID 100 to 102
        try {
            a2.charge(25.0);
            bank.transfer(100, 102, 160.00); // na koncie mamy 150 a chcemy przelac 150 - to powoduje wystapienie wyjatku
            // aby sprawdzić złapanie drugiego błędu można ustawić ID np na 99
        } catch (NotEnoughMoneyException e) {
            JOptionPane.showMessageDialog(null,"Not enough money on account! Ballance: "+ e.getBalance());
        } catch (NonExistingAccountException e) {
            JOptionPane.showMessageDialog(null,"No existing account! ID: "+ e.getAccountID());
        }
        System.out.println(bank);

    }
}
