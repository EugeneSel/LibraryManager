package exception;

public class DaoException extends Exception {
	public DaoException() {
		super();
	}

	public DaoException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public DaoException(final String message) {
		super(message);
	}
}