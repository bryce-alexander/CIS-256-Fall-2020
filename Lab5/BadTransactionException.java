/* BadTransactionException.java */

/**
 * Implements an exception that should be thrown for nonexistent accounts.
 **/
public class BadTransactionException extends Exception {

	public int depositAmount; // The invalid account number.

	/**
	 * Creates an exception object for nonexistent account "badAcctNumber".
	 **/
	public BadTransactionException(int invalidDepositAmount) {
		super("Invalid amount: " + invalidDepositAmount);

		depositAmount = invalidDepositAmount;
	}
}