package edu.msg.ro.business.user.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import edu.msg.ro.business.user.dao.UserDAO;
import edu.msg.ro.business.user.dto.UserDTO;
import edu.msg.ro.persistence.user.entity.User;

/**
 * 
 * @author nemeta
 * 
 *         Class for generating username and password for the user.
 */
@Stateless
public class UserGenerator {

	@EJB
	private UserDAO userDao;

	/**
	 * Creates the username
	 * 
	 * @param user
	 * @return
	 */
	public String createUsername(UserDTO user) {

		String firstName = user.getFirstname();
		String lastName = user.getLastname();

		int lastNameLength = lastName.length();
		int firstNameLength = firstName.length();
		StringBuilder username = new StringBuilder();
		int firstNamePos = 1;

		username.append(lastName.substring(0, Math.min(lastNameLength, 5)));
		username.append(firstName.substring(0, firstNamePos));

		while (checkIfUsernameExists(username.toString()) == true) {
			if (firstNameLength != 1) {
				username.append(firstName.substring(firstNamePos, firstNamePos + 1));
				firstNamePos++;
				firstNameLength--;
			} else {
				int randomNum = generateRandomNumber();
				username.append(randomNum);
			}
		}
		return username.toString();
	}

	/**
	 * Generates random int between 0-9
	 * 
	 * @return
	 */
	private int generateRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(10);
	}

	/**
	 * Checks if username is already taken.
	 * 
	 * @param username
	 * @return {@link Boolean}
	 */
	private boolean checkIfUsernameExists(String username) {
		User existingUser = userDao.findUserByUsername(username);
		if (existingUser != null) {
			String existingUsername = existingUser.getUsername();
			return existingUsername.equals(username);
		}
		return false;
	}

	/**
	 * Creates hash for user password.
	 *
	 * @param userDTO
	 * @return
	 */
	public String encryptPassword(UserDTO userDTO) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] md_password = md.digest(userDTO.getPassword().getBytes("UTF-8"));
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < md_password.length; i++) {
				sb.append(Integer.toString((md_password[i] & 0xff) + 0x100, 16).substring(1));
			}
			return sb.toString();
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			// not needed
		}
		return null;
	}

}
