package exception;



public class ServiceException extends Exception {
	
	/**
     *
     */
    private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
    public ServiceException() {
		super();
	}
	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
	/**
	 * 
	 * @param message
	 */
	public ServiceException(String message) {
		super(message);
	}
	
}
