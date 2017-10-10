package edu.msg.ro.business.common.exception;

/**
 * Exception class for business problems.
 *
 * @author balinc
 *
 */
public class BusinessException extends JBugsExeption {

	private static final long serialVersionUID = -5628141671921410481L;

	/**
	 * Constructor.
	 * 
	 * @param message
	 */
	public BusinessException(String message) {
		super(message);
	}

	/**
	 * Constructor.
	 * 
	 * @param message
	 */
	public BusinessException(String message, Object arguments) {
		super(message, arguments);
	}
}
