package edu.msg.ro.business.util;

import java.util.ArrayList;
import java.util.Date;

import javax.ejb.Stateless;

import edu.msg.ro.business.bug.dto.BugDTO;
import edu.msg.ro.business.bug.enums.BugSeverity;
import edu.msg.ro.business.bug.enums.StatusEnum;
import edu.msg.ro.business.common.exception.BusinessException;
import edu.msg.ro.business.user.dto.UserDTO;

/**
 * 
 * @author nagya
 *
 */
@Stateless
public class TestHelper {

	public UserDTO initializUser(String firstname, String lastname, String email, String password, String phoneNumber)
			throws BusinessException {
		return initializUser(null, firstname, lastname, email, password, phoneNumber);
	}

	public UserDTO initializUser(Long id, String firstname, String lastname, String email, String password,
			String phoneNumber) throws BusinessException {
		UserDTO testUser = new UserDTO();
		testUser.setId(id);
		testUser.setFirstname(firstname);
		testUser.setLastname(lastname);
		testUser.setEmail(lastname);
		testUser.setEmail(email);
		testUser.setPassword(password);
		testUser.setPhoneNumber(phoneNumber);
		testUser.setRoles(new ArrayList<>());
		return testUser;
	}

	public BugDTO initializingBug(String title, String description, BugSeverity severity, String version,
			String fixedIn, StatusEnum status, UserDTO testUser) {
		return initializingBug(null, title, description, severity, version, fixedIn, status, testUser);
	}

	public BugDTO initializingBug(Long id, String title, String description, BugSeverity severity, String version,
			String fixedIn, StatusEnum status, UserDTO testUser) {
		BugDTO testBug = new BugDTO();
		testBug.setId(id);
		testBug.setTitle(title);
		testBug.setDescription(description);
		testBug.setAssigned(testUser);
		testBug.setAuthor(testUser);
		testBug.setVersion(version);
		testBug.setFixedIn(fixedIn);
		testBug.setTargetDate(new Date());
		testBug.setSeverity(severity);
		testBug.setStatus(status);
		return testBug;
	}

}
