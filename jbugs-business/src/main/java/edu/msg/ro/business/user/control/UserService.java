package edu.msg.ro.business.user.control;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import edu.msg.ro.business.common.exception.BusinessException;
import edu.msg.ro.business.user.dao.PermissionDAO;
import edu.msg.ro.business.user.dao.UserDAO;
import edu.msg.ro.business.user.dto.UserDTO;
import edu.msg.ro.business.user.dto.mapper.UserDTOMapper;
import edu.msg.ro.business.user.security.PermissionEnum;
import edu.msg.ro.business.user.util.UserGenerator;
import edu.msg.ro.business.user.validation.UserValidator;
import edu.msg.ro.persistence.user.entity.Role;
import edu.msg.ro.persistence.user.entity.User;

/**
 * Controller for {@link User} component.
 * 
 * @author balinc
 *
 */
@Stateless
public class UserService {

	@EJB
	private UserDTOMapper userDTOMapper;

	@EJB
	private UserDAO userDAO;

	@EJB
	UserValidator userValidator;

	@EJB
	UserGenerator userUtils;

	@EJB
	PermissionDAO permissionDAO;

	public static final String I18N_DELETE_USER_FAIL = "user.crud.delete.error";

	/**
	 * Method for creating a new {@link User}.
	 * 
	 * @param user
	 * @return
	 * @throws BusinessException
	 */
	public UserDTO createUser(UserDTO user) throws BusinessException {

		userValidator.validateUserData(user);

		User userEntity = new User();
		String username = userUtils.createUsername(user);
		user.setUsername(username);
		String password = userUtils.encryptPassword(user);
		user.setPassword(password);
		userDTOMapper.mapToEntity(user, userEntity);
		userEntity.setActive(true);
		userDAO.persistEntity(userEntity);
		User persistedUser = userDAO.findEntity(userEntity.getId());
		return userDTOMapper.mapToDTO(persistedUser);
	}

	/**
	 * Method for updating an {@link User}.
	 * 
	 * @param user
	 * @return
	 * @throws BusinessException
	 */
	public UserDTO updateUser(UserDTO user) throws BusinessException {

		userValidator.validateUserData(user);

		User persistedUser = userDAO.findEntity(user.getId());
		userDTOMapper.mapToEntity(user, persistedUser);
		return userDTOMapper.mapToDTO(persistedUser);
	}

	/**
	 * Reseting the password for user
	 * 
	 * @param user
	 * @return
	 */
	public UserDTO resetPassword(UserDTO user) {

		String password = userUtils.encryptPassword(user);
		user.setPassword(password);
		User persistedUser = userDAO.findEntity(user.getId());
		userDTOMapper.mapToEntity(user, persistedUser);
		return userDTOMapper.mapToDTO(persistedUser);
	}

	/**
	 * Method for deleting(deactivating) an {@link User}.
	 * 
	 * @param userDTO
	 * @return
	 * @throws BusinessException
	 */
	public UserDTO deleteUser(UserDTO userDTO) throws BusinessException {
		User userEntity = userDAO.findUserByUsername(userDTO.getUsername());
		if (userValidator.checkIfUserHasActiveTasks(userEntity) == false) {
			userEntity.setActive(false);
		} else {
			throw new BusinessException(I18N_DELETE_USER_FAIL, new Object[] { userDTO.getUsername() });
		}
		return userDTOMapper.mapToDTO(userEntity);
	}

	public UserDTO deleteUserNoCheck(UserDTO userDTO) {
		User userEntity = userDAO.findUserByUsername(userDTO.getUsername());
		userEntity.setActive(false);
		return userDTOMapper.mapToDTO(userEntity);
	}

	/**
	 * Method for verifying that the user with the given username and password
	 * exist(for login).
	 * 
	 * @param username
	 * @param pass
	 * @return {@link Boolean}
	 * @throws BusinessException
	 */
	public boolean findUserExists(String username, String pass) throws BusinessException {
		return userDAO.verifyUserExist(username, pass);
	}

	/**
	 * Method for getting back all the {@link User}s.
	 * 
	 * @return
	 */
	public List<UserDTO> getAllUsers() {
		return userDTOMapper.mapToDTOs(userDAO.getAll());
	}

	/**
	 * Get user by username.
	 * 
	 * @param username
	 * @return
	 */
	public UserDTO findUserByUsername(String username) {
		return userDTOMapper.mapToDTO(userDAO.findUserByUsername(username));
	}

	/**
	 * Get User list by username starts with.
	 * 
	 * @param query
	 * @return
	 */
	public List<UserDTO> getAllUserByQuery(String query) {
		return userDTOMapper.mapToDTOs(userDAO.getAllUsernameStartsWith(query));
	}

	/**
	 * Get all users with the role.
	 * 
	 * @param query
	 * @return
	 */
	public List<UserDTO> findAllByRole(Role role) {
		return userDTOMapper.mapToDTOs(userDAO.findAllByRole(role));
	}

	/**
	 * Get all users with the permission.
	 * 
	 * @param query
	 * @return
	 */
	public List<UserDTO> findAllByPermission(PermissionEnum permission) {
		return userDTOMapper.mapToDTOs(permissionDAO.getAllUsersByPermission(permission));
	}
}
