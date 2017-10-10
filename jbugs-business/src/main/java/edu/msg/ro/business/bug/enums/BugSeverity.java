package edu.msg.ro.business.bug.enums;

/**
 * Enum for bug severity.
 * 
 * @author varadp
 *
 */
public enum BugSeverity {

	CRITICAL, HIGH, MEDIUM, LOW;

	public int key;

	/**
	 * Constructor.
	 */
	private BugSeverity() {
		this.key = ordinal();
	}

}
