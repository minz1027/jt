package edu.msg.ro.business.common.exception;

/**
 * Exception class for technical problems.
 * 
 * @author laszll
 *
 */
public class TechnicalExeption extends JBugsExeption {

	private static final String MESSAGE_KEY = "exeption.technical";

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 */
	public TechnicalExeption() {
		super(MESSAGE_KEY);
	}

	/**
	 * Constructor.
	 * 
	 * @param cause
	 */
	public TechnicalExeption(Throwable cause) {
		super(MESSAGE_KEY, cause);
	}
}
