package edu.msg.ro.business.util;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import edu.msg.ro.business.junit.bug.bundary.BugFacadeTest;
import edu.msg.ro.business.junit.bug.control.BugServiceTest;
import edu.msg.ro.business.junit.bug.dao.BugDAOTest;
import edu.msg.ro.business.junit.bug.dto.mapper.BugDTOMapperTest;
import edu.msg.ro.business.junit.common.dao.AbstractDAOTest;
import edu.msg.ro.business.junit.user.boundary.RoleFacadeTest;
import edu.msg.ro.business.junit.user.boundary.UserFacadeTest;
import edu.msg.ro.business.junit.user.boundary.UserLoginTest;
import edu.msg.ro.business.junit.user.boundary.UserPermissonTest;
import edu.msg.ro.business.junit.user.control.PermissionServiceTest;
import edu.msg.ro.business.junit.user.control.RoleServiceTest;
import edu.msg.ro.business.junit.user.control.UserServiceTest;
import edu.msg.ro.business.junit.user.dao.PermissionDAOTest;
import edu.msg.ro.business.junit.user.dao.RoleDAOTest;
import edu.msg.ro.business.junit.user.dao.UserDAOTest;
import edu.msg.ro.business.junit.user.dto.mapper.PermissionDTOMapperTest;
import edu.msg.ro.business.junit.user.dto.mapper.RoleDTOMapperTest;
import edu.msg.ro.business.junit.user.dto.mapper.UserDTOMapperTest;
import edu.msg.ro.business.junit.user.security.PermissionCheckerTest;
import edu.msg.ro.business.junit.user.validator.UserValidatorTest;

/**
 * SuitClass for JUnit test
 * 
 * @author varadp
 *
 */
@RunWith(Suite.class)

@SuiteClasses({ BugFacadeTest.class, BugServiceTest.class, BugDTOMapperTest.class, RoleFacadeTest.class,
		UserFacadeTest.class, UserLoginTest.class, UserPermissonTest.class, PermissionServiceTest.class,
		RoleServiceTest.class, UserDTOMapperTest.class, PermissionDTOMapperTest.class, RoleDTOMapperTest.class,
		UserValidatorTest.class, UserServiceTest.class, BugDAOTest.class, PermissionDAOTest.class, RoleDAOTest.class,
		AbstractDAOTest.class, UserDAOTest.class, PermissionCheckerTest.class

})
public class Alltest {

}
