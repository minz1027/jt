package edu.msg.ro.business.common.exception;

/**
 * Exception class for JBugs applications exceptions.
 * 
 * @author laszll
 *
 */
public class JBugsExeption extends Exception {

	private static final long serialVersionUID = 1L;

	private final String message;

	private transient Object arguments = null;

	/**
	 * Constructor.
	 *
	 * @param message
	 */
	public JBugsExeption(String message) {
		super();
		this.message = message;
	}

	/**
	 * Constructor.
	 * 
	 * @param message
	 */
	public JBugsExeption(String message, Object arguments) {
		super();
		this.message = message;
		this.arguments = arguments;
	}

	/**
	 * Constructor.
	 * 
	 * @param message
	 * @param cause
	 */
	public JBugsExeption(String message, Throwable cause) {
		super(cause);
		this.message = message;
	}

	/**
	 * Constructor.
	 * 
	 * @param message
	 * @param cause
	 */
	public JBugsExeption(String message, Object arguments, Throwable cause) {
		super(cause);
		this.message = message;
		this.arguments = arguments;
	}

	/**
	 * Method for getting the message.
	 */
	@Override
	public String getMessage() {
		return message;
	}

	/**
	 * Get arguments for translation the message.
	 * 
	 * @return
	 */
	public Object getArguments() {
		return arguments;
	}
}
