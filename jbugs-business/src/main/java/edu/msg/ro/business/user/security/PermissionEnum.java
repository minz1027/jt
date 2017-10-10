package edu.msg.ro.business.user.security;

public enum PermissionEnum {
	PERMISSION_MANAGEMENT(1L), USER_MANAGEMENT(2L), BUG_MANAGEMENT(3L), BUG_CLOSE(4L), BUG_EXPORT(5L);

	private Long id;

	PermissionEnum(Long l) {
		this.id = l;
	}

	public Long getId() {
		return id;
	}

}
