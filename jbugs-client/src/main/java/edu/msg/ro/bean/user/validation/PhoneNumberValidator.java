package edu.msg.ro.bean.user.validation;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

/**
 * Class for Validating phone numbers.
 * 
 * @author nagya
 *
 */
@FacesValidator("phoneNumberValidator")
public class PhoneNumberValidator extends AbstractValidator {

	/**
	 * Error message key.
	 */
	public static final String I18N_ERROR = "users.phone.error";
	boolean isValidDE;
	boolean isValidRO;

	/**
	 * Check if the numbe ris a valid German or Romanian phone number with
	 * Google API and regexp
	 */
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

		String phoneNumber = value.toString();
		if (!phoneNumber.matches("^\\+?\\d+${6,13}")) {
			throw new ValidatorException(translate(I18N_ERROR));
		}

		PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
		try {
			PhoneNumber phoneNumberDE = phoneUtil.parse(phoneNumber, "DE");
			PhoneNumber phoneNumberRO = phoneUtil.parse(phoneNumber, "RO");

			isValidDE = phoneUtil.isValidNumberForRegion(phoneNumberDE, "DE");
			isValidRO = phoneUtil.isValidNumberForRegion(phoneNumberRO, "RO");

			if (!((isValidDE) || (isValidRO))) {
				throw new ValidatorException(translate(I18N_ERROR));

			}
		} catch (NumberParseException e) {
			throw new ValidatorException(translate(I18N_ERROR));
		}
	}
}
