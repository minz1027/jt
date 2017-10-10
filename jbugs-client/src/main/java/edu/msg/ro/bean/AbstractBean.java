package edu.msg.ro.bean;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import edu.msg.ro.business.common.exception.JBugsExeption;
import edu.msg.ro.business.user.dto.UserDTO;
import edu.msg.ro.i18n.Translator;

/**
 * AbstractBean class.
 * 
 * @author balinc
 *
 */
public abstract class AbstractBean {

	protected FacesContext context = FacesContext.getCurrentInstance();

	@EJB
	protected Translator t;

	/**
	 * Get data by key from session map.
	 * 
	 * @param key
	 * @return
	 */
	protected Object getSessionData(String key) {
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(key);
	}

	/**
	 * Get the logged {@link User}.
	 * 
	 * @return
	 */
	protected UserDTO getLoggedUser() {
		return (UserDTO) getSessionData("loggedUser");
	}

	/**
	 * Translate JBugsExeption exeption message.
	 * 
	 * @param e
	 */
	protected void handleExeptionI18n(JBugsExeption e) {
		String translated = t.translate(e.getMessage(), e.getArguments());
		t.getContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, translated, translated));
	}

	/**
	 * Translate message send to interface.
	 * 
	 * @param message
	 */
	protected void addI18nMessage(String message) {
		addI18nMessage(null, message, null);
	}

	/**
	 * Translate message send to interface.
	 * 
	 * @param key
	 * @param message
	 */
	protected void addI18nMessage(String key, String message) {
		addI18nMessage(key, message, null);
	}

	/**
	 * Translate message send to interface.
	 * 
	 * @param message
	 * @param arguments
	 */
	protected void addI18nMessage(String message, Object arguments) {
		addI18nMessage(null, message, arguments);
	}

	/**
	 * Translate message send to interface object.
	 * 
	 * @param key
	 * @param message
	 * @param arguments
	 */
	protected void addI18nMessage(String key, String message, Object arguments) {
		addMessage(key, t.translate(message, arguments));
	}

	/**
	 * Send new message to primeface.
	 * 
	 * @param message
	 */
	protected void addMessage(String message) {
		addMessage(null, message);
	}

	/**
	 * Send new message to primeface object.
	 * 
	 * @param key
	 * @param message
	 */
	protected void addMessage(String key, String message) {
		t.getContext().addMessage(key, new FacesMessage(message));
	}
}
