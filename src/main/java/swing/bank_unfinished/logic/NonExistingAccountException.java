package swing.bank_unfinished.logic;

public class NonExistingAccountException extends BankException {
        private Integer accountID;

        public NonExistingAccountException(Integer accountID) {
            super("No such account exists, requested account ID: " + accountID);
            this.accountID = accountID;
        }

        public Integer getAccountID() {
            return accountID;
        }
}
