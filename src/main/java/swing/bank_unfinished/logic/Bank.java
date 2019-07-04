package swing.bank_unfinished.logic;

import java.util.ArrayList;
import java.util.List;

public class Bank {

    private List<Customer> custList = new ArrayList<>();
    private List<Account> accList = new ArrayList<>();
    private Integer lastCustID = 0;
    private Integer lastAccID = 100;

    // Public API
    public Customer newCustomer(String firstName,
                                String lastName,
                                String email) {
        Customer cust = new Customer(lastCustID++, firstName,
                lastName, email);
        custList.add(cust);
        return cust;
    }

    public Account newAccount(Customer cust, String accType) {
        Account acc;
        switch (accType) {
            case "Savings":
                acc = new SavingsAccount(lastAccID++, 0.0, cust);
                break;
            case "Debit":
                acc = new DebitAccount(lastAccID++, 0.0, cust);
                break;
            default:
                acc = new CheckingAccount(lastAccID++, 0.0, cust);
        }
        accList.add(acc);
        return acc;
    }

    public Account findAccountByID(Integer accID) throws NonExistingAccountException {
        for (Account acc : accList) {
            if (acc.getAccountID().equals(accID))
                return acc;
        }
        throw new NonExistingAccountException(accID);
    }

    public void transfer(Integer fromAccID, Integer toAccID, Double amount) throws NotEnoughMoneyException, NonExistingAccountException {
        // dodana tresc metody i rzucanie wyjatku w przypadku braku pieniedzy na koncie
        Account fromAcc = findAccountByID(fromAccID);
        Account toAcc = findAccountByID(toAccID);
        fromAcc.charge(amount);
        toAcc.deposit(amount);
    }

    // metoda dodana na potrzeby zadania 7
    //przegladam konta i sprawdzam które posiada customera z tym samym id ktory ma ten customer
    // zwracam liste takich accountow
    public List<Account> findAccountsByCustomer(Customer customer) {
        List<Account> accounts = new ArrayList<>();
            for (Account account : accList) {
                if (customer.getCustomerID().equals(account.getCustomer().getCustomerID())) {
                    accounts.add(account);
                }
        }
        return accounts;
    }



    @Override
    public String toString() {
        return "Bank{" +
                "custs=\n" + custList +
                ",\naccs=\n" + accList +
                '}';
    }
}
