package edu.msg.ro.i18n;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.ejb.Stateless;
import javax.faces.context.FacesContext;

/**
 * Translator service.
 * 
 * @author balinc
 */
@Stateless
public class Translator {

	public static final String INT_BUNDLE = "i18n.messages";

	/**
	 * Get ResourceBundle.
	 *
	 * @return {@link ResourceBundle}
	 */
	public ResourceBundle getResourceBundle() {
		Locale locale = getContext().getViewRoot().getLocale();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		return ResourceBundle.getBundle("i18n.messages", locale, loader);
	}

	/**
	 * Format string.
	 * 
	 * @param message
	 * @param arguments
	 * @return {@link String}
	 */
	public String messageFormat(String message, Object arguments) {
		MessageFormat formatter = new MessageFormat(message);
		formatter.setLocale(getContext().getViewRoot().getLocale());
		return formatter.format(arguments);
	}

	/**
	 * Translate string.
	 * 
	 * @param message
	 * @return {@link String}
	 */
	public String translate(String message) {
		return getResourceBundle().getString(message);
	}

	/**
	 * Translate string and replace placeholders.
	 * 
	 * @param message
	 * @param arguments
	 * @return {@link String}
	 */
	public String translate(String message, Object arguments) {
		return messageFormat(translate(message), arguments);
	}

	/**
	 * Get the context.
	 * 
	 * @return FacesContext
	 */
	public FacesContext getContext() {
		return FacesContext.getCurrentInstance();
	}
}
