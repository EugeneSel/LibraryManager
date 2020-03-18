package exception;

public class DaoException extends Exception {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	public DaoException() {
		super();
	}
	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public DaoException(final String message, final Throwable cause) {
		super(message, cause);
	}
	/**
	 * 
	 * @param message
	 */
	public DaoException(final String message) {
		super(message);
	}
}