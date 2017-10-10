package edu.msg.ro.bean;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import edu.msg.ro.enums.Language;

/**
 * Class for managing languages.
 * 
 * @author laszll
 *
 */
@ManagedBean
@SessionScoped
public class LanguageBean extends AbstractBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8657090779305459598L;

	private Locale locale = new Locale(Language.DEFAULT.getKey());

	/**
	 * Get for locale.
	 * 
	 * @return
	 */
	public Locale getLocale() {
		return locale;
	}

	/**
	 * Set for locale.
	 * 
	 * @param lang
	 */
	public void setLocale(Locale lang) {
		locale = lang;
	}

	/**
	 * Method for setting language to session.
	 * 
	 * @param lang
	 */
	public void setLanguage(Language lang) {
		locale = new Locale(lang.getKey());
		FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
	}

}