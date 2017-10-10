package edu.msg.ro.bean.user.validation;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

/**
 * Class for password validation.
 * 
 * @author balinc
 *
 */
@FacesValidator("passwordValidator")
public class PasswordValidator extends AbstractValidator {

	/**
	 * Password minimum lenght.
	 */
	public static final Integer LENGHT = 6;

	/**
	 * Error message key.
	 */
	public static final String I18N_ERROR = "users.password.error";

	/**
	 * Method for validating password.
	 */
	@Override
	public void validate(FacesContext context, UIComponent uiComponent, Object value) throws ValidatorException {
		String stringValue = value.toString();
		if (stringValue.length() < LENGHT) {
			throw new ValidatorException(translate(I18N_ERROR, new Object[] { LENGHT }));
		}
	}
}
