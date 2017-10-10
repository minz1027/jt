package edu.msg.ro.enums;

/**
 * Enum for avabil languages.
 * 
 * @author laszll
 *
 */
public enum Language {
	DEFAULT("en"), ENGLISH("en"), ROMANIAN("ro");

	/**
	 * Constructor
	 * 
	 * @param key
	 */
	private Language(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	private final String key;
}
