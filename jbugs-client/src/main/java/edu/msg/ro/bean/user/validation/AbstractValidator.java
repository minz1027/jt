package edu.msg.ro.bean.user.validation;

import javax.faces.application.FacesMessage;
import javax.faces.validator.Validator;

import edu.msg.ro.i18n.Translator;

/**
 * Abastract Validator.
 * 
 * @author balinc
 *
 */
public abstract class AbstractValidator implements Validator {

	/**
	 * Translator {@link Translator}
	 */
	protected Translator t = new Translator();

	/**
	 * Translate message.
	 * 
	 * @param message
	 * @return {@link FacesMessage}
	 */
	protected FacesMessage translate(String message) {
		String translated = t.translate(message);
		return newMessage(translated);
	}

	/**
	 * Translate message and replace arguments.
	 * 
	 * @param message
	 * @param arguments
	 * @return {@link FacesMessage}
	 */
	protected FacesMessage translate(String message, Object arguments) {
		String translated = t.translate(message, arguments);
		return newMessage(translated);
	}

	/**
	 * Generate new FacesMessage message.
	 *
	 * @param message
	 * @return {@link FacesMessage}
	 */
	protected FacesMessage newMessage(String message) {
		return new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message);
	}
}
