package edu.msg.ro.bean.user.validation;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

/**
 * Class for checking email format
 * 
 * @author nemeta
 *
 */
@FacesValidator("emailValidator")
public class EmailValidator extends AbstractValidator {

	/**
	 * Error message key.
	 */
	public static final String I18N_ERROR = "users.email.error";

	/**
	 * Check email format with regex
	 */
	@Override
	public void validate(FacesContext context, UIComponent uiComponent, Object value) throws ValidatorException {
		String email = value.toString().trim();
		if (!email.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@+[msggroup.com]{12}$")) {
			throw new ValidatorException(translate(I18N_ERROR));
		}

	}

}
