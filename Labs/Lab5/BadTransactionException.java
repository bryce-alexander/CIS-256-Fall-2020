/* BadAccountException.java */

/**
 *  Implements an exception that should be thrown for nonexistent accounts.
 **/
public class BadTransactionException extends Exception {

  public int amount;  // The invalid account number.

  /**
   *  Creates an exception object for nonexistent account "badAcctNumber".
   **/
  public BadTransactionException(int inputAmount) {
    super("Invalid transaction amount: " + inputAmount);

    amount = inputAmount;
  }
}
