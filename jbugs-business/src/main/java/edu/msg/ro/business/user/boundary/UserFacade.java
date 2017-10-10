package edu.msg.ro.business.user.boundary;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import edu.msg.ro.business.common.exception.BusinessException;
import edu.msg.ro.business.user.control.UserService;
import edu.msg.ro.business.user.dto.UserDTO;
import edu.msg.ro.persistence.user.entity.Role;
import edu.msg.ro.persistence.user.entity.User;

/**
 * Boundary for user component.
 * 
 * @author balinc
 * 
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class UserFacade {

	@EJB
	private UserService userService;

	/**
	 * Method for creating an {@link User}.
	 * 
	 * @param user
	 * @return
	 * @throws BusinessException
	 */
	public UserDTO createUser(UserDTO user) throws BusinessException {
		return userService.createUser(user);
	}

	/**
	 * Method for updating an {@link User}.
	 * 
	 * @param user
	 * @return
	 * @throws BusinessException
	 */
	public UserDTO updateUser(UserDTO user) throws BusinessException {
		return userService.updateUser(user);
	}

	/**
	 * Method for reseting the user password
	 * 
	 * @param user
	 * @return
	 * @throws BusinessException
	 */
	public UserDTO resetPassword(UserDTO user) throws BusinessException {
		return userService.resetPassword(user);
	}

	/**
	 * Method for deleting(deactivating) an {@link User}.
	 * 
	 * @param userDTO
	 * @return
	 * @throws BusinessException
	 */
	public UserDTO deleteUser(UserDTO userDTO) throws BusinessException {
		return userService.deleteUser(userDTO);

	}

	public UserDTO deleteUserNoCheck(UserDTO userDTO) {
		return userService.deleteUserNoCheck(userDTO);

	}

	/**
	 * Method for getting back all {@link User}s.
	 * 
	 * @return
	 */
	public List<UserDTO> getAllUsers() {
		return userService.getAllUsers();
	}

	/**
	 * Method for getting back all {@link User}s witch starts with the argument.
	 * 
	 * @param query
	 * @return
	 */
	public List<UserDTO> getAllUserByQuery(String query) {
		return userService.getAllUserByQuery(query);
	}

	/**
	 * Method for getting back a single {@link User} by username.
	 * 
	 * @param username
	 * @return
	 */
	public UserDTO getUserByUsername(String username) {
		return userService.findUserByUsername(username);
	}

	/**
	 * Method for getting back all {@link User} by role.
	 * 
	 * @param role
	 * @return
	 */
	public List<UserDTO> getUsersByRole(Role role) {
		return userService.findAllByRole(role);
	}
}
