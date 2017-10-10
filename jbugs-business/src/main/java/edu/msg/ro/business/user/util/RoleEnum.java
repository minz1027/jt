package edu.msg.ro.business.user.util;

public enum RoleEnum {
	ADMIN(1L), PROJECT_MANAGER(2L), TEST_MANAGER(3L), DEVELOPER(4L), TESTER(5L);

	private Long id;

	RoleEnum(Long l) {
		this.id = l;
	}

	public Long getId() {
		return id;
	}
}
