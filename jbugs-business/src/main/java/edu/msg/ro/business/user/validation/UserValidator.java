package edu.msg.ro.business.user.validation;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import edu.msg.ro.business.common.exception.BusinessException;
import edu.msg.ro.business.user.dao.UserDAO;
import edu.msg.ro.business.user.dto.UserDTO;
import edu.msg.ro.persistence.user.entity.User;

/**
 * Validate user.
 * 
 * @author balinc
 *
 */
@Stateless
public class UserValidator {

	@EJB
	private UserDAO userDAO;

	public static final String I18N_USER_EMAIL_EXISTS = "users.email.exists";

	/**
	 * Check for active user tasks.
	 *
	 * @param entity
	 * @return
	 */
	public boolean checkIfUserHasActiveTasks(User user) {
		return userDAO.checkIfUserHasAssignedBugs(user);
	}

	/**
	 * Check {@link User} integrity.
	 *
	 * @param email
	 * @return
	 * @throws BusinessException
	 */
	public void validateUserData(UserDTO user) throws BusinessException {
		validateEmail(user);
	}

	/**
	 * Check if {@link User} with this email already exist.
	 *
	 * @param user
	 * @return
	 * @throws BusinessException
	 */
	public void validateEmail(UserDTO user) throws BusinessException {
		User existingUserWithSameEmail = userDAO.findUserByEmail(user.getEmail());
		if (existingUserWithSameEmail != null && !existingUserWithSameEmail.getId().equals(user.getId())) {
			throw new BusinessException(UserValidator.I18N_USER_EMAIL_EXISTS, new Object[] { user.getEmail() });
		}
	}
}