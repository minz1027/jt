package edu.msg.ro.bean.user.validation;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

/**
 * Class for checking description lenght.
 * 
 * @author balinc
 *
 */
@FacesValidator("descriptionValidator")
public class DescriptionValidator extends AbstractValidator {

	/**
	 * Error message key.
	 */
	public static final String I18N_ERROR = "users.description.error";

	/**
	 * Check email format with regex
	 */
	@Override
	public void validate(FacesContext context, UIComponent uiComponent, Object value) throws ValidatorException {
		String description = value.toString().trim();
		if (description.length() < 250) {
			throw new ValidatorException(translate(I18N_ERROR));
		}

	}

}
